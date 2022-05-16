package com.xxxx.hrm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.DepartmentMapper;
import com.xxxx.hrm.query.DepartmentQuery;
import com.xxxx.hrm.utils.AssertUtil;
import com.xxxx.hrm.vo.Department;
import com.xxxx.hrm.vo.FileUpLoad;
import com.xxxx.hrm.vo.UserAuth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentService extends BaseService<Department,Integer> {

    @Resource
    private DepartmentMapper departmentMapper;



    //查询部门
    public Map<Object,Object> queryAll(DepartmentQuery query){
        Map<Object,Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(query.getPage(),query.getLimit());
        //使用方法查询数据，返回数组
        List<Department> files = departmentMapper.queryAll(query);
        //按照分页条件，格式化数据，展示第一页的数据
        PageInfo<Department> departmentPageInfoPageInfo = new PageInfo<>(files);
        //创建键值对
        map.put("code",200);
        map.put("msg","数据查询成功");
        map.put("size",departmentPageInfoPageInfo.getTotal());
        map.put("data",departmentPageInfoPageInfo.getList());
        return map;
    }


    //根据id删除部门
    public Integer deletedeptById(Integer id){
        return  departmentMapper.deletedeptById(id);
    }


    //添加文件
    public Integer add(Department department){
        return  departmentMapper.insertSelective(department);
    }


    //修改部门
    public Integer updateByPrimaryKeySelective(Department department){
        return  departmentMapper.updateByPrimaryKeySelective(department);
    }

}

