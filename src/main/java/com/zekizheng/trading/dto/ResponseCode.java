package com.zekizheng.trading.dto;

/**
 * @author zongzi
 **/
public enum ResponseCode {
    SUCCESS(0, "success"),
    USER_TYPE_ERROR(101, "user type error"),
    PARAM_ERROR(102, "param error"),
    DATABASE_ERROR(201, "databases error"),
    UNKNOWN_ERROR(999, "unknown error");


    private final int code;
    private final String description;

    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
