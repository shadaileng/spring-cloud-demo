package com.qpf.spring.cloud.sso.service.impl;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.mapper.UserMapper;
import com.qpf.spring.cloud.sso.service.LoginService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements LoginService {

    private UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User register(User user) {
        String now = new SimpleDateFormat("YYYYMMddHHmmssSSS").format(new Date());
        user.setCreateDate(now);
        user.setUpdateDate(now);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
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
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("loginCode", loginCode);
        User user = userMapper.selectOneByExample(example);

        if (user != null) {
            if (StringUtils.equals(user.getPassword(), DigestUtils.md5DigestAsHex(password.getBytes()))) {
                return user;
            }
        }

        return null;
    }
}
