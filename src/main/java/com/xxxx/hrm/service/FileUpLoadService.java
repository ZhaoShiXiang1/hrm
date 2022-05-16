package com.xxxx.hrm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.hrm.base.BaseService;
import com.xxxx.hrm.dao.FileUpLoadMapper;
import com.xxxx.hrm.query.FileUpLoadQuery;
import com.xxxx.hrm.utils.AssertUtil;
import com.xxxx.hrm.vo.FileUpLoad;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileUpLoadService extends BaseService<FileUpLoad,Integer> {
    @Resource
    private FileUpLoadMapper fileUpLoadMapper;
    @Resource
    private FileUpLoadService fileUpLoadService;
    //设置文件根路径
    @Value("${file.path}")
    private String filePath;

    //查询所有文件数据并展示
    public Map<Object,Object> selectFilesAll(FileUpLoadQuery query){
        Map<Object,Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(query.getPage(),query.getLimit());
        //使用方法查询数据，返回数组
        List<FileUpLoad> files = fileUpLoadMapper.selectFilesAll(query);
        //按照分页条件，格式化数据，展示第一页的数据
        PageInfo<FileUpLoad> filePageInfo = new PageInfo<>(files);
        //创建键值对
        map.put("code",200);
        map.put("msg","数据查询成功");
        map.put("size",filePageInfo.getTotal());
        map.put("data",filePageInfo.getList());
        return map;
    }

    //根据id删除文件数据
    public Integer deleteFilesById(Integer id){
        //判断id非空
        AssertUtil.isTrue(null==id||null==fileUpLoadService.selectByPrimaryKey(id),"数据库文件信息不存在");
        //删除存储在后台文件夹中的文件
        new File("" + (fileUpLoadService.selectByPrimaryKey(id).getPath())).delete();
        return  fileUpLoadMapper.deleteFilesById(id);
    }

    //添加文件
    public Integer add(FileUpLoad file){
        //非空判断（标题/描述/文件存储路径）
        AssertUtil.isTrue(null==file.getTitle()||null==file.getDescription()||null==file.getPath(),"信息填写不完整，请重新填写");
        return  fileUpLoadMapper.insertSelective(file);
    }


    //文件夹中文件的上传和更新
    public Map<Object,Object> uploadFile(MultipartFile file, Integer rowId) throws IOException {//MultipartFile类实现文件下载
        //删除存储在数据库中的文件
        if (null!=rowId && null!=fileUpLoadService.selectByPrimaryKey(rowId)){
            new File("" + (fileUpLoadService.selectByPrimaryKey(rowId).getPath())).delete();
        }
        //创建存储文件的文件夹
        String str=filePath.substring(0,filePath.length()-1);
        new File(str).mkdir();

        //重命名文件名称，防止文件名重复
        String name = file.getOriginalFilename();//获取原来的文件名和后缀
        int num = name.lastIndexOf(".");//获取后缀点所在的索引
        String path = name.substring(num);//获取后缀类型《.xxx》
        name = System.currentTimeMillis()+path;


        //将内容转换成数组写出
        byte[] arr = file.getBytes();
        FileOutputStream out = new FileOutputStream(new File(filePath+name));//输出流
        out.write(arr);//输出数据
        out.flush();
        out.close();
        //返回前台数据
        Map<Object, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","数据查询成功");
        map.put("data",name);//获取名称返回名称
        return map;
    }

    //更新文件
    public Integer updateByPrimaryKeySelective(FileUpLoad file){
        return  fileUpLoadMapper.updateByPrimaryKeySelective(file);
    }

    //实现文件的下载
    //实现文件下载(文件名/数组长度/IO流)
    public ResponseEntity<InputStreamResource> downloadFile(Integer id) throws IOException {
        //文件绝对路径
        String filepath = fileUpLoadService.selectByPrimaryKey(id).getPath();
        //根据绝对路径创建一个文件对象
        FileSystemResource file = new FileSystemResource(filepath);
        //将数据封装到请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        //需要一个文件名
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String(fileUpLoadService.selectByPrimaryKey(id).getFilename().getBytes("UTF-8"),"ISO-8859-1")));
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

