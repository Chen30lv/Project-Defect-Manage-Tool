package com.cityu.defect.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * After desensitization the user view is returned to the front end
 */
@Data
public class UserVO implements Serializable {

    private Long id;

    /**
     * account
     */
    private String account;

//    /**
//     * 用户角色（user/admin）
//     */
//    private String role;

    /**
     * createTime
     */
    private Timestamp createTime;
    /**
     * updateTime
     */
    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;
}
