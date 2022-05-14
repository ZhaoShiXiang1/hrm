package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.base.ResultInfo;
import com.xxxx.hrm.query.FileUpLoadQuery;
import com.xxxx.hrm.service.FileUpLoadService;
import com.xxxx.hrm.vo.FileUpLoad;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping ("/upload")
    @ResponseBody
    public Map<Object,Object> uploadFile(MultipartFile file) throws IOException {
        //获取文件名称
        String name = file.getOriginalFilename();

        //将内容转换成数组写出
        byte[] arr = file.getBytes();
        FileOutputStream out = new FileOutputStream(new File(filePath+name));
        System.out.println(new File(filePath+name));
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
    //更新文件
    @PutMapping("/updateTo")
    @ResponseBody
    public Map<Object,Object> updateTo(@RequestBody FileUpLoad file){
        //根据ID查询数据
//        FileUpLoad file1 = fileUpLoadService.selectByPrimaryKey(file.getId());
        System.out.println(filePath);
        file.setPath(filePath+file.getPath());
        System.out.println(file.getPath());
       Integer num= fileUpLoadService.updateByPrimaryKeySelective(file);
        System.out.println("num"+num);
        System.out.println("数据更新成功");
        Map<Object, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","数据查询成功");
        return map;
    }

    //实现文件下载(文件名/数组长度/IO流)
    @RequestMapping("/downloadFile")
    public ResponseEntity<InputStreamResource> downloadFile(Integer id) throws IOException {
        //文件绝对路径
        String filepath = fileUpLoadService.selectByPrimaryKey(id).getPath();
        //根据绝对路径创建一个文件对象
        FileSystemResource file = new FileSystemResource(filepath);
        //将数据封装到请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        //需要一个文件名
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));//获取文件名加后缀
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)//加入该对象
                .contentLength(file.contentLength())//内容字符数组线长度
                .contentType(MediaType.parseMediaType("application/octet-stream"))//字符
                .body(new InputStreamResource(file.getInputStream()));//放入一个流I/O

    }

}
