package com.zhaocheng.project.config;

import com.zhaocheng.project.prop.ExecutorProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 * @author zhao.cheng
 */
@Configuration
@EnableAsync
@Slf4j
@EnableConfigurationProperties(ExecutorProperties.class)
@RequiredArgsConstructor
@ConditionalOnProperty(value = "zc.executor.enable",havingValue = "true")
public class ExecutorConfig {

    private final ExecutorProperties executorProperties;

    @Bean("asyncServiceExecutor")
    public Executor asyncServiceExecutor() {

        log.info("start asyncServiceExecutor");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(executorProperties.getCorePoolSize());

        executor.setMaxPoolSize(executorProperties.getMaxPoolSize());

        executor.setQueueCapacity(executorProperties.getQueueCapacity());
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(executorProperties.getKeepAliveSeconds());

        executor.setThreadNamePrefix(executorProperties.getThreadNamePrefix());

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        return executor;
    }
}
