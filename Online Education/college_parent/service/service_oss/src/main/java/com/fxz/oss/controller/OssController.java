package com.fxz.oss.controller;

import com.fxz.commonutils.R;
import com.fxz.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(description = "阿里云文件上传")
@RestController
@RequestMapping("/eduoss/file")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;
    @ApiOperation(value = "头像上传")
    @PostMapping("upload")
    public R upload(MultipartFile file){
        String url = ossService.uploadAvatar(file);
        return R.ok().message("头像上传成功").data("url",url);
    }
}
