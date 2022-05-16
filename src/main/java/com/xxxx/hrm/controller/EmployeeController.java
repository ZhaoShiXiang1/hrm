package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("emp")
public class EmployeeController extends BaseController{

    @Resource
    private EmployeeService employeeService;


    //跳转到员工管理页面
    @RequestMapping("index")
    public String auth_view() {
        return "employee_view";
    }

    //跳转到员工添加页面
    @RequestMapping("toAddUpdate")
    public String employee(Integer id) {
        return "employee-insert";
    }
}
