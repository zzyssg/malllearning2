package com.zzy.mall.test.domain;

import com.zzy.mall.test.model.TestCategory;
import com.zzy.mall.test.model.TestResource;
import lombok.Data;

import java.util.List;

/**
 * @ClassName TestCategoryExt
 * @Author ZZy
 * @Date 2024/5/16 22:57
 * @Description
 * @Version 1.0
 */
@Data
public class TestCategoryExt extends TestCategory {

    List<TestResource> resourceList;

}
