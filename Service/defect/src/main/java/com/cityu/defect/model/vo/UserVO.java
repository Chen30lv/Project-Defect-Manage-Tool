package com.cityu.defect.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 脱敏后用户视图，返回给前端
 */
@Data
public class UserVO implements Serializable {

    private Long id;

    /**
     * 账户
     */
    private String account;

    /**
     * 用户角色（user/admin）
     */
    private String role;

    /**
     * 用户创建时间
     */
    private Timestamp createTime;
    /**
     * 用户更新时间
     */
    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;
}
