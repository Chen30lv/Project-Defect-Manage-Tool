package com.cityu.defect.model.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户查询请求
 */
@Data
public class UserQueryRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 账户
     */
    private String account;

    /**
     * 用户角色：user/admin
     */
    private String role;

    private static final long serialVersionUID = 1L;
}