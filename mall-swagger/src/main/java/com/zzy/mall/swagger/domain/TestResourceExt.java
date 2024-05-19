package com.zzy.mall.swagger.domain;

import com.zzy.mall.swagger.model.TestCategory;
import com.zzy.mall.swagger.model.TestResource;
import lombok.Data;

/**
 * @ClassName TestResourceExt
 * @Author ZZy
 * @Date 2024/5/16 22:54
 * @Description
 * @Version 1.0
 */
@Data
public class TestResourceExt extends TestResource {

    TestCategory category;


}
