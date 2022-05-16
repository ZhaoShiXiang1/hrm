package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.base.ResultInfo;
import com.xxxx.hrm.query.EmployeeQuery;
import com.xxxx.hrm.query.FileUpLoadQuery;
import com.xxxx.hrm.service.DepartmentService;
import com.xxxx.hrm.service.EmployeeService;
import com.xxxx.hrm.service.PositionService;
import com.xxxx.hrm.vo.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("emp")
public class EmployeeController extends BaseController{

    @Resource
    private EmployeeService employeeService;

    @Resource
    private PositionService positionService;

    @Resource
    private DepartmentService departmentService;



    //跳转到员工管理页面
    @RequestMapping("index")
    public String auth_view() {
        return "employee_view";
    }

    //跳转到员工添加或修改页面
    @RequestMapping("toAddUpdate")
    public String employee(Integer id, HttpServletRequest request) {
        if (null != id){
            //修改操作
            //id为当前员工的id号
            Employee employee = employeeService.selectByPrimaryKey(id);
            request.setAttribute("employee",employee);
        }
        return "employee-insert";
    }


    //添加员工
    @RequestMapping("addEmp")
    @ResponseBody
    public ResultInfo addEmp(Employee employee){
        System.out.println(employee);
        //调用service层添加员工
        employeeService.addEmp(employee);
        return success();
    }

    //修改员工信息
    @RequestMapping("updateEmp")
    @ResponseBody
    public ResultInfo updateEmp(Employee employee){
        System.out.println(employee);
        employeeService.updateEmp(employee);
        return success();
    }

    //删除员工
    @RequestMapping("deleteEmp")
    @ResponseBody
    public ResultInfo deleteEmp(Integer id){
        employeeService.deleteEmp(id);
        return success();
    }

    //多条件查询
    @RequestMapping("/list")
    @ResponseBody
    public Map<Object,Object> selectAllEmp(EmployeeQuery query){
        return employeeService.selectAllEmp(query);
    }


    //下拉框的职位列表
    @PostMapping("queryAllPosition")
    @ResponseBody
    public List<Map<String, Object>> queryAllPosition(){
        return positionService.queryAllPosition();
    }

    //下拉框的部门列表
    @PostMapping("queryAllDept")
    @ResponseBody
    public List<Map<String, Object>> queryAllDept(){
        return departmentService.queryAllDept();
    }
}
