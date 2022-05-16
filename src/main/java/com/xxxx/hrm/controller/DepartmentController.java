package com.xxxx.hrm.controller;


import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.base.ResultInfo;
import com.xxxx.hrm.query.DepartmentQuery;
import com.xxxx.hrm.service.DepartmentService;
import com.xxxx.hrm.vo.Department;
import com.xxxx.hrm.vo.FileUpLoad;
import com.xxxx.hrm.vo.UserAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("dep")
public class DepartmentController extends BaseController {

    @Resource
    private DepartmentService departmentService;


    //跳转页面
    @RequestMapping("management")
    public String register(){
        return "department_view";
    }


    /**
     * 查询全部部门
     * @param query
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<Object,Object> queryAll(DepartmentQuery query){
        return departmentService.queryAll(query);
    }


    /**
     * 根据id删除部门
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo deleteById(Integer id){
        departmentService.deletedeptById(id);
        return success("删除成功");
    }


    /**
     * 添加部门
     * @param department
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultInfo add(Department department){
        departmentService.add(department);
        return success("文件添加成功") ;
    }


    /**
     * 修改部门
     * @param department
     * @return
     */
    @PutMapping("/update")
    @ResponseBody
    public Map<Object,Object> update(@RequestBody Department department){
        Integer num= departmentService.updateByPrimaryKeySelective(department);
        Map<Object, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","数据查询成功");
        return map;
    }

}
