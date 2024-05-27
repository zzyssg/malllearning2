package com.zzy.mall.rabbitmq.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName UmsAdmin
 * @Author ZZy
 * @Date 2024/5/20 21:48
 * @Description
 * @Version 1.0
 */
@Data
public class UmsAdmin {

    private String username;

    private String password;

    private String icon;

    private String email;

    private String nickName;

    private String note;

    private Date createTime;

    private Date loginTime;

    private Integer status;


}
