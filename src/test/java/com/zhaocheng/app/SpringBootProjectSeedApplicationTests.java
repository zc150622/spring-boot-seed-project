package com.zhaocheng.app;

import com.zhaocheng.project.util.CodeGeneratorUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootProjectSeedApplicationTests {

    @Autowired
    private CodeGeneratorUtils codeGeneratorUtils;

    @Test
    void contextLoads() {

        codeGeneratorUtils.codeGenerator();
    }

}
