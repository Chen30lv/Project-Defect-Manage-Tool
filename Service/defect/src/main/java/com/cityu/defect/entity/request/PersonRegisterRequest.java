package com.cityu.defect.entity.request;

import lombok.Data;

/**
 * 注册请求体
 */
@Data
public class PersonRegisterRequest {
    private String accout;
    private String password;
    private String checkPassword;
}
