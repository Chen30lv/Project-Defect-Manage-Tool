package com.cityu.defect.model.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * UserRegisterRequest
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;
    @ApiModelProperty(value="account",example="dingyi")
    private String account;
    @ApiModelProperty(value="password",example="12345678")
    private String password;
    @ApiModelProperty(value="checkPassword",example="12345678")
    private String checkPassword;
}
