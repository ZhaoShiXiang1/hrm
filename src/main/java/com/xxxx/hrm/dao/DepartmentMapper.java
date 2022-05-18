package com.xxxx.hrm.dao;

import com.xxxx.hrm.base.BaseMapper;
import com.xxxx.hrm.query.DepartmentQuery;
import com.xxxx.hrm.vo.Department;

import java.util.List;
import java.util.Map;

//部门管理
public interface DepartmentMapper extends BaseMapper<Department,Integer> {

    //查询所有
     public List<Department> queryAll(DepartmentQuery departmentQuery);


    //根据id删除部门
    public Integer deletedeptById(Integer id);



    //查询下拉框的部门列表
    List<Map<String, Object>> queryAllDept();
    //根据部门名称查询数据
    Department queryByName(String name);
}