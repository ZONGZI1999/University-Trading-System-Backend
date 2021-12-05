package com.zekizheng.trading.dto;


/**
 * @author zongzi
 **/

public class HttpBaseResponse<T> {
    private ResponseCode message;
    private Integer code;
    private String description;
    private T data;

    public void setMessage(ResponseCode message) {
        this.message = message;
        this.code = message.getCode();
        this.description = message.getDescription();
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseCode getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public T getData() {
        return data;
    }
}
