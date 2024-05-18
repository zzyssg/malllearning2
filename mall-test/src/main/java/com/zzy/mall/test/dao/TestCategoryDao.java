package com.zzy.mall.test.dao;

import com.zzy.mall.test.domain.TestCategoryExt;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName TestCategoryDao
 * @Author ZZy
 * @Date 2024/5/18 15:37
 * @Description
 * @Version 1.0
 */
@Repository
public interface TestCategoryDao {


    List<TestCategoryExt> selectByCategoryId(Long categoryId);

}
