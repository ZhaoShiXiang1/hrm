package com.xxxx.hrm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.PositionMapper;
import com.xxxx.hrm.query.DepartmentQuery;
import com.xxxx.hrm.query.PositionQuery;
import com.xxxx.hrm.utils.AssertUtil;
import com.xxxx.hrm.vo.Department;
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
        map.put("count",positionPageInfoPageInfo.getTotal());
        map.put("data",positionPageInfoPageInfo.getList());
        return map;
    }


    /*
     *添加职位
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addPosition(Position position) {
        AssertUtil.isTrue(StringUtils.isBlank(position.getName()), "添加职位名称不能为空");
        //AssertUtil.isTrue(StringUtils.isBlank(position.getId()),"添加职位名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(position.getDescription()), "职位描述不能为空");
        //AssertUtil.isTrue(null != positionMapper.insertPosition(position), "职位名已存在");
        // 校验参数
        checkPositionParams(position.getName(), position.getDescription());
        //设置默认值
        position.setCreatedTime(new Date());
        //执行添加操作
        AssertUtil.isTrue(positionMapper.insertPosition(position) < 1,"用户添加失败");
    }

    /*
    * 保存职位信息
    * */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updatePosition(Position position){
        //设置默认值
        position.setCreatedTime(new Date());
        return positionMapper.updatePosition(position);
    }

    /* public void updatePosition(Position position){
        //id     非空|存在
        AssertUtil.isTrue(null == position.getId() ,"职位不存在");
        Position dbposition = positionMapper.selectByPrimaryKey(position.getId());
        //用户名  非空 | 唯一
        AssertUtil.isTrue(position.getName() == null,"职位名称不能为空");
        // 名称唯一
        AssertUtil.isTrue(dbposition != null && position.getId() != dbposition.getId(),"用户名已存在");
        //设置默认值
        position.setCreatedTime(new Date());
        //执行修改操作
        AssertUtil.isTrue(positionMapper.updateByPrimaryKeySelective(position) < 1,"职位修改失败");
    }*/

    /*
     * 删除职位信息
     * */
    /*@Transactional(propagation = Propagation.REQUIRED)
    public void deletePosition(Integer[] ids){
        AssertUtil.isTrue(ids == null || ids.length < 1,"未选中删除数据");
        positionMapper.deletePosition(ids);
    }*/
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


}
