package com.cityu.defect.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "初始化测试Controller")//@Api：用在类上，说明该类的作用
@RestController
public class HelloController {

    @ApiOperation("初始化方法")//@ApiOperation：注解来给API增加方法说明
    @PostMapping("/hello")
    public String get(){
        return "hello Spring Boot!";
    }

}

