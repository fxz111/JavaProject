package com.fxz.eduservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.commonutils.R;
import com.fxz.eduservice.entity.EduCourse;
import com.fxz.eduservice.entity.EduTeacher;
import com.fxz.eduservice.entity.query.CourseQuery;
import com.fxz.eduservice.entity.vo.CourseInfoVo;
import com.fxz.eduservice.entity.vo.CoursePublishVo;
import com.fxz.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-19
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;
    //课程列表（条件查询带分页）
    @PostMapping("getCourseList/{current}/{limit}")
    public R getCourseList(@PathVariable long current,
                           @PathVariable long limit,
                           @RequestBody(required = false) CourseQuery courseQuery){
        Page<EduCourse> pageCourse = new Page<>(current,limit);
        courseService.pageQuery(pageCourse,courseQuery);
        long total = pageCourse.getTotal();//总记录数
        List<EduCourse> records = pageCourse.getRecords(); //数据list集合
        return R.ok().data("total",total).data("list",records);
    }

    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("list",courseInfoVo);
    }
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return R.ok();
    }
    @DeleteMapping("deleteById/{courseId}")
    public R deleteById(@PathVariable String courseId){
        courseService.deleteById(courseId);
        return R.ok();
    }

}

