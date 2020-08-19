package com.zhaocheng.app;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

/**
 * webClient配置类
 * 用于高并发场景下发送大量异步请求
 * 其性能远远优于restTemplate
 */
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        //配置固定大小连接池，如最大连接数、连接获取超时、空闲连接死亡时间等
        // ConnectionProvider provider = ConnectionProvider.fixed("fixed", 20, 4000, Duration.ofSeconds(6));
        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(tcpClient -> {
                    //指定Netty的select 和 work线程数量
                    // LoopResources loop = LoopResources.create("event-loop", 1, 4, true);
                    return tcpClient.doOnConnected(connection -> {
                        //读写超时设置
                        connection.addHandlerLast(new ReadTimeoutHandler(20, TimeUnit.SECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(20));
                    })
                            //连接超时设置
                            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 20000)
                            .option(ChannelOption.TCP_NODELAY, true)
                            .wiretap(true);
                    //  .runOn(loop);
                });

        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> {
                    configurer.customCodecs().decoder(new Jackson2JsonDecoder());
                    configurer.customCodecs().encoder(new Jackson2JsonEncoder());
                })
                .build();
        return WebClient.builder()
                //设置webClient的baseUrl
                .baseUrl("http://yourUrl")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(strategies)
                .build();

    }
}
