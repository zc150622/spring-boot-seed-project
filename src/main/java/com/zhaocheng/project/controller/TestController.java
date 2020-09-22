package com.zhaocheng.project.controller;

import com.zhaocheng.project.core.ResponseTypeEnum;
import com.zhaocheng.project.core.annotion.ResponseResultBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhao.cheng
 * @date 2020/9/22 11:10
 */
@RestController
public class TestController {

    @ResponseResultBody
    @GetMapping("/string")
    public String testString() {
        return "123";
    }

    @ResponseResultBody(ResponseTypeEnum.DATA)
    @GetMapping("/data")
    public String testData() {
        return "456";
    }

    @ResponseResultBody(ResponseTypeEnum.STATUS)
    @GetMapping("/status")
    public boolean status() {
        return false;
    }
}
