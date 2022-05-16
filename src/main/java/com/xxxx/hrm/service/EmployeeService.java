package com.xxxx.hrm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.EmployeeMapper;
import com.xxxx.hrm.query.EmployeeQuery;
import com.xxxx.hrm.utils.AssertUtil;
import com.xxxx.hrm.vo.Department;
import com.xxxx.hrm.vo.Employee;
import com.xxxx.hrm.vo.FileUpLoad;
import com.xxxx.hrm.vo.Position;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService extends BaseService<Employee,Integer> {

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private PositionService positionService;


    //多条件查询
    public Map<Object, Object> selectAllEmp(EmployeeQuery query) {
        Map<Object,Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(query.getPage(),query.getLimit());
        //使用方法查询数据，返回数组
        List<Employee> employees = employeeMapper.selectAllEmp(query);
        //添加部门职位名称
        for (Employee employee : employees) {
            //获取部门id
            Integer deptId = employee.getDeptId();
            //根据部门id查找部门
            Department department = departmentService.selectByPrimaryKey(deptId);
            //赋值
            employee.setDeptName(department.getName());

            //获取职位id
            Integer positionId = employee.getPositionId();
            //根据职位id查找职位
            Position position = positionService.selectByPrimaryKey(positionId);
            //赋值
            employee.setPositionName(position.getName());
        }
        //按照分页条件，格式化数据，展示第一页的数据
        PageInfo<Employee> employeePageInfo = new PageInfo<>(employees);
        //创建键值对
        map.put("code",200);
        map.put("msg","数据查询成功");
        map.put("size",employeePageInfo.getTotal());
        map.put("data",employeePageInfo.getList());
        return map;
    }

    //添加员工
    public void addEmp(Employee employee) {
        //校验参数
        AssertUtil.isTrue(null == employee,"数据异常，请重试");
        AssertUtil.isTrue(StringUtils.isBlank(employee.getName()),"员工姓名不能为空");
        AssertUtil.isTrue(null == employee.getSex(),"员工性别不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(employee.getPhone()),"电话号码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(employee.getEmail()),"邮箱不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(employee.getEducation()),"学历不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(employee.getIdcard()),"身份证不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(employee.getAddress()),"地址不能为空");
        AssertUtil.isTrue(null == employee.getPositionId(),"职位不能为空");
        AssertUtil.isTrue(null == employee.getDeptId(),"部门不能为空");

        //设置默认值
        employee.setCreatedTime(new Date());

        //添加用户
        AssertUtil.isTrue(employeeMapper.addEmp(employee) < 1 ,"添加用户失败");
    }


    //修改用户信息
    public void updateEmp(Employee employee) {
        //校验参数
        AssertUtil.isTrue(null == employee,"数据异常，请重试");
        AssertUtil.isTrue(null == employee.getId(),"员工id不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(employee.getName()),"员工姓名不能为空");
        AssertUtil.isTrue(null == employee.getSex(),"员工性别不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(employee.getPhone()),"电话号码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(employee.getEmail()),"邮箱不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(employee.getEducation()),"学历不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(employee.getIdcard()),"身份证不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(employee.getAddress()),"地址不能为空");
        AssertUtil.isTrue(null == employee.getPositionId(),"职位不能为空");
        AssertUtil.isTrue(null == employee.getDeptId(),"部门不能为空");

        //查询此用户是否存在
        AssertUtil.isTrue(null == employeeMapper.selectByPrimaryKey(employee.getId()),"要修改的员工信息不存在");


        //修改员工
        AssertUtil.isTrue(employeeMapper.updateEmp(employee) < 1 ,"修改用户失败");
    }

    //根据id删除员工
    public void deleteEmp(Integer id) {
        //校验员工
        AssertUtil.isTrue( null == id ,"id不能为空");
        AssertUtil.isTrue( null == employeeMapper.selectByPrimaryKey(id),"要删除的员工信息不存在");

        //删除员工
        AssertUtil.isTrue(employeeMapper.deleteByPrimaryKey(id) < 1 ,"删除失败");
    }
}
