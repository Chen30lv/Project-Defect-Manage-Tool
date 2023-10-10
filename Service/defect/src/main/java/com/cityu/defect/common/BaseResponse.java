package com.cityu.defect.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 */
@Data
public class BaseResponse<T> implements Serializable {
    //状态码
    private int code;
    //数据
    private T data;
    //返回信息
    private String message;
    //返回描述
    private String description;

    public BaseResponse(int code,T data,String message,String description){
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }
    public BaseResponse(int code,T data,String message){
        this.code = code;
        this.data = data;
        this.message = message;
    }
    public BaseResponse(int code,T data){
        this(code,data,"","");
    }
    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(),null,errorCode.getMessage(), errorCode.getDescription());
    }
}
