package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.base.ResultInfo;
import com.xxxx.hrm.query.FileUpLoadQuery;
import com.xxxx.hrm.query.PostQuery;
import com.xxxx.hrm.service.PostService;
import com.xxxx.hrm.vo.FileUpLoad;
import com.xxxx.hrm.vo.Post;
import com.xxxx.hrm.vo.UserAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("posts")
public class PostController extends BaseController {

    @Resource
    private PostService postService;


    //转发到公告页面
    @RequestMapping("/index")
    public String index(){
        return "post_view";
    }
    /**
     * 页面访问后台查询全部数据展示到前台
     * */
    @GetMapping("/list")
    @ResponseBody
    public Map<Object,Object> querySalePostByParams (PostQuery query){
        return postService. querySalePostByParams(query);
    }

    /**实现文件删除
     * */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo delete(Integer id){
        postService.deletePostsById(id);
        return success("删除成功");
    }

    /**实现文件添加
     * */
    @RequestMapping("/add")
    @ResponseBody
    public ResultInfo add(Post post){
        postService.add(post);
        return success("文件添加成功") ;
    }


    //修改用户
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Post post) {
        postService.updatePost(post);
        return success();
    }

    /*
     * 跳转用户修改的页面
     */
    @RequestMapping("toUpdatePage")
    public String toUpdatePage(Integer id, HttpServletRequest request) {
        request.setAttribute("post", postService.selectByPrimaryKey(id));
        return "post-update";
    }
}
