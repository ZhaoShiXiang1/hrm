package com.xxxx.hrm.dao;

import com.xxxx.hrm.base.BaseMapper;
import com.xxxx.hrm.query.UserAuthQuery;
import com.xxxx.hrm.vo.UserAuth;

import java.util.List;

//用户权限管理
public interface UserAuthMapper extends BaseMapper<UserAuth,Integer> {


    //根据用户名查询用户
    UserAuth queryUserByName(String username);

    //多条件分页查询数据
    public List<UserAuth> queryAllUserAuths(UserAuthQuery query);
}