package com.zzy.mall.swagger.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName TestResource
 * @Author ZZy
 * @Date 2024/5/16 22:54
 * @Description
 * @Version 1.0
 */
@Data
public class TestResource {

    private Long id;

    private Date createTime;

    private String name;

    private String url;

    private String description;

    private Long categoryId;

}
