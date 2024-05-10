package com.zzy.mall.security.common.api;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ClassName CommonResult
 * @Author ZZy
 * @Date 2024/5/8 23:20
 * @Description
 * @Version 1.0
 */
@Data
public class CommonResult<T> {


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
    public static  <T> CommonResult<T> success(T data) {
        return new CommonResult(ResultCode.SUCCESS.getMessage(), ResultCode.SUCCESS.getCode(), data);
    }

    //失败
    public static <T> CommonResult<T> failed() {
        return new CommonResult(ResultCode.FAILED.getMessage(), ResultCode.FAILED.getCode(), null);
    }

    //校验失败
    public static <T> CommonResult<T> checkFailed() {
        return new CommonResult<>(ResultCode.CHECK_FAILED.getMessage(), ResultCode.CHECK_FAILED.getCode(), null);
    }

    //未登录或者token失效
    public static <T> CommonResult<T> unauthenticated() {
        return new CommonResult<>(ResultCode.UNAUTHENTICATION.getMessage(), ResultCode.UNAUTHENTICATION.getCode(), null);
    }

    public static <T> CommonResult<T> unauthenticated(String message) {
        return new CommonResult<>(message, ResultCode.UNAUTHENTICATION.getCode(), null);
    }

    //未授权
    public static <T> CommonResult<T> forbidden() {
        return new CommonResult<>(ResultCode.FORBIDDEN.getMessage(), ResultCode.FORBIDDEN.getCode(), null);
    }

    public static <T> CommonResult<T> forbidden(String message) {
        return new CommonResult<>(message, ResultCode.FORBIDDEN.getCode(), null);
    }

}
