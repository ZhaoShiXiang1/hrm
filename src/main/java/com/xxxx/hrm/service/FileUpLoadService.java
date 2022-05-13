package com.xxxx.hrm.service;

import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.FileUpLoadMapper;
import com.xxxx.hrm.vo.FileUpLoad;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileUpLoadService extends BaseService<FileUpLoad,Integer> {


    @Resource
    private FileUpLoadMapper fileUpLoadMapper;
}

