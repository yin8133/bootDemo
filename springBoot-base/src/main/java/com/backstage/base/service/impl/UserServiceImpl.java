package com.backstage.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.backstage.base.mapper.UserMapper;
import com.backstage.base.models.User;
import com.backstage.base.service.IRedisService;
import com.backstage.base.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IRedisService redisService;
    private final Logger log = Logger.getLogger(this.getClass());
    @Override
    public String getUserList(String name, int pageNum, int pageSize) throws Exception {
        String result = "";
        if (redisService.get("getUserList_" + pageNum + "_" + pageSize+"_"+name) == null) {
            PageHelper.startPage(pageNum, pageSize);
            List<User> userList = userMapper.getUserList(name);
            PageInfo<User> page = new PageInfo<User>(userList);
            result = JSON.toJSONString(page);
            redisService.set("getUserList_" + pageNum + "_" + pageSize+"_"+name,result,30L);
            log.info("=====存入缓存=====");
        } else {
            result = redisService.get("getUserList_" + pageNum + "_" + pageSize+"_"+name);
            log.info("=====读取缓存=====");
        }
        return result;
    }
}