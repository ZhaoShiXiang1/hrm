package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("post")
public class PostController extends BaseController {

    @Resource
    private PostService postService;


    //转发到公告页面
    @RequestMapping("index")
    public String index(){
        return "post_view";
    }

}
