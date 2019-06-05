package com.qpf.spring.cloud.sso.test.service;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.sso.service.LoginService;
import com.qpf.spring.cloud.sso.SSOApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Rollback
@Transactional
@ActiveProfiles("prod")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SSOApplication.class})
public class LoginServiceTest {
    @Autowired
    private LoginService loginService;

    @Test
    public void testRegister() {
        User user = new User();
        user.setUserName("0001");
        user.setPassword("123456");
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
        User register = loginService.register(user);
        Assert.assertEquals(user.getLoginCode(), register.getLoginCode());
    }
    @Test
    public void testLogin() {
        User login = loginService.login("0001", "123456");
        Assert.assertNotNull(login);
        System.out.println(login);
    }
}
