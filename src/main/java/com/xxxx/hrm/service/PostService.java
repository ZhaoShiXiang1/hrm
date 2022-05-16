package com.xxxx.hrm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.PostMapper;
import com.xxxx.hrm.query.FileUpLoadQuery;
import com.xxxx.hrm.query.PostQuery;
import com.xxxx.hrm.utils.AssertUtil;
import com.xxxx.hrm.vo.FileUpLoad;
import com.xxxx.hrm.vo.Post;
import com.xxxx.hrm.vo.UserAuth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService extends BaseService<Post,Integer> {

    @Resource
    private PostMapper postMapper;

    //查询所有文件数据并展示
    public Map<Object, Object> querySalePostByParams(PostQuery query) {
        Map<Object, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        //使用方法查询数据，返回数组
        List<Post> post = postMapper.selectPostAll(query);
        //按照分页条件，格式化数据，展示第一页的数据
        PageInfo<Post> PageInfo = new PageInfo<>(post);
        //创建键值对
        map.put("code", 200);
        map.put("msg", "数据查询成功");
        map.put("size", PageInfo.getTotal());
        map.put("data", PageInfo.getList());
        return map;

    }

    //根据id删除文件数据
    public Integer deletePostsById(Integer id) {
        return postMapper.deletePostById(id);
    }
    //添加文件
    public Integer add(Post post){

        return  postMapper.insertSelective(post);
    }

    //修改公告
    public void updatePost(Post post) {
        //判断id是否存在
        System.out.println(post.getId());
        AssertUtil.isTrue(post.getId() == null, "数据异常，请重试");
        //校验参数
        checkParams(post.getTitle(), String.valueOf(post.getContent()));
        //设置时间默认值
        post.setCreatedTime(new Date());
        //通过现有的id查询修改之前的数据
        Post dbpost = postMapper.selectByPrimaryKey(post.getId());
        AssertUtil.isTrue(dbpost == null, "数据异常，请重试");
        //执行修改操作
        AssertUtil.isTrue(postMapper.updateByPrimaryKeySelective(post) < 1, "用户数据修改失败");
    }

    //校验参数是否正确
    private void checkParams(String title, String content) {

        AssertUtil.isTrue(StringUtils.isBlank(title), "公告名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(content), "公告内容不能为空!");

    }
}