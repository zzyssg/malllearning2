package com.zzy.mall.test.tests;

import com.github.pagehelper.PageInfo;
import com.zzy.mall.test.dao.TestAdminDao;
import com.zzy.mall.test.model.TestAdmin;
import com.zzy.mall.test.service.TestAdminService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AdminTest
 * @Author ZZy
 * @Date 2024/5/16 21:02
 * @Description
 * @Version 1.0
 */
@SpringBootTest
public class AdminTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminTest.class);

    @Autowired
    private TestAdminDao adminDao;

    @Autowired
    TestAdminService adminService;

    @Test
    void testSelectById() {
        Long id = 20L;
        TestAdmin testAdmin = adminDao.selectById(id);
        LOGGER.info("id=20L testAdmin:{}", testAdmin);
    }

    @Test
    void testPageInfo() {
        String keyword = "zzy";
        PageInfo<TestAdmin> list = adminService.list(1, 5, keyword);
        LOGGER.info("testAdmin pageInfo:{}", list);
    }

    @Test
    void testCreate() {
        TestAdmin admin = new TestAdmin();
        admin.setCreateTime(new Date());
        admin.setEmail("comzzymalltest@qq.com");
        admin.setNickName("testAdminCreate");
        admin.setPassword("11233");
        int id = adminDao.insert(admin);
        LOGGER.info("insertAdmin test Id:{}", id);
        LOGGER.info("insertAdmin test admin.Id:{}", admin.getId());

    }

    @Test
    void testUpdate() {
        TestAdmin admin = new TestAdmin();
        admin.setId(20L);
        admin.setCreateTime(new Date());
        admin.setEmail("updateComzzymalltest@qq.com");
        admin.setNickName("updateTestAdminCreate");
        admin.setPassword("update11233");
        LOGGER.info("updateAdminTest:{}", admin);
    }

    @Test
    void testDelete() {
        TestAdmin admin = new TestAdmin();
        Long id = 20L;
        admin.setId(id);
        adminDao.deleteById(id);
    }

    @Test
    void testSelectByUsernameAndEmailLikeWithIf() {
        String username = "zzy";
        String email = "test";
        List<TestAdmin> testAdmins = adminDao.selectByUsernameAndEmailLikeWithIf(username, email);
        LOGGER.info("testSelectWithIf admins:{}", testAdmins);

    }

    @Test
    void testSelectByUsernameAndEmailLikeWithChoose() {
        String username = "zzy";
        String email = "test";
        List<TestAdmin> testAdmins = adminDao.selectByUsernameAndEmailLikeWithChoose(username, email);
        LOGGER.info("testSelectWithChoose admins:{}", testAdmins);

    }

    @Test
    void testSelectByUsernameAndEmailLikeWithWhere() {
        String username = "zzy";
        String email = "test";
        List<TestAdmin> testAdmins = adminDao.selectByUsernameAndEmailLikeWithWhere(username, email);
        LOGGER.info("testSelectWithWhere admins:{}", testAdmins);
    }

    @Test
    void testUpdateByIdSelectiveWithSet() {
        TestAdmin admin = new TestAdmin();
        admin.setId(22L);
        admin.setEmail("testUpdateWithSet");
        int count = adminDao.updateByIdSelectiveWithSet(admin);
        LOGGER.info("test updateByIdSelectiveWithSet:{}", count);

    }

    @Test
    void testInsertBatchWithForeach() {
        List<TestAdmin> admins = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TestAdmin admin = new TestAdmin();
            admin.setEmail("testInsertForeach1@com");
            admin.setUsername("testInsertForeach1");
            admin.setNickName("testInsertForeach1");
            admin.setCreateTime(new Date());
            admins.add(admin);
        }
        int count = adminDao.insertBatchWithForeach(admins);
        LOGGER.info("batchInsertForeach count:{}",count);
    }




}
