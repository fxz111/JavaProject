package com.fxz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.eduservice.entity.EduCourse;
import com.fxz.eduservice.entity.EduCourseDescription;
import com.fxz.eduservice.entity.frontvo.CourseFrontVo;
import com.fxz.eduservice.entity.frontvo.CourseWebInfoVo;
import com.fxz.eduservice.entity.query.CourseQuery;
import com.fxz.eduservice.entity.vo.CourseInfoVo;
import com.fxz.eduservice.entity.vo.CoursePublishVo;
import com.fxz.eduservice.mapper.EduCourseMapper;
import com.fxz.eduservice.service.EduChapterService;
import com.fxz.eduservice.service.EduCourseDescriptionService;
import com.fxz.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.eduservice.service.EduVideoService;
import com.fxz.servicebase.exceptionhandler.GuliException;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-19
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert<=0){
            //添加失败
            throw new GuliException(20001,"添加课程信息失败");
        }
        String cid = eduCourse.getId();
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(cid);
        eduCourseDescriptionService.save(courseDescription);
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        EduCourseDescription description = eduCourseDescriptionService.getById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        courseInfoVo.setDescription(description.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        baseMapper.updateById(eduCourse);
        EduCourseDescription description = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,description);
        eduCourseDescriptionService.updateById(description);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        //调用mapper
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public void pageQuery(Page<EduCourse> pageCourse, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String courseName = courseQuery.getName();
        String Status = courseQuery.getStatus();
        if (!StringUtils.isEmpty(courseName)){
            wrapper.like("title",courseName);
        }
        if (!StringUtils.isEmpty(Status)){
            wrapper.eq("status",Status);
        }
        wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageCourse,wrapper);
    }

    @Override
    public void deleteById(String courseId) {
        eduVideoService.removeVideoByCourseId(courseId);
        eduChapterService.removeChapterByCourseId(courseId);
        eduCourseDescriptionService.removeById(courseId);
        int result = baseMapper.deleteById(courseId);
        if(result == 0){
            throw new GuliException(20001,"删除失败");
        }

    }

    @Cacheable(key = "'selectIndexList'",value = "course")
    @Override
    public List<EduCourse> getEightCourses() {
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        List<EduCourse> courseList = baseMapper.selectList(courseQueryWrapper);
        return courseList;
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo) {
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        //判断，不为空再拼接
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())){//一级分类
            courseQueryWrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())){//二级分类
            courseQueryWrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())){//销量
            courseQueryWrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())){//时间
            courseQueryWrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())){//价格
            courseQueryWrapper.orderByDesc("price");
        }
        baseMapper.selectPage(coursePage,courseQueryWrapper);
        List<EduCourse> records = coursePage.getRecords();
        long current = coursePage.getCurrent();
        long pages = coursePage.getPages();
        long size = coursePage.getSize();
        long total = coursePage.getTotal();
        boolean hasNext = coursePage.hasNext();//下一页
        boolean hasPrevious = coursePage.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    @Override
    public CourseWebInfoVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }
}
