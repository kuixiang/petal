package com.sunflower.petal.common;

import java.io.Serializable;

/**
 * @author sunny
 * @version 1.0.0
 * @since 2012-7-23
 */
@SuppressWarnings(value = "serial,unused")
public class Result<T> implements Serializable {

    /**
     * code
     */
    private Integer code;

    /**
     * message
     */
    private String message;

    /**
     * object
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
