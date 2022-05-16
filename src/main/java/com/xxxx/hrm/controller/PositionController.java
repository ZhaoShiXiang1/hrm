package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.base.ResultInfo;
import com.xxxx.hrm.query.DepartmentQuery;
import com.xxxx.hrm.query.PositionQuery;
import com.xxxx.hrm.service.PositionService;
import com.xxxx.hrm.vo.Position;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("positions")
public class PositionController extends BaseController {

    @Resource
    private PositionService positionService;

    //查询所有职位信息
    @RequestMapping("list")
    @ResponseBody
    public Map<Object,Object> queryAll(PositionQuery query){
        return positionService.queryAllPositions(query);
    }

    //添加职位名称
    @RequestMapping ("add")
    @ResponseBody
    public ResultInfo addPosition(Position position){
        positionService.addPosition(position);
        return success("职位添加成功");
    }

     //修改职位信息
     @RequestMapping("update")
     @ResponseBody
     public ResultInfo updatePosition(@RequestBody Position position){
         System.out.println(position);
         positionService.updatePosition(position);
         return success("职位修改成功");
     }

    //删除职位信息
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteBatchs(Integer id){
        positionService.deletePosition(id);
        return success("删除成功");
    }

    @RequestMapping("index")
    public String toUpdateAddPage(){
        return "position_view";
    }

}
