package com.xxxx.hrm.service;

import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.UserAuthMapper;
import com.xxxx.hrm.utils.AssertUtil;
import com.xxxx.hrm.vo.UserAuth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAuthService extends BaseService<UserAuth,Integer> {

    @Resource
    private UserAuthMapper userAuthMapper;


    //用户登录功能
    public UserAuth login(String username, String password) {
        //参数校验
        AssertUtil.isTrue(StringUtils.isBlank(username),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(password),"密码不能为空");
        //调用dao层判断数据库中是否有此用户
        UserAuth dbUserAuth = userAuthMapper.queryUserByName(username);
        AssertUtil.isTrue(null == dbUserAuth,"用户不存在");
        //判断密码是否与数据库中密码相同
        AssertUtil.isTrue(!password.equals(dbUserAuth.getPassword()),"密码错误");

        //登陆成功，将userAuth对象返回给Controller层
        return dbUserAuth;
    }
}
