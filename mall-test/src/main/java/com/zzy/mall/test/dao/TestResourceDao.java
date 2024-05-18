package com.zzy.mall.test.dao;

import com.zzy.mall.test.domain.TestResourceExt;
import org.springframework.stereotype.Repository;

/**
 * @ClassName TestResourceDao
 * @Author ZZy
 * @Date 2024/5/16 22:56
 * @Description
 * @Version 1.0
 */
@Repository
public interface TestResourceDao {

    TestResourceExt selectById(Long id);

}
