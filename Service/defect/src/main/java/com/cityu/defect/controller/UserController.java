package com.cityu.defect.controller;

import com.cityu.defect.common.BaseResponse;
import com.cityu.defect.common.ErrorCode;
import com.cityu.defect.common.ResultUtils;
import com.cityu.defect.model.entity.User;
import com.cityu.defect.model.dto.user.UserLoginRequest;
import com.cityu.defect.model.dto.user.UserRegisterRequest;
import com.cityu.defect.exception.BusinessException;
import com.cityu.defect.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @ApiOperation("注册")
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }
        String account = userRegisterRequest.getAccount();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(account,password,checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        long result = userService.userRegister(account, password, checkPassword);
        return ResultUtils.success(result);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String account = userLoginRequest.getAccount();
        String password = userLoginRequest.getPassword();
        if(StringUtils.isAnyBlank(account,password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        User user = userService.userLogin(account, password, request);
        return ResultUtils.success(user);
    }
}
