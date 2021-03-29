package com.fxz.eduservice.controller;


import com.alibaba.excel.util.StringUtils;
import com.fxz.commonutils.R;
import com.fxz.eduservice.client.VodClient;
import com.fxz.eduservice.entity.EduVideo;
import com.fxz.eduservice.service.EduVideoService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-19
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private VodClient vodClient;
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return R.ok();
    }
    @DeleteMapping("deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        //chapterId=>videoId
        EduVideo eduvideo = eduVideoService.getById(id);
        String VideoId = eduvideo.getVideoSourceId();
        if(!StringUtils.isEmpty(VideoId)){
            vodClient.deleteById(VideoId);
        }
        eduVideoService.removeById(id);
        return R.ok();
    }
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }
    @GetMapping("getById/{id}")
    public R getById(@PathVariable String id){
        EduVideo eduVideo = eduVideoService.getById(id);
        return R.ok().data("video",eduVideo);
    }

}

