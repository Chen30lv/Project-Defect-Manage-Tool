package com.cityu.defect.controller;

import com.cityu.defect.model.dto.statisticInfo.StatisticQueryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@SpringBootTest
class StatisticControllerTests {
    @Resource
    StatisticController statisticController;
    @Test
    void test(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        StatisticQueryRequest statisticQueryRequest = new StatisticQueryRequest();
        statisticQueryRequest.setKey("project");
        statisticQueryRequest.setUserId(1L);
        System.out.println(statisticController.list(statisticQueryRequest,request));
    }

}
