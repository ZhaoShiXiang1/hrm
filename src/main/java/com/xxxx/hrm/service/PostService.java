package com.xxxx.hrm.service;

import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.PostMapper;
import com.xxxx.hrm.vo.Post;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PostService extends BaseService<Post,Integer> {

    @Resource
    private PostMapper postMapper;
}
