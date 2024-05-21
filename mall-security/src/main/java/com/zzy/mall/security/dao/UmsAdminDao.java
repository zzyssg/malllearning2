package com.zzy.mall.security.dao;

import com.zzy.mall.security.model.UmsAdmin;
import org.springframework.stereotype.Repository;

import java.util.List;

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
