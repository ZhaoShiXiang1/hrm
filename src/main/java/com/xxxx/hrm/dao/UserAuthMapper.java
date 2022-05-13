package com.xxxx.hrm.dao;

import com.xxxx.hrm.base.BaseMapper;
import com.xxxx.hrm.vo.UserAuth;

//用户权限管理
public interface UserAuthMapper extends BaseMapper<UserAuth,Integer> {


    //根据用户名查询用户
    UserAuth queryUserByName(String username);
}