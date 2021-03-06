package com.xxxx.hrm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.DepartmentMapper;
import com.xxxx.hrm.dao.PositionMapper;
import com.xxxx.hrm.query.PositionQuery;
import com.xxxx.hrm.utils.AssertUtil;
import com.xxxx.hrm.vo.Position;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PositionService extends BaseService<Position, Integer> {

    @Resource
    private PositionMapper positionMapper;
    @Resource
    private DepartmentMapper departmentMapper;

    /*
     *添加职位
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addPosition(Position position) {
        AssertUtil.isTrue(StringUtils.isBlank(position.getName()), "添加职位名称不能为空");
        //AssertUtil.isTrue(StringUtils.isBlank(position.getId()),"添加职位名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(position.getDescription()), "职位描述不能为空");
        AssertUtil.isTrue(null != positionMapper.selectPositionByName(position.getName()), "职位名已存在");
        // 校验参数
        checkPositionParams(position.getName(), position.getDescription());
        //设置默认值
        position.setCreatedTime(new Date());
        //执行添加操作
        AssertUtil.isTrue(positionMapper.insertPosition(position) < 1,"用户添加失败");
    }


    //查询全部职位
    public Map<Object,Object> queryAllPositions(PositionQuery query){
        Map<Object,Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(query.getPage(),query.getLimit());
        //使用方法查询数据，返回数组
        List<Position> files = positionMapper.queryAllPositions(query);
        //按照分页条件，格式化数据，展示第一页的数据
        PageInfo<Position> positionPageInfoPageInfo = new PageInfo<>(files);
        //创建键值对
        map.put("code",200);
        map.put("msg","数据查询成功");
        map.put("size",positionPageInfoPageInfo.getTotal());
        map.put("data",positionPageInfoPageInfo.getList());
        return map;
    }
    /*
     * 保存职位信息
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updatePosition(Position position){
        //设置默认值
        position.setCreatedTime(new Date());
        Position dbpositon = positionMapper.selectPositionByName(position.getName());
        AssertUtil.isTrue(null!=dbpositon&&position.getId() != dbpositon.getId(),"职位名称已存在");
        System.out.println("测试保存职位");
        System.out.println(position.getId());
        return positionMapper.updatePosition(position);
    }

    /*
     * 删除职位信息
     * */
    public Integer deletePosition(Integer id){
        return  positionMapper.deletePosition(id);
    }

    /**
     * 校验用户添加数据
     *
     * @param
     * @param
     */
    private void checkPositionParams(String name, String description) {
        AssertUtil.isTrue(StringUtils.isBlank(name),"职位名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(description),"描述不能为空");
    }

    //查询下拉框的职位列表
    public List<Map<String, Object>> queryAllPosition() {
        return positionMapper.queryAllPosition();
    }
}
