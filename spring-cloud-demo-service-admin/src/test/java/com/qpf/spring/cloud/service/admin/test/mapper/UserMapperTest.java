package com.qpf.spring.cloud.service.admin.test.mapper;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.service.admin.ServiceAdminApplication;
import com.qpf.spring.cloud.service.admin.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@ActiveProfiles("prod")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ServiceAdminApplication.class})
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelectByIds() {
        List<User> users = userMapper.selectByIds(1, 2, 3);
        System.out.println(users);
    }
    @Test
    public void testSelect() {
        User user = new User();
        user.setLoginCode("0001");
        user.setPassword("e10adc3949ba59abbe56e057f20f883e");
        List<User> users = userMapper.select(user);
        System.out.println(users);
    }
}
