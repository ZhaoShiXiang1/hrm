package com.xxxx.hrm.service;

import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.DepartmentMapper;
import com.xxxx.hrm.vo.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentService extends BaseService<Department,Integer> {

    @Resource
    private DepartmentMapper departmentMapper;

    public List<Department> queryAll(){
        return departmentMapper.queryAll();
    }

}

