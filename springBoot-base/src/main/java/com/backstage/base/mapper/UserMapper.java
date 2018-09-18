package com.backstage.base.mapper;


import com.backstage.base.models.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getUserList(@Param("name") String name)throws Exception;
}