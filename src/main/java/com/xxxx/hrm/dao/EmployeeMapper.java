package com.xxxx.hrm.dao;

import com.xxxx.hrm.base.BaseMapper;
import com.xxxx.hrm.query.EmployeeQuery;
import com.xxxx.hrm.vo.Employee;

import java.util.List;

//员工管理
public interface EmployeeMapper extends BaseMapper<Employee,Integer> {


    //多条件查询
    List<Employee> selectAllEmp(EmployeeQuery query);

    //添加用户
    Integer addEmp(Employee employee);


    //修改用户
    Integer updateEmp(Employee employee);
}