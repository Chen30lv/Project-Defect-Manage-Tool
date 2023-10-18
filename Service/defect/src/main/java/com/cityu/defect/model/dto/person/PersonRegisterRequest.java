package com.cityu.defect.model.dto.person;

import lombok.Data;

import java.io.Serializable;

/**
 * 注册请求体
 */
@Data
public class PersonRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;
    private String account;
    private String password;
    private String checkPassword;
}
