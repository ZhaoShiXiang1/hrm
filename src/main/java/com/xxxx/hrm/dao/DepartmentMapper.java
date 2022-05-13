package com.xxxx.hrm.dao;

import com.xxxx.hrm.base.BaseMapper;
import com.xxxx.hrm.vo.Department;

import java.util.List;

//部门管理
public interface DepartmentMapper extends BaseMapper<Department,Integer> {

    //查询所有
    List<Department> queryAll();

}