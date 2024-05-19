package com.zzy.mall.swagger.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName TestAdmin
 * @Author ZZy
 * @Date 2024/5/16 20:57
 * @Description
 * @Version 1.0
 */
@Data
public class TestAdmin {

    private Long id;

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
