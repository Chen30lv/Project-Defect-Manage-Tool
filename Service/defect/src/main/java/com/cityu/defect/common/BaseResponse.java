package com.cityu.defect.common;

import lombok.Data;

import java.io.Serializable;

/**
 * BaseResponse class
 */
@Data
public class BaseResponse<T> implements Serializable {
    //status code
    private int code;
    //data
    private T data;
    //message
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
