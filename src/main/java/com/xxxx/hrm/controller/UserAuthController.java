package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.base.ResultInfo;
import com.xxxx.hrm.service.UserAuthService;
import com.xxxx.hrm.vo.UserAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user_auth")
public class UserAuthController extends BaseController {

    @Resource
    private UserAuthService userAuthService;


    //用户登录功能
    @PostMapping("login")
    @ResponseBody
    public ResultInfo login(String username, String password, HttpServletRequest request){
        UserAuth userAuth = userAuthService.login(username, password);
        request.getSession().setAttribute("user",userAuth);
        //如果登录成功，将当前登录的对象传入request域对象，以供前台提取数据使用
        //request.setAttribute("user",userAuth);
        return success();
    }
}
