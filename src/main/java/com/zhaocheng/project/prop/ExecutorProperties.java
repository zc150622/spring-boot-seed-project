package com.zhaocheng.project.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZC
 * @date 2020/8/19 22:34
 */
@Data
@Configuration
@ConfigurationProperties("zc.executor")
public class ExecutorProperties {

    private Boolean enable = false;

    private Integer corePoolSize;

    private Integer maxPoolSize;

    private Integer queueCapacity;

    private Integer keepAliveSeconds;

    private String threadNamePrefix;
}
