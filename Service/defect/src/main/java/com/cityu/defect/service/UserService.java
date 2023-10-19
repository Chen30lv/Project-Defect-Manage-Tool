package com.cityu.defect.service;

import com.cityu.defect.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    /**
     * 用户注册
     * @param account 用户账户
     * @param password 用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String account, String password, String checkPassword);

    /**
     * 用户登录
     * @param account 用户账号
     * @param password 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String account, String password, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param user
     * @return
     */
    User getSaftyPerson(User user);
}
