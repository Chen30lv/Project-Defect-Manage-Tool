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
     * UserRegister
     * @param account account
     * @param password password
     * @param checkPassword checkPassword
     * @return User id
     */
    long userRegister(String account, String password, String checkPassword);

    /**
     * UserLogin
     * @param account account
     * @param password password
     * @param request
     * @return Desensitizing user information
     */
    UserVO userLogin(String account, String password, HttpServletRequest request);

    /**
     * Desensitizing user information
     * @param user
     * @return
     */
    UserVO getUserVO(User user);
    /**
     * getLoginUser
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);
    /**
     * Get desensitized user information
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
     * UserLogout
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    List<UserVO> getUserVOList(List<User> userList);

    List<User> getQueryWrapper(UserQueryRequest userQueryRequest);
}
