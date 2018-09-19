package com.backstage.base.service.impl;

import com.backstage.base.mapper.UserMapper;
import com.backstage.base.models.User;
import com.backstage.base.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * @author jiangcaijun
 * @desc resdis service
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> getUserList(String name,int pageNum,int pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.getUserList(name);
        PageInfo<User> page = new PageInfo<User>(userList);
        return page;
    }
}