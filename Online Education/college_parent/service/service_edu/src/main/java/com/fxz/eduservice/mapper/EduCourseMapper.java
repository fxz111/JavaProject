package com.fxz.eduservice.mapper;

import com.fxz.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.eduservice.entity.frontvo.CourseWebInfoVo;
import com.fxz.eduservice.entity.vo.CoursePublishVo;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-19
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

     public CoursePublishVo getPublishCourseInfo(String id);

    CourseWebInfoVo getBaseCourseInfo(String courseId);
}
