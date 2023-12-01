package com.cityu.defect.controller;

import com.cityu.defect.exception.BusinessException;
import com.cityu.defect.model.dto.user.UserLoginRequest;
import com.cityu.defect.model.dto.user.UserRegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserControllerTests {

    @Resource
    private UserController userController;
    @Test
    void testRegister(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        //the account is too short
        userRegisterRequest.setAccount("dy");
        userRegisterRequest.setPassword("12345678");
        userRegisterRequest.setCheckPassword("12345678");
        BusinessException businessException = assertThrows(BusinessException.class,() ->{
            userController.userRegister(userRegisterRequest);
        });
        System.out.println(businessException.getMessage());
        //the password is too short
        userRegisterRequest.setAccount("dingyi");
        userRegisterRequest.setPassword("1234");
        userRegisterRequest.setCheckPassword("1234");
        businessException = assertThrows(BusinessException.class,() ->{
            userController.userRegister(userRegisterRequest);
        });
        System.out.println(businessException.getMessage());
        //the account cannot contain special characters
        userRegisterRequest.setAccount("dingyi???");
        userRegisterRequest.setPassword("12345678");
        userRegisterRequest.setCheckPassword("12345678");
        businessException = assertThrows(BusinessException.class,() ->{
            userController.userRegister(userRegisterRequest);
        });
        System.out.println(businessException.getMessage());
        //password is not equal to checkPassword
        userRegisterRequest.setAccount("dingyi");
        userRegisterRequest.setPassword("12345678");
        userRegisterRequest.setCheckPassword("1234567");
        businessException = assertThrows(BusinessException.class,() ->{
            userController.userRegister(userRegisterRequest);
        });
        System.out.println(businessException.getMessage());
        //the user already exists
        userRegisterRequest.setAccount("dingyi");
        userRegisterRequest.setPassword("12345678");
        userRegisterRequest.setCheckPassword("12345678");
        businessException = assertThrows(BusinessException.class,() ->{
            userController.userRegister(userRegisterRequest);
        });
        System.out.println(businessException.getMessage());
        //success
        userRegisterRequest.setAccount("Bryant");
        userRegisterRequest.setPassword("12345678");
        userRegisterRequest.setCheckPassword("12345678");
        System.out.println(userController.userRegister(userRegisterRequest));
    }
    @Test
    void testLogin(){
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //param cannot be null
        BusinessException businessException = assertThrows(BusinessException.class,() ->{
            userController.userLogin(userLoginRequest,request);
        });
        System.out.println(businessException.getMessage());
        //user login failed, account doesn't exist
        userLoginRequest.setAccount("dingyi!");
        userLoginRequest.setPassword("1234");
        businessException = assertThrows(BusinessException.class,() ->{
            userController.userLogin(userLoginRequest,request);
        });
        System.out.println(businessException.getMessage());
        //user login failed, password is wrong
        userLoginRequest.setAccount("dingyi");
        userLoginRequest.setPassword("12345679");
        businessException = assertThrows(BusinessException.class,() ->{
            userController.userLogin(userLoginRequest,request);
        });
        System.out.println(businessException.getMessage());
        //success
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setAccount("Bryant");
        userRegisterRequest.setPassword("12345678");
        userRegisterRequest.setCheckPassword("12345678");
        System.out.println(userController.userRegister(userRegisterRequest));
        userLoginRequest.setAccount("Bryant");
        userLoginRequest.setPassword("12345678");
        System.out.println(userController.userLogin(userLoginRequest,request));
    }
    @Test
    void testLogout(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setAccount("Bryant");
        userRegisterRequest.setPassword("12345678");
        userRegisterRequest.setCheckPassword("12345678");
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        userLoginRequest.setAccount("Bryant");
        userLoginRequest.setPassword("12345678");
        System.out.println(userController.userLogout(request));
    }
}
