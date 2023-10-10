package com.cityu.defect.entity.request;

import lombok.Data;

/**
 * 登录请求体
 */
@Data
public class PersonLoginRequest {
    private String accout;
    private String password;
}
