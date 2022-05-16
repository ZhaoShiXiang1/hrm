
package com.xxxx.hrm;

import com.alibaba.fastjson.JSON;
import com.xxxx.hrm.base.ResultInfo;
import com.xxxx.hrm.exceptions.NoLoginException;
import com.xxxx.hrm.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
/*
*
     * 控制层的方法返回的内容两种情况
     * 1. 视图:视图异常
     * 2. Json:方法执行错误 返回错误json信息
     */

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //设置默认的异常处理
        ModelAndView mv = new ModelAndView();
        //先处理未登录异常
        if (ex instanceof NoLoginException){
            NoLoginException nx = (NoLoginException)ex;
            //mv.setViewName("index"); 错误 默认访问静态资源  但是ftl需要通过接口进行访问
            mv.setViewName("redirect:login");
            return mv;
        }

        mv.setViewName("error");
        mv.addObject("code", 300);
        mv.addObject("msg", "数据异常，请重试");

        //判断目标方法返回的是视图还是json数据
        if (handler instanceof HandlerMethod) {
            //转换为Controller方法对象
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            //获取注解对象
            ResponseBody annotation = handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);

            //判断是否存在此注解
            if (annotation == null){
                //不存在，返回视图
                if (ex instanceof ParamsException){
                    ParamsException px = (ParamsException)ex;
                    mv.addObject("code",px.getCode());
                    mv.addObject("msg",px.getMsg());
                }
                return mv;
            }else {
                //存在，返回json
                ResultInfo resultInfo = new ResultInfo();
                //默认异常
                resultInfo.setCode(500);
                resultInfo.setMsg("系统异常，请重试");
                //判断异常
                if (ex instanceof ParamsException){
                    ParamsException px = (ParamsException)ex;
                    resultInfo.setCode(px.getCode());
                    resultInfo.setMsg(px.getMsg());
                }
                //将resultInfo转换为json数据发送给前台
                //设置编码集
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer = null;
                try {
                    //获取流
                    writer = response.getWriter();
                    //转换为json
                    writer.write(JSON.toJSONString(resultInfo));
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (writer != null){
                        writer.close();
                    }
                }
                return null;
            }
        }
        return mv;
    }
}
