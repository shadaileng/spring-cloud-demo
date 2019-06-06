package com.qpf.spring.cloud.sso.service.impl;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.mapper.UserMapper;
import com.qpf.spring.cloud.sso.service.LoginService;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements LoginService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult register(User user) {

//        Example example = new Example(User.class);
//        example.createCriteria().andEqualTo("loginCode", user.getLoginCode());
        User user_ = userMapper.selectOneByExample(new Example(User.class).
                createCriteria().andEqualTo("loginCode", user.getLoginCode()));
        if ( user_ != null) {
            logger.warn("登陆账号{}已存在", user.getLoginCode());
            return BaseResult.ER(String.format("登陆账号%s已存在", user.getLoginCode()));
        }
        user_ = userMapper.selectOneByExample(new Example(User.class).
                createCriteria().andEqualTo("email", user.getEmail()));
        if ( user_ != null) {
            logger.warn("邮箱{}已存在", user.getEmail());
            return BaseResult.ER(String.format("邮箱%s已存在", user.getLoginCode()));
        }
        user_ = userMapper.selectOneByExample(new Example(User.class).
                createCriteria().andEqualTo("phone", user.getPhone()));
        if ( user_ != null) {
            logger.warn("手机号码{}已存在", user.getPhone());
            return BaseResult.ER(String.format("手机号码%s已存在", user.getLoginCode()));
        }

        String now = new SimpleDateFormat("YYYYMMddHHmmssSSS").format(new Date());
        user.setCreateDate(now);
        user.setUpdateDate(now);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        int insert = userMapper.insert(user);
        if (insert > 0) {
            Example example = new Example(User.class);
            example.createCriteria().andEqualTo("id", insert);
            user = userMapper.selectOneByExample(example);
        } else {
            user = null;
        }
        return user != null ? BaseResult.OK(user) : BaseResult.ER("注册失败");
    }

    @Override
    public User login(String loginCode, String password) {
        Example example = new Example(User.class);
        example.createCriteria().orEqualTo("loginCode", loginCode)
        .orEqualTo("email", loginCode)
        .orEqualTo("mobile", loginCode);
        User user = userMapper.selectOneByExample(example);

        if (user != null) {
            if (StringUtils.equals(user.getPassword(), DigestUtils.md5DigestAsHex(password.getBytes()))) {
                return user;
            }
        }

        return null;
    }
}
