package com.fxz.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.eduservice.entity.frontvo.CourseFrontVo;
import com.fxz.eduservice.entity.frontvo.CourseWebInfoVo;
import com.fxz.eduservice.entity.query.CourseQuery;
import com.fxz.eduservice.entity.vo.CourseInfoVo;
import com.fxz.eduservice.entity.vo.CoursePublishVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-19
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void pageQuery(Page<EduCourse> pageCourse, CourseQuery courseQuery);

    void deleteById(String courseId);

    List<EduCourse> getEightCourses();

    Map<String, Object> getCourseFrontList(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo);

    CourseWebInfoVo getBaseCourseInfo(String courseId);
}
