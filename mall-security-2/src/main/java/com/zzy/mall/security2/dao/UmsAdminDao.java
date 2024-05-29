package com.zzy.mall.security2.dao;

import com.zzy.mall.security2.model.UmsAdmin;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UmsAdminDao
 * @Author ZZy
 * @Date 2024/5/20 21:52
 * @Description
 * @Version 1.0
 */
@Repository
public interface UmsAdminDao {

    UmsAdmin selectById(Long id);

}
