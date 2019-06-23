package com.qpf.spring.cloud.service.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.mapper.UserMapper;
import com.qpf.spring.cloud.service.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public PageInfo page(int start, int length, User user) {
//        PageHelper pageHelper = new PageHelper();
        PageHelper.startPage(start, length);
        return new PageInfo<>(userMapper.select(user));
    }

    @Override
    @Transactional
    public BaseResult save(User user) {
        Integer id = user.getId();
        int save;
        String now = String.valueOf(new Date().getTime());
        if (id != null) {
            user.setUpdateDate(now);
            save = userMapper.updateByPrimaryKeySelective(user);
        } else {
            user.setCreateDate(now);
            user.setUpdateDate(now);
            save = userMapper.insert(user);
        }
        return save > 0 ? BaseResult.OK(user, "保存成功") : BaseResult.ER("保存失败");
    }

    @Override
    @Transactional
    public BaseResult delete(User user) {
        int delete = userMapper.delete(user);
        return delete > 0 ? BaseResult.OK(user.getId(), "删除成功") : BaseResult.ER("删除失败");
    }

    @Override
    public BaseResult getUserById(User user) {
        user = userMapper.selectOne(user);
        return user != null ? BaseResult.OK(user, "查询成功") : BaseResult.ER("查询失败");
    }

}
