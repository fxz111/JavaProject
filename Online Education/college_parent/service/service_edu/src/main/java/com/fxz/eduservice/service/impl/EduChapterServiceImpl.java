package com.fxz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxz.eduservice.entity.EduChapter;
import com.fxz.eduservice.entity.EduVideo;
import com.fxz.eduservice.entity.chapter.ChapterVo;
import com.fxz.eduservice.entity.chapter.VideoVo;
import com.fxz.eduservice.mapper.EduChapterMapper;
import com.fxz.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.eduservice.service.EduVideoService;
import com.fxz.servicebase.exceptionhandler.GuliException;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-19
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService videoService;
    @Override
    public List<ChapterVo> getAllCV(String courseId) {
        //获取所有章节列表
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapperChapter);
        //获取所有小节列表
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduVideo> eduVideos = videoService.list(wrapperVideo);
        //封装章节
        List<ChapterVo> finalList = new ArrayList<>();
        for (int i = 0; i < eduChapters.size(); i++) {
            EduChapter chapter = eduChapters.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            finalList.add(chapterVo);
            List<VideoVo> finalVideo = new ArrayList<>();
            for (int j = 0; j < eduVideos.size(); j++) {
                EduVideo video = eduVideos.get(j);
                if (video.getChapterId().equals(chapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    finalVideo.add(videoVo);
                }

            }
            chapterVo.setChildren(finalVideo);


        }
        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //先根据id查小节表，若有小节不删除，没有则删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        if(count>0){
            throw new GuliException(20001,"不能删除");
        }else{
          int result = baseMapper.deleteById(chapterId);
            return result>0;
        }
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
