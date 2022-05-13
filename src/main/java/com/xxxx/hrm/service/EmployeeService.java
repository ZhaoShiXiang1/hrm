package com.xxxx.hrm.service;

import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.EmployeeMapper;
import com.xxxx.hrm.vo.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmployeeService extends BaseService<Employee,Integer> {

    @Resource
    private EmployeeMapper employeeMapper;

}
