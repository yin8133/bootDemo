package com.backstage.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.backstage.base.exception.XPFBadRequestException;
import com.backstage.base.models.User;
import com.backstage.base.response.XPFSingleResponse;
import com.backstage.base.service.IRedisService;
import com.backstage.base.service.UserService;
import com.backstage.bean.DataBean;
import com.backstage.constant.Common;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static java.awt.SystemColor.info;

/**
 * @Autor jiangcaijun
 * Created by jiangcaijun on 2017/10/27.
 */
@Api(description = "用户测试类")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger log = Logger.getLogger(this.getClass());

    @ApiOperation(value = "查询用户")
    @PostMapping(value ="/getUserList", produces  = "application/json;charset=UTF-8")
    public String getUserList(HttpServletRequest request) {
        DataBean dataBean = new DataBean();
        try {
            String param = request.getParameter("param");
            JSONObject parseObj = JSONObject.parseObject(param);
            log.info("==getUserList:" + parseObj);
            int pageNum = parseObj.getInteger("pageNum");
            int pageSize = parseObj.getInteger("pageSize");
            String name = parseObj.getString("name");
            PageInfo<User> userList = userService.getUserList(name, pageNum, pageSize);
            dataBean.setCode(Common.DATABEAN_CODE_SUCCESS);
            dataBean.setId("1");
            dataBean.setMessage(JSON.toJSONString(userList));
        } catch (Exception e) {
            e.printStackTrace();
            dataBean.setCode(Common.DATABEAN_CODE_ERROR);
            dataBean.setId("1");
            dataBean.setMessage(e.getMessage());
        }
        return dataBean.getJsonStr();
    }


}



