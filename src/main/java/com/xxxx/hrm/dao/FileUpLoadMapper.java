package com.xxxx.hrm.dao;

import com.xxxx.hrm.base.BaseMapper;
import com.xxxx.hrm.query.FileUpLoadQuery;
import com.xxxx.hrm.vo.FileUpLoad;

import java.util.List;

//文件上传
public interface FileUpLoadMapper extends BaseMapper<FileUpLoad,Integer> {
     //查询所有文件数据
    List<FileUpLoad> selectFilesAll(FileUpLoadQuery query);

    //根据id删除文件数据
    Integer deleteFilesById(Integer id);
}