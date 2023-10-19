package com.cityu.defect.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新请求
 */
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 账户
     */
    private String account;

    /**
     * 密码
     */
    private String password;


    /**
     * 用户角色：user/admin
     */
    private String role;

    private static final long serialVersionUID = 1L;
}