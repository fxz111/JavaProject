package com.fxz.eduservice.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxz.eduservice.client.VodClient;
import com.fxz.eduservice.entity.EduVideo;
import com.fxz.eduservice.mapper.EduVideoMapper;
import com.fxz.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-19
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
    private VodClient vodClient;

    @Override
    public void removeVideoByCourseId(String courseId) {
        //删除阿里云视频
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id",courseId);
        videoWrapper.select("video_source_id");
        List<EduVideo> videoList = baseMapper.selectList(videoWrapper);
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            EduVideo eduVideo = videoList.get(i);
            String videoId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoId)){
                videoIds.add(videoId);
            }
        }
        if (videoIds.size()>0){
            vodClient.deleteVideos(videoIds);
        }
        //删除小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
