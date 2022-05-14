package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.base.ResultInfo;
import com.xxxx.hrm.query.FileUpLoadQuery;
import com.xxxx.hrm.service.FileUpLoadService;
import com.xxxx.hrm.vo.FileUpLoad;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("files")
public class FileUpLoadController extends BaseController {

    @Resource
    private FileUpLoadService fileUpLoadService;
     //触发前台页面跳转至下载中心
    @RequestMapping("/download")
    public String index(){
        return "download_view";
    }

/**
 * 页面访问后台查询全部数据展示到前台
 * */
    @RequestMapping("/list")
    @ResponseBody
    public Map<Object,Object> selectFilesAll(FileUpLoadQuery query){
        return fileUpLoadService.selectFilesAll(query);
    }

    /**实现文件删除
     * */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo delete(Integer id){
        fileUpLoadService.deleteFilesById(id);
        return success("删除成功");
    }
}
