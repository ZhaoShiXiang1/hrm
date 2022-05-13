package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.service.DepartmentService;
import com.xxxx.hrm.vo.Department;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("dep")
public class DepartmentController extends BaseController {

    @Resource
    private DepartmentService departmentService;

    @RequestMapping("list")
    @ResponseBody
    public List<Department> queryAll(){
        return departmentService.queryAll();
    }
}
