package com.qpf.spring.cloud.service.admin.service.test;

import com.qpf.spring.cloud.service.admin.ServiceAdminApplication;
import com.qpf.spring.cloud.service.admin.entity.User;
import com.qpf.spring.cloud.service.admin.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ServiceAdminApplication.class})
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testSelectAll(){
        List<User> users = userService.selectAll();
        Assert.assertEquals(0, users.size());
    }

    @Test
    public void testRegister() {
        User user = new User();
        user.setUserName("0001");
        user.setPassword("0001");
        user.setUserCode("0001");
        user.setLoginCode("0001");
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
        Assert.assertEquals("1", register.getId());
    }

}
