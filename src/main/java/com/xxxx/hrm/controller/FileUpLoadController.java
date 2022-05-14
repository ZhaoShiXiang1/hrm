package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.base.ResultInfo;
import com.xxxx.hrm.query.FileUpLoadQuery;
import com.xxxx.hrm.service.FileUpLoadService;
import com.xxxx.hrm.vo.FileUpLoad;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("files")
public class FileUpLoadController extends BaseController {

    //设置文件根路径
    @Value("${file.path}")
    private String filePath;

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
    /**实现文件添加
     * */
    @RequestMapping("/add")
    @ResponseBody
    public ResultInfo add(FileUpLoad file){
        fileUpLoadService.add(file);
        return success("文件添加成功") ;
    }

    // 文件上传(传入一个文件（原来的路径和文件名），复制到本地，传出一个结果：文件名，状态码 msg )
    @RequestMapping (value="upload" ,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public Map<Object,Object> uploadFile(MultipartFile file) throws IOException {
        //获取文件名称
        String name = file.getOriginalFilename();

        //将内容转换成数组写出
        byte[] arr = file.getBytes();
        FileOutputStream out = new FileOutputStream(new File(filePath + name));
        out.write(arr);
        out.flush();
        out.close();
        //返回前台数据
        Map<Object, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","数据查询成功");
        map.put("data",name);//获取名称返回名称
        System.out.println("-----------------------");
        return map;
    }

}
