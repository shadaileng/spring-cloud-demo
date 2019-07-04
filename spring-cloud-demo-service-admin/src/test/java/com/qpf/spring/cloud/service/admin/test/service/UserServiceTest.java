package com.qpf.spring.cloud.service.admin.test.service;

import com.github.pagehelper.PageInfo;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.service.admin.ServiceAdminApplication;
import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.service.admin.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

@ActiveProfiles("prod")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ServiceAdminApplication.class})
@Rollback
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetByIds() {
        BaseResult byIds = userService.getByIds(1, 2, 3);
        System.out.println(byIds);
    }

    @Test
    public void testPage() {
        PageInfo page = userService.page(1, 10, null);
        System.out.println(page);
    }

    @Test
    public void testSelectAll(){
        List<User> users = userService.selectAll();
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void testSaveAndDelete() {
        User user = new User();
//        user.setId(1);
        user.setUserName("0002");
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        user.setUserCode("0002");
        user.setLoginCode("0002");
        user.setMobile("13800000000");
        user.setPhone("13800000000");
        user.setSex("1");
        user.setUserType("1");
        user.setMgrType("1");
        user.setStatus("0");
        user.setCorpCode("B");
        user.setCorpName("Bank1");
        user.setCreateBy("1");
        user.setUpdateBy("1");
        BaseResult save = userService.save(user);
        System.out.println(save);

        BaseResult delete = userService.delete(2);

        System.out.println(delete);
    }

    @Test
    public void testGetById() {
        User user = new User();
        user.setId(1);
        BaseResult userById = userService.getById(user);
        System.out.println(userById);
    }

}
