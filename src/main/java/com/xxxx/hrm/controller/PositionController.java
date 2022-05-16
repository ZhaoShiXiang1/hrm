package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.base.ResultInfo;
import com.xxxx.hrm.service.PositionService;
import com.xxxx.hrm.vo.Position;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("position")
public class PositionController extends BaseController {

    @Resource
    private PositionService positionService;

    //添加职位名称
    @RequestMapping ("add")
    @ResponseBody
    public ResultInfo addPosition(Position position){
        positionService.addPosition(position);
        return success("职位添加成功");
    }
}
