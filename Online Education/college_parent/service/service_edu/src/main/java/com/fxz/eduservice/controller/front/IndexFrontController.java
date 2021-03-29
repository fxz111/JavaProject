package com.fxz.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxz.commonutils.R;
import com.fxz.eduservice.entity.EduCourse;
import com.fxz.eduservice.entity.EduTeacher;
import com.fxz.eduservice.service.EduCourseService;
import com.fxz.eduservice.service.EduTeacherService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;
    @GetMapping("findIndex")
    public R index(){
        List<EduCourse> courseList = courseService.getEightCourses();
        List<EduTeacher> teacherList = teacherService.getFourTeachers();
        return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }
}
