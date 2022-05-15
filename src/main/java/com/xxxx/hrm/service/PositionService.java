package com.xxxx.hrm.service;

import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.PositionMapper;
import com.xxxx.hrm.utils.AssertUtil;
import com.xxxx.hrm.vo.Position;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class PositionService extends BaseService<Position, Integer> {

    @Resource
    private PositionMapper positionMapper;

    /*
     *添加职位
     * */
    public void addPosition(Position position) {
        AssertUtil.isTrue(StringUtils.isBlank(position.getName()), "添加职位名称不能为空");
        //AssertUtil.isTrue(StringUtils.isBlank(position.getId()),"添加职位名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(position.getDescription()), "职位描述不能为空");
        AssertUtil.isTrue(null != positionMapper.insertPosition(position.getId()), "职位名已存在");
        // 校验参数
        checkUserParams(position.getName(), position.getDescription());
        //设置默认值
        position.setCreatedTime(new Date());
        //设置用户默认密码 123456(加密MD5)
        //user.setUserPwd(Md5Util.encode("123456"));
        //执行添加操作
        AssertUtil.isTrue(positionMapper.insertSelective(position) < 1,"用户添加失败");
        //执行添加操作，设置对应sql属性，主键返回到user对象中
        //AssertUtil.isTrue(positionMapper.insertHasKey(position) < 1, "用户添加失败");

    }


    /**
     * 校验用户添加数据
     *
     * @param
     * @param
     */
    private void checkUserParams(String name, String description) {
        AssertUtil.isTrue(StringUtils.isBlank(name),"职位名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(description),"手机号不能为空");
    }

}
