package com.cityu.defect.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cityu.defect.model.dto.user.UserQueryRequest;
import com.cityu.defect.model.entity.User;
import com.cityu.defect.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService extends IService<User> {
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
    UserVO userLogin(String account, String password, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param user
     * @return
     */
    UserVO getUserVO(User user);
    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);
    /**
     * 获取脱敏的已登录用户信息
     *
     * @return
     */
    UserVO getLoginUserVO(User user);

//    /**
//     * 是否为管理员
//     *
//     * @param request
//     * @return
//     */
//    boolean isAdmin(HttpServletRequest request);

//    /**
//     * 是否为管理员
//     *
//     * @param user
//     * @return
//     */
//    boolean isAdmin(User user);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);
    /**
     * 获取脱敏的用户信息
     *
     * @param userList
     * @return
     */
    List<UserVO> getUserVO(List<User> userList);
    /**
     * 获取查询条件
     *
     * @param userQueryRequest
     * @return
     */
    List<User> getQueryWrapper(UserQueryRequest userQueryRequest);
}
