package com.qpf.spring.cloud.service.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
        BaseResult result = null;
        Example example = new Example(User.class);
        example.createCriteria().orEqualTo("loginCode", loginCode)
                .orEqualTo("email", loginCode)
                .orEqualTo("mobile", loginCode);
        User user = userMapper.selectOneByExample(example);
        if (user != null && StringUtils.equals(user.getPassword(), DigestUtils.md5DigestAsHex(password.getBytes()))) {
            result = BaseResult.OK(user, "登陆成功");
        } else {
            result = BaseResult.ER("登陆失败");
        }
        return  result;
    }

    @Override
    public BaseResult register(User user) {
        BaseResult result;
        User query = new User();
        query.setLoginCode(user.getLoginCode());
        if (getByBean(query).getResult()) {
            String msg = String.format("登陆账号%s已存在", user.getLoginCode());
            logger.warn(msg);
            return BaseResult.ER(msg);
        }
        query = new User();
        query.setEmail(user.getEmail());
        if (getByBean(query).getResult()) {
            String msg = String.format("邮箱%s已存在", user.getEmail());
            logger.warn(msg);
            return BaseResult.ER(msg);
        }

        query = new User();
        query.setEmail(user.getPhone());
        if (getByBean(query).getResult()) {
            String msg = String.format("电话%s已存在", user.getPhone());
            logger.warn(msg);
            return BaseResult.ER(msg);
        }

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return save(user);
    }

    private BaseResult checkUser(User user) {
        Arrays.asList(new String[]{"loginCode", "email", "phone"}).forEach(field -> {
            String methodName_get = String.format("get%s%s", Character.toUpperCase(field.charAt(0)), field.substring(1));
            String methodName_set = String.format("get%s%s", Character.toUpperCase(field.charAt(0)), field.substring(1));
            User query = new User();
            try {
                Method method_set = User.class.getMethod(methodName_set, String.class);
                Method method_get = User.class.getMethod(methodName_get);
                method_set.invoke(query, method_get.invoke(user));
                if (getByBean(query).getResult()) {

                }
            } catch (Exception e) {
                logger.error(String.format("ERROR: %s", e.getMessage()));
            }
        });

        return null;
    }
}
