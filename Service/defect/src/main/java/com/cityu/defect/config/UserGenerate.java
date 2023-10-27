package com.cityu.defect.config;

import com.cityu.defect.common.ErrorCode;
import com.cityu.defect.exception.BusinessException;
import com.cityu.defect.mapper.UserMapper;
import com.cityu.defect.model.entity.User;
import com.cityu.defect.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.regex.Pattern;

@Component
@Slf4j
public class UserGenerate {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;
    private static final String SALT = "DEFECT";

    public void initUser(){
        log.info("Init the users......");
        doInit();
        log.info("User-Initiation finished......");
    }
    @PostConstruct
    private void doInit(){
        userService.addUser("dingyi","12345678");
        userService.addUser("wangyuchen","12345678");
        userService.addUser("wangjiahe","12345678");
        userService.addUser("lvguanchen","12345678");
        userService.addUser("lijiayi","12345678");
        userService.addUser("hayanguang","12345678");
    }
}
