package com.fxz.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.commonutils.R;
import com.fxz.eduservice.entity.EduCourse;
import com.fxz.eduservice.entity.EduTeacher;
import com.fxz.eduservice.service.EduCourseService;
import com.fxz.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/teacherFront")
@CrossOrigin
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;
    @PostMapping("getFrontTeacher/{page}/{limit}")
    public R getList(@PathVariable Long page,@PathVariable Long limit){
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        Map<String,Object> map =  teacherService.getTeacherList(teacherPage);
        return R.ok().data(map);
    }
    //讲师详情
    @PostMapping("getTeacherFrontInfo/{teacherId}")
    public R getInfo(@PathVariable String teacherId){
        //id查详情
        EduTeacher teacher = teacherService.getById(teacherId);
        //id查课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = courseService.list(wrapper);
        return R.ok().data("teacher",teacher).data("courseList",courseList);
    }
}
