package com.xxxx.hrm.dao;

import com.xxxx.hrm.base.BaseMapper;
import com.xxxx.hrm.vo.Position;

import java.util.List;
import java.util.Map;

//职位管理
public interface PositionMapper extends BaseMapper<Position,Integer> {

    //添加职位
    Integer insertPosition(Integer id);

    //查询下拉框的职位
    List<Map<String, Object>> queryAllPosition();
}