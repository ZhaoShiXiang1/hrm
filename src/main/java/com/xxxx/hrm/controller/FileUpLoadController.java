package com.xxxx.hrm.controller;

import com.xxxx.hrm.base.BaseController;
import com.xxxx.hrm.service.FileUpLoadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("file")
public class FileUpLoadController extends BaseController {

    @Resource
    private FileUpLoadService fileUpLoadService;
}
