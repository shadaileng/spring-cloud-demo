package com.qpf.spring.cloud.service.admin.service.impl;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.service.impl.AbstractServiceImpl;
import com.qpf.spring.cloud.service.admin.mapper.UserMapper;
import com.qpf.spring.cloud.service.admin.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractServiceImpl<User, UserMapper> implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public BaseResult login(String loginCode, String password) {
        BaseResult result;
        Example example = new Example(User.class);
        example.createCriteria().orEqualTo("loginCode", loginCode)
                .orEqualTo("email", loginCode)
                .orEqualTo("mobile", loginCode);
        User user = userMapper.selectOneByExample(example);
        if (user != null && StringUtils.equals(user.getPassword(), DigestUtils.md5DigestAsHex(password.getBytes()))) {
            result = BaseResult.OK(user, "登陆成功");
        } else {
            logger.error("登陆失败: 账号或密码不正确");
            result = BaseResult.ER("登陆失败: 账号或密码不正确");
        }
        return  result;
    }

}
