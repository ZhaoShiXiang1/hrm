package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.base.ResultInfo;
import com.xxxx.hrm.query.UserAuthQuery;
import com.xxxx.hrm.service.UserAuthService;
import com.xxxx.hrm.utils.AssertUtil;
import com.xxxx.hrm.vo.UserAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.Map;

@Controller
@RequestMapping("user_auth")
public class UserAuthController extends BaseController {

    @Resource
    private UserAuthService userAuthService;


    //用户登录功能
    @PostMapping("login")
    @ResponseBody
    public ResultInfo login(String username, String password, HttpServletRequest request) {
        UserAuth userAuth = userAuthService.login(username, password);
        request.getSession().setAttribute("user", userAuth);
        //如果登录成功，将当前登录的对象传入request域对象，以供前台提取数据使用
        //request.setAttribute("user",userAuth);
        return success();
    }


    //账户注销退出功能
    @RequestMapping("logout")
    @ResponseBody
    public ResultInfo logout(HttpServletRequest request) {
        //判断当前session是否存在用户
        Object user = request.getSession().getAttribute("user");
        if (null != user) {
            //存在
            //清空当前的session中的user
            request.getSession().removeAttribute("user");
        }
        return success();
    }

    /*
     * 多条件分页查询数据
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<Object, Object> queryAllUserAuths(UserAuthQuery query) {
        return userAuthService.queryAllUserAuths(query);
    }

    //点击授权管理，页面展示
    @RequestMapping("auth")
    public String auth_view() {
        return "auth_view";
    }

    //新增用户
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo save(UserAuth userAuth) {
        userAuthService.addUserAuth(userAuth);
        return success();
    }

    /*
     * 跳转用户添加的页面
     */
    @RequestMapping("toAddPage")
    public String toAddPage() {
        return "auth-insert";
    }

    //修改用户
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(UserAuth userAuth) {
        userAuthService.updateUserAuth(userAuth);
        return success();
    }

    /*
     * 跳转用户修改的页面
     */
    @RequestMapping("toUpdatePage")
    public String toUpdatePage(Integer id, HttpServletRequest request) {
        request.setAttribute("userAuth", userAuthService.selectByPrimaryKey(id));
        return "auth-edit";
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo userDelete(Integer id){
        userAuthService.delete(id);
        return success("资源删除成功");
    }
}
