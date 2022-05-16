package com.xxxx.hrm.dao;

import com.xxxx.hrm.base.BaseMapper;
import com.xxxx.hrm.query.DepartmentQuery;
import com.xxxx.hrm.query.PositionQuery;
import com.xxxx.hrm.vo.Department;
import com.xxxx.hrm.vo.Position;

import java.util.List;

//职位管理
public interface PositionMapper extends BaseMapper<Position,Integer> {

    //添加职位
    Integer insertPosition(Position position);
    //保存职位
    Integer updatePosition(Position position);
    //删除职位
    Integer deletePosition(Integer id);
    //查询所有
    public List<Position> queryAllPositions(PositionQuery positionQuery);
}