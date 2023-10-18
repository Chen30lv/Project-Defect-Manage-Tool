package com.cityu.defect.controller;

import com.cityu.defect.common.BaseResponse;
import com.cityu.defect.common.ErrorCode;
import com.cityu.defect.common.ResultUtils;
import com.cityu.defect.model.entity.Person;
import com.cityu.defect.model.dto.person.PersonLoginRequest;
import com.cityu.defect.model.dto.person.PersonRegisterRequest;
import com.cityu.defect.exception.BusinessException;
import com.cityu.defect.service.PersonService;
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
@RequestMapping("/person")
@Slf4j
public class PersonController {
    @Resource
    private PersonService personService;
    @ApiOperation("注册")
    @PostMapping("/register")
    public BaseResponse<Long> personRegister(@RequestBody PersonRegisterRequest personRegisterRequest) {
        if (personRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }
        String account = personRegisterRequest.getAccount();
        String password = personRegisterRequest.getPassword();
        String checkPassword = personRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(account,password,checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        long result = personService.personRegister(account, password, checkPassword);
        return ResultUtils.success(result);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public BaseResponse<Person> personLogin(@RequestBody PersonLoginRequest personLoginRequest, HttpServletRequest request) {
        if (personLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String account = personLoginRequest.getAccount();
        String password = personLoginRequest.getPassword();
        if(StringUtils.isAnyBlank(account,password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        Person person = personService.personLogin(account, password, request);
        return ResultUtils.success(person);
    }
}
