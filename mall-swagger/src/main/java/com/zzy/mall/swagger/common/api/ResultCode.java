package com.zzy.mall.swagger.common.api;

/**
 * @ClassName ResultCode
 * @Author ZZy
 * @Date 2024/5/19 17:13
 * @Description
 * @Version 1.0
 */
public enum ResultCode implements IError {

    SUCCESS("成功",200),
    FAILED("失败",400),
    VALIDATE_FAILED("验证失败",401),
    AUTHENTICATE_FAILED("未授权或者token失效",402),
    FORBIDDEN("权限不足", 403),;

    private String message;

    private Integer code;

    ResultCode() {
    }

    ResultCode(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
}
