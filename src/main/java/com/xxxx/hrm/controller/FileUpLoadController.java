package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.base.ResultInfo;
import com.xxxx.hrm.query.FileUpLoadQuery;
import com.xxxx.hrm.service.FileUpLoadService;
import com.xxxx.hrm.utils.AssertUtil;
import com.xxxx.hrm.vo.FileUpLoad;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("files")
public class FileUpLoadController extends BaseController {
    //设置文件根路径
    @Value("${file.path}")
    private String filePath;

    @Resource
    private FileUpLoadService fileUpLoadService;

    /**
     * 触发前台页面跳转至下载中心
     * */
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

    /**
     * 实现数据库信息删除以及文件删除
     * */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo delete(Integer id){
        fileUpLoadService.deleteFilesById(id);
        return success("删除成功");
    }

    /**
     * 实现文件添加
     * */
    @RequestMapping("/add")
    @ResponseBody
    public ResultInfo add(FileUpLoad file){
        fileUpLoadService.add(file);
        return success("文件添加成功");
    }

    // 文件上传和更新(传入一个文件（原来的路径和文件名），复制到本地，传出一个结果：文件名，状态码 msg )
    @PostMapping ("/upload")
    @ResponseBody
    public Map<Object,Object> uploadFile(MultipartFile file,Integer id) throws IOException {
        return fileUpLoadService.uploadFile(file,id);
    }


    //更新数据库内容信息
    @PutMapping("/updateTo")
    @ResponseBody
    public Map<Object,Object> updateTo(@RequestBody FileUpLoad file){
        file.setPath(filePath+file.getPath());
        AssertUtil.isTrue(fileUpLoadService.updateByPrimaryKeySelective(file)<1,"文件更新异常");
        Map<Object, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","数据查询成功");
        return map;
    }



    //实现文件下载(文件名/数组长度/IO流)
    @RequestMapping("/downloadFile")
    public ResponseEntity<InputStreamResource> downloadFile(Integer id) throws IOException {
        return   fileUpLoadService.downloadFile(id);
    }
}
