package com.zhaocheng.project.prop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 代码生成器配置类
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "zc.generator")
public class CodeGeneratorProperties {

    private String author;

    private String driverName;

    private String url;

    private String username = "root";

    private String passWord;

    private List<String> tables;

    private String tablePrefix;

    private String modelName;

    private String parentPackage;

    private String controller = "controller";

    private String service = "service";

    private String mapper = "mapper";

    private String entity = "entity";

    private boolean fileOverride = false;

    private boolean setSwagger2 = true;

    private boolean resultMap = true;

    private boolean columnList = true;

    private boolean entityLombokModel = true;

    private boolean restControllerStyle = true;
}
