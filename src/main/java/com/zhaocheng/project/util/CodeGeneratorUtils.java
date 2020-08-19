package com.zhaocheng.project.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.zhaocheng.project.prop.CodeGeneratorProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 */
@Component
@RequiredArgsConstructor
public class CodeGeneratorUtils {

    private final CodeGeneratorProperties codeGeneratorProperties;

    public void codeGenerator(){
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java")
          .setAuthor(codeGeneratorProperties.getAuthor())
          .setOpen(false)
          .setFileOverride(codeGeneratorProperties.isFileOverride())
          .setServiceName("%sService")
          .setIdType(IdType.AUTO)
          .setDateType(DateType.ONLY_DATE)
          .setSwagger2(codeGeneratorProperties.isSetSwagger2())//实体属性 Swagger2 注解
          .setBaseResultMap(codeGeneratorProperties.isResultMap())
          .setBaseColumnList(codeGeneratorProperties.isColumnList());

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(codeGeneratorProperties.getUrl())
           .setDriverName(codeGeneratorProperties.getDriverName())
           .setUsername(codeGeneratorProperties.getUsername())
           .setPassword(codeGeneratorProperties.getPassWord());

        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(codeGeneratorProperties.getModelName())
          .setParent(codeGeneratorProperties.getParentPackage())
          .setController(codeGeneratorProperties.getController())
          .setEntity(codeGeneratorProperties.getEntity())
          .setService(codeGeneratorProperties.getService())
          .setMapper(codeGeneratorProperties.getMapper());
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        List<String> tables = codeGeneratorProperties.getTables();

        strategy.setInclude(tables.toArray(new String[tables.size()]));
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        strategy.setEntityLombokModel(codeGeneratorProperties.isEntityLombokModel());
        strategy.setRestControllerStyle(codeGeneratorProperties.isRestControllerStyle());

        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(codeGeneratorProperties.getTablePrefix());
        TableFill create = new TableFill("create_time", FieldFill.INSERT);
        TableFill update = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        List<TableFill> tableFills = new ArrayList<>();
        tableFills.add(create);
        tableFills.add(update);
        strategy.setTableFillList(tableFills);

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController("templates/controller.java");
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.setTemplate(templateConfig);
        mpg.setStrategy(strategy);

        mpg.execute();
    }
}
