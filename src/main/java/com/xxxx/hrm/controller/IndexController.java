package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//其他页面
@Controller
public class IndexController extends BaseController {

    //跳转登录页面
    @RequestMapping("login")
    public String index(){
        return "user/login";
    }

    //登录成功跳转到后台界面
    @RequestMapping("index")
    public String register(){
        return "index_view";
    }


    @RequestMapping("auth_view")
    public String auth_view(){
        return "auth_view";
    }

    @RequestMapping("index_view")
    public String index_view(){
        return "index_view";
    }

}
