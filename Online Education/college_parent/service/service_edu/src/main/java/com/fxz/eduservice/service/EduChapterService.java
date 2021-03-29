package com.fxz.eduservice.service;

import com.fxz.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-19
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getAllCV(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
