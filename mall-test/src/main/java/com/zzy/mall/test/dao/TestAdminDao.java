package com.zzy.mall.test.dao;

import com.zzy.mall.test.model.TestAdmin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName TestAdminDao
 * @Author ZZy
 * @Date 2024/5/16 20:57
 * @Description
 * @Version 1.0
 */
@Repository
public interface TestAdminDao {

    //基础
    TestAdmin selectById(Long id);


    List<TestAdmin> selectLikeUsername(String usernameLike);

    int insert(TestAdmin admin);

    int update(TestAdmin admin);

    int deleteById(Long id);

    //动态 if、choose、where、set、foreach
    List<TestAdmin> selectByUsernameAndEmailLikeWithIf(@Param("username") String username, @Param("email") String email);

    List<TestAdmin> selectByUsernameAndEmailLikeWithChoose(@Param("username") String username, @Param("email") String email);

    List<TestAdmin> selectByUsernameAndEmailLikeWithWhere(@Param("username") String username, @Param("email") String email);

    int updateByIdSelectiveWithSet(TestAdmin admin);

    int insertBatchWithForeach(List<TestAdmin> admins);

    //高级  一对一映射、一对多映射、分页插件



}
