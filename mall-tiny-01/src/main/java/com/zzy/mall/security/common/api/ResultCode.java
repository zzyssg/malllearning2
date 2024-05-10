package com.zzy.mall.security.common.api;

/**
 * @ClassName ResultCode
 * @Author ZZy
 * @Date 2024/5/8 23:14
 * @Description
 * @Version 1.0
 */
public enum ResultCode implements IError {


    SUCCESS("成功",200),
    FAILED("失败",300),
    CHECK_FAILED("校验失败", 401),
    UNAUTHENTICATION("未登录或者token认证失效",405),
    FORBIDDEN("未授权",403)
    ;


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
