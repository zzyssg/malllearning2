package com.zzy.mall.swagger.dao;

import com.zzy.mall.swagger.domain.TestResourceExt;
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
