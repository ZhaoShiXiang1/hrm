package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//其他页面
@Controller
public class IndexController extends BaseController {

    @RequestMapping("index")
    public String index(){
        return "user/login";
    }

    @RequestMapping("register")
    public String register(){
        return "user/register";
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
