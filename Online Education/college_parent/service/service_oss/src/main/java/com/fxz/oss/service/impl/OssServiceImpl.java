package com.fxz.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.fxz.oss.service.OssService;
import com.fxz.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadAvatar(MultipartFile file) {
        String endPoint = ConstantPropertiesUtils.END_POIND;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        String url = null;
        String fileName = file.getOriginalFilename();
        try{
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = datePath+"/"+uuid+fileName;
            ossClient.putObject(bucketName,fileName,inputStream);
            ossClient.shutdown();
            url = "https://"+bucketName+"."+endPoint+"/"+fileName;
            return url;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
