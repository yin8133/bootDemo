package com.backstage.base.service;

import com.backstage.base.models.User;
import com.github.pagehelper.PageInfo;


public interface UserService {
    String getUserList(String name,int pageNum,int pageSize)throws Exception;
}