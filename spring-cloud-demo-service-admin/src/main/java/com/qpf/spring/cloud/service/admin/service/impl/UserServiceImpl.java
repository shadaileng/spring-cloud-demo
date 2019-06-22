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

import java.text.SimpleDateFormat;
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
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(start, length);
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.select(user));
        return pageInfo;
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(User user) {
        Integer id = user.getId();
        int save = 0;
        String now = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
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
    @Transactional(readOnly = false)
    public BaseResult delete(User user) {
        int delete = userMapper.delete(user);
        return delete > 0 ? BaseResult.OK(user.getId(), "删除成功") : BaseResult.ER("删除失败");
    }

}
