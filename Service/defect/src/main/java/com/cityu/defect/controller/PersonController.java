package com.cityu.defect.controller;

import com.cityu.defect.common.BaseResponse;
import com.cityu.defect.common.ErrorCode;
import com.cityu.defect.common.ResultUtils;
import com.cityu.defect.entity.Person;
import com.cityu.defect.entity.request.PersonLoginRequest;
import com.cityu.defect.entity.request.PersonRegisterRequest;
import com.cityu.defect.exception.BusinessException;
import com.cityu.defect.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "Person Controller")//@Api：用在类上，说明该类的作用
@RestController
@RequestMapping("/person")
public class PersonController {
    @Resource
    private PersonService personService;
    @ApiOperation("注册")
    @PostMapping("/register")
    public BaseResponse<Long> personRegister(@RequestBody PersonRegisterRequest personRegisterRequest) {
        if (personRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }
        String account = personRegisterRequest.getAccout();
        String password = personRegisterRequest.getPassword();
        String checkPassword = personRegisterRequest.getCheckPassword();
        if (account.isEmpty() || password.isEmpty() || checkPassword.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        long result = personService.personRegister(account, password, checkPassword);
        return ResultUtils.success(result,"注册成功！");
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public BaseResponse<Person> personLogin(@RequestBody PersonLoginRequest personLoginRequest, HttpServletRequest request) {
        if (personLoginRequest == null) {
            return null;
        }
        String account = personLoginRequest.getAccout();
        String password = personLoginRequest.getPassword();
        if (account.isEmpty() || password.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        Person person = personService.personLogin(account, password, request);
        return ResultUtils.success(person,"登录成功！");
    }
}
