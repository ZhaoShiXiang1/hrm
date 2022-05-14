package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.query.FileUpLoadQuery;
import com.xxxx.hrm.query.PostQuery;
import com.xxxx.hrm.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

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
    /**
     * 页面访问后台查询全部数据展示到前台
     * */
    @RequestMapping("/list")
    @ResponseBody
    public Map<Object,Object> querySalePostByParams (PostQuery query){
        return postService. querySalePostByParams(query);
    }
}
