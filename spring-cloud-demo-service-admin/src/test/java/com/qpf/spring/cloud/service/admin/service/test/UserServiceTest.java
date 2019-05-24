package com.qpf.spring.cloud.service.admin.service.test;

import com.qpf.spring.cloud.service.admin.ServiceAdminApplication;
import com.qpf.spring.cloud.service.admin.entity.User;
import com.qpf.spring.cloud.service.admin.service.UserService;
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
        System.out.println(users);
    }
}
