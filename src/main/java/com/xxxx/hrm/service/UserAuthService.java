package com.xxxx.hrm.service;

import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.UserAuthMapper;
import com.xxxx.hrm.vo.UserAuth;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAuthService extends BaseService<UserAuth,Integer> {

    @Resource
    private UserAuthMapper userAuthMapper;
}
