/*
package com.xxxx.hrm.interceptors;

import com.xxxx.crm.exceptions.NoLoginException;
import com.xxxx.crm.service.UserService;
import com.xxxx.crm.utils.LoginUserUtil;
import com.xxxx.crm.vo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过cookie查到id
        int id = LoginUserUtil.releaseUserIdFromCookie(request);
        //判断
        if (id == 0 || null == userService.selectByPrimaryKey(id)){
            //不存在
            throw new NoLoginException();
        }

        //放行
        return true;
    }
}
*/
