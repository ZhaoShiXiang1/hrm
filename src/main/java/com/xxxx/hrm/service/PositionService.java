package com.xxxx.hrm.service;

import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.PositionMapper;
import com.xxxx.hrm.vo.Position;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PositionService extends BaseService<Position,Integer> {

    @Resource
    private PositionMapper positionMapper;

    public void addPosition(Position position){

    }
}
