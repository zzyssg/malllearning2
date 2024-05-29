package com.zzy.mall.security2.common.api;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CommonResult
 * @Author ZZy
 * @Date 2024/5/20 23:33
 * @Description
 * @Version 1.0
 */
@Data
public class CommonResult<T> implements Serializable {

    private String message;

    private Integer code;

    private T data;

    public CommonResult() {

    }

    public CommonResult(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    //成功
    public static <T> CommonResult<T> success(){
        return new CommonResult(ResultCode.SUCCESS.getMessage(), ResultCode.SUCCESS.getCode(), null);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultCode.SUCCESS.getMessage(), ResultCode.SUCCESS.getCode(), data);
    }

    //失败
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<>(ResultCode.FAILED.getMessage(), ResultCode.FAILED.getCode(), null);
    }

    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getMessage(), ResultCode.VALIDATE_FAILED.getCode(), null);
    }

    public static <T> CommonResult<T> unAuthenticated(String message) {
        return new CommonResult<>(ResultCode.UNAUTHENTICATION.getMessage(), ResultCode.UNAUTHENTICATION.getCode(), null);
    }

    public static <T> CommonResult<T> forbidden(String message) {
        return new CommonResult<>(ResultCode.FORBIDDEN.getMessage(), ResultCode.FORBIDDEN.getCode(), null);
    }

}
