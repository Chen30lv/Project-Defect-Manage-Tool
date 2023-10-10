package com.cityu.defect.controller;

import com.cityu.defect.common.BaseResponse;
import com.cityu.defect.common.ResultUtils;
import com.cityu.defect.entity.Person;
import com.cityu.defect.repository.PersonRepository;
import com.cityu.defect.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "初始化测试Controller")//@Api：用在类上，说明该类的作用
@RestController
public class HelloController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @ApiOperation("初始化方法")//@ApiOperation：注解来给API增加方法说明
    @GetMapping("/hello")
    public BaseResponse<String> get(){
        List<Person> list = personRepository.findAll();
        String str = "";
        for(Person p : list){
            str = str.concat(p.toString());
        }
        return ResultUtils.success(str);
    }

    /**
     * 测试接口，未做加密
     * @param account 用户名称
     * @param password 密码
     * @return object 1 返回成功
     */
    @ResponseBody
    @RequestMapping("/addPerson")
    public Object addPerson(@RequestParam(value="account") String account, @RequestParam(value="password") String password){

        return personService.addPerson(account, password);
    }

}

