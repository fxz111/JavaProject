package com.fxz.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.ram.model.v20150501.DeleteAccessKeyRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.fxz.commonutils.R;
import com.fxz.servicebase.exceptionhandler.GuliException;
import com.fxz.vod.service.VodService;
import com.fxz.vod.utils.ConstantVodUtils;
import com.fxz.vod.utils.InitVodCilent;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("vodservice/video")
@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;
    @PostMapping("uploadVideo")
    public R uploadVideo(MultipartFile file){
        String videoId = vodService.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }
    @DeleteMapping("deleteAliyunById/{id}")
    public R deleteById(@PathVariable String id){
        try{
            DefaultAcsClient defaultAcsClient = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            defaultAcsClient.getAcsResponse(request);
            return R.ok();
        }catch(Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"删除失败");
        }
    }
    @DeleteMapping("deleteAliyunVideos")
    public R deleteVideos(@RequestParam("videoList")List<String> videoList){
        vodService.deleteVideos(videoList);
        return R.ok();
    }
    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id){
        try{
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth",playAuth);
        }catch (Exception e){
            throw new GuliException(20001,"获取错误");
        }

    }
}
