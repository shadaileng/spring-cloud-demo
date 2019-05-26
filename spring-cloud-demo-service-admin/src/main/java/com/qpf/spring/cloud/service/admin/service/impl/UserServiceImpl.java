package com.qpf.spring.cloud.service.admin.service.impl;

import com.qpf.spring.cloud.service.admin.entity.User;
import com.qpf.spring.cloud.service.admin.mapper.UserMapper;
import com.qpf.spring.cloud.service.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User register(User user) {
        String now = new SimpleDateFormat("YYYYmmDDHHMMSS").format(new Date());
        user.setCreateDate(now);
        user.setUpdateDate(now);
        int insert = userMapper.insert(user);
        if (insert > 0) {
            Example example = new Example(User.class);
            example.createCriteria().andEqualTo("id", insert);
            userMapper.selectOneByExample(example);
        } else {
            user = null;
        }
        return user;
    }

    @Override
    public User login(String loginCode, String password) {
        return null;
    }
}
