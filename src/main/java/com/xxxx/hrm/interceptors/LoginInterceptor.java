package com.xxxx.hrm.interceptors;

import com.xxxx.hrm.exceptions.NoLoginException;
import com.xxxx.hrm.service.UserAuthService;
import com.xxxx.hrm.vo.UserAuth;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private UserAuthService userAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过session查询当前登录用户
        UserAuth user = (UserAuth)request.getSession().getAttribute("user");
        //判断当前用户是否为空，以及数据库中是否含有当前用户
        if (null == user || null == userAuthService.selectByPrimaryKey(user.getId())){
            //不存在
            throw new NoLoginException();
        }
        //放行
        return true;
    }
}
