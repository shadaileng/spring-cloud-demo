package com.qpf.spring.cloud.service.admin.service.test;

import com.qpf.spring.cloud.service.admin.ServiceAdminApplication;
import com.qpf.spring.cloud.service.admin.entity.User;
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

import java.lang.reflect.Field;
import java.util.List;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ServiceAdminApplication.class})
@Rollback
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testRegister() {
        User user = new User();
        user.setUserName("0002");
        user.setPassword("0002");
        user.setUserCode("0002");
        user.setLoginCode("0002");
        user.setMobile("13800000000");
        user.setPhone("13800000000");
        user.setSex("1");
        user.setUserType("1");
        user.setMgrType("1");
        user.setStatus("0");
        user.setCorpCode("B");
        user.setCorpName("Bank");
        user.setCreateBy("1");
        user.setUpdateBy("1");
        User register = userService.register(user);
        Assert.assertEquals(user.getLoginCode(), register.getLoginCode());
    }
    @Test
    public void testLogin() {
        User login = userService.login("0001", "0001");
        Assert.assertNotNull(login);
        System.out.println(login);
    }

    @Test
    public void testSelectAll(){
        List<User> users = userService.selectAll();
        Assert.assertEquals(1, users.size());
    }

}
