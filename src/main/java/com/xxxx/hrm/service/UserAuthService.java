package com.xxxx.hrm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.UserAuthMapper;
import com.xxxx.hrm.query.UserAuthQuery;
import com.xxxx.hrm.utils.AssertUtil;
import com.xxxx.hrm.vo.UserAuth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserAuthService extends BaseService<UserAuth, Integer> {

    @Resource
    private UserAuthMapper userAuthMapper;


    //用户登录功能
    public UserAuth login(String username, String password) {
        //参数校验
        AssertUtil.isTrue(StringUtils.isBlank(username), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(password), "密码不能为空");
        //调用dao层判断数据库中是否有此用户
        UserAuth dbUserAuth = userAuthMapper.queryUserByName(username);
        AssertUtil.isTrue(null == dbUserAuth, "用户不存在");
        //判断密码是否与数据库中密码相同
        AssertUtil.isTrue(!password.equals(dbUserAuth.getPassword()), "密码错误");

        //登陆成功，将userAuth对象返回给Controller层
        return dbUserAuth;
    }

    /*
     * 多条件查询数据
     */
    public Map<Object, Object> queryAllUserAuths(UserAuthQuery query) {
        Map<Object, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        //使用方法查询数据，返回数组
        List<UserAuth> userAuths = userAuthMapper.queryAllUserAuths(query);
        //按照分页条件，格式化数据
        PageInfo<UserAuth> userAuthsPageInfo = new PageInfo<>(userAuths);
        map.put("code", 0);
        map.put("msg", "");
        map.put("size", userAuthsPageInfo.getTotal());
        map.put("data", userAuthsPageInfo.getList());
        return map;
    }


    /*
     * 添加用户
     */
    public void addUserAuth(UserAuth userAuth) {
        //校验参数
        checkParams(userAuth.getUsername(), String.valueOf(userAuth.getId()));
        //校验当前角色是否已存在
        UserAuth temp = userAuthMapper.queryUserByName(userAuth.getUsername());
        AssertUtil.isTrue(null !=temp,"该角色已存在!");
        //设置时间默认值
        userAuth.setCreatedTime(new Date());
        //执行添加操作，判断是否添加成功
        AssertUtil.isTrue(userAuthMapper.insertSelective(userAuth) < 1, "数据添加失败");
    }


    //校验参数是否正确
    private void checkParams(String username, String password) {

        AssertUtil.isTrue(StringUtils.isBlank(username), "用户名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(password), "密码不能为空!");

    }
    //修改用户
    public void updateUserAuth(UserAuth userAuth) {
        //判断id是否存在
        AssertUtil.isTrue(userAuth.getId() == null, "数据异常，请重试");
        //校验参数
        checkParams(userAuth.getUsername(), String.valueOf(userAuth.getId()));
        //校验当前角色是否已存在
        UserAuth temp = userAuthMapper.queryUserByName(userAuth.getUsername());
        AssertUtil.isTrue(null !=temp,"该角色已存在!");
        //设置时间默认值
        userAuth.setCreatedTime(new Date());
        //通过现有的id查询修改之前的数据
        UserAuth dbuserAuth = userAuthMapper.selectByPrimaryKey(userAuth.getId());
        AssertUtil.isTrue(dbuserAuth == null, "数据异常，请重试");
        //执行修改操作
        AssertUtil.isTrue(userAuthMapper.updateByPrimaryKeySelective(userAuth) < 1, "用户数据修改失败");
    }

    //删除
    public void delete(Integer id) {
        UserAuth userAuth = userAuthMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(id == null || null == userAuth,"待删除计划项不存在");
        AssertUtil.isTrue(userAuthMapper.deleteByPrimaryKey(id) < 1,"计划项删除失败");
    }
}
