package com.zzy.mall.test.domain;

import com.zzy.mall.test.model.TestCategory;
import com.zzy.mall.test.model.TestResource;
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
