package com.zzy.mall.swagger.common.api;

/**
 * @ClassName CommonResult
 * @Author ZZy
 * @Date 2024/5/19 17:19
 * @Description
 * @Version 1.0
 */
public class  CommonResult<T> {

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

    //成功 调用的方法
    public static <T> CommonResult<T> success() {
        return new CommonResult<>(ResultCode.SUCCESS.getMessage(), ResultCode.SUCCESS.getCode(), null);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultCode.SUCCESS.getMessage(), ResultCode.SUCCESS.getCode(), data);
    }

    //失败 调用的方法
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<>(message, ResultCode.FAILED.getCode(), null);
    }

    //验证失败 调用的方法
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<>(message, ResultCode.VALIDATE_FAILED.getCode(), null);
    }

    //认证失效  调用的方法
    public static <T> CommonResult<T> authenticateFailed(String message) {
        return new CommonResult<>(message, ResultCode.AUTHENTICATE_FAILED.getCode(), null);
    }

    //未授权 调用的方法
    public static <T> CommonResult<T> forbidden(String message) {
        return new CommonResult<>(message, ResultCode.FORBIDDEN.getCode(), null);
    }
}
