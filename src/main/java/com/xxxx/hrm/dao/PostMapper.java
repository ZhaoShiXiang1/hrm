package com.xxxx.hrm.dao;

import com.xxxx.hrm.base.BaseMapper;
import com.xxxx.hrm.query.FileUpLoadQuery;
import com.xxxx.hrm.query.PostQuery;
import com.xxxx.hrm.vo.FileUpLoad;
import com.xxxx.hrm.vo.Post;

import java.util.List;

//公告管理
public interface PostMapper extends BaseMapper<Post,Integer> {
    //查询所有文件数据
    public List<Post> selectPostAll(PostQuery query);

    //根据id删除文件数据
    public Integer deletePostById(Integer id);
}