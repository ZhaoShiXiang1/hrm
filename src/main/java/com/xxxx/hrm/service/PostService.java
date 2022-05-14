package com.xxxx.hrm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.PostMapper;
import com.xxxx.hrm.query.FileUpLoadQuery;
import com.xxxx.hrm.query.PostQuery;
import com.xxxx.hrm.vo.FileUpLoad;
import com.xxxx.hrm.vo.Post;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService extends BaseService<Post,Integer> {

    @Resource
    private  PostMapper postMapper;
    //查询所有文件数据并展示
    public  Map<Object,Object> querySalePostByParams(PostQuery query){
        Map<Object,Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(query.getPage(),query.getLimit());
        //使用方法查询数据，返回数组
        List<Post> post = postMapper.selectPostAll(query);
        //按照分页条件，格式化数据，展示第一页的数据
        PageInfo<Post> PageInfo = new PageInfo<>(post);
        //创建键值对
        map.put("code",200);
        map.put("msg","数据查询成功");
        map.put("count",PageInfo.getTotal());
        map.put("data",PageInfo.getList());
        return map;
    }
}
