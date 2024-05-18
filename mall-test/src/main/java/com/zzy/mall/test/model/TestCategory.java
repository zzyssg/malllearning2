package com.zzy.mall.test.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName TestCategory
 * @Author ZZy
 * @Date 2024/5/16 22:57
 * @Description
 * @Version 1.0
 */
@Data
public class TestCategory {

    private Long id;

    private Date createTime;

    private String name;

    private Integer sort;

}
