package com.cityu.defect.common;

/**
 * Error Code
 */
public enum ErrorCode {
    SUCCESS(0, "ok"),
    PARAMS_ERROR(40000, "Request parameter error"),
    NOT_LOGIN_ERROR(40100, "Not login error"),
    NO_AUTH_ERROR(40101, "No Authentication error"),
    NOT_FOUND_ERROR(40400, "Request data does not exist"),
    FORBIDDEN_ERROR(40300, "Deny access"),
    SYSTEM_ERROR(50000, "System internal exception"),
    OPERATION_ERROR(50001, "Operation failure");

    /**
     * status code
     */
    private final int code;

    /**
     * message
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
