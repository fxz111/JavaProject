package com.fxz.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.commonutils.JwtUtils;
import com.fxz.commonutils.R;
import com.fxz.commonutils.orderVo.CourseOrder;
import com.fxz.eduservice.client.OrderClient;
import com.fxz.eduservice.entity.EduCourse;
import com.fxz.eduservice.entity.chapter.ChapterVo;
import com.fxz.eduservice.entity.frontvo.CourseFrontVo;
import com.fxz.eduservice.entity.frontvo.CourseWebInfoVo;
import com.fxz.eduservice.service.EduChapterService;
import com.fxz.eduservice.service.EduCourseService;
import com.fxz.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/eduservice/coursefront")
public class FrontCourseController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private OrderClient orderClient;
    @PostMapping("getFrontCourse/{page}/{limit}")
    public R getFrontCourse(@PathVariable Long page,
                            @PathVariable Long limit,
                            @RequestBody(required = false)CourseFrontVo courseFrontVo){
        Page<EduCourse> coursePage = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(coursePage,courseFrontVo);
        return R.ok().data(map);
    }
    //查询课程详细信息
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId,HttpServletRequest request){
        //查课程信息
        CourseWebInfoVo courseInfo= courseService.getBaseCourseInfo(courseId);
        //查章节和小节
        List<ChapterVo> chapterVoList = chapterService.getAllCV(courseId);
        //根据课程id和用户id查询status
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(memberId == null){
            throw new GuliException(20001,"未登录");
        }else{
            System.out.println(memberId);
            Boolean status = orderClient.getStatus(courseId, memberId);
            System.out.println(status);
            return R.ok().data("courseWebInfo",courseInfo).data("chapterVoList",chapterVoList).data("status",status);
        }
    }
    @GetMapping("getCourseOrder/{courseId}")
    public CourseOrder getCourseOrder(@PathVariable String courseId){
        CourseWebInfoVo baseCourseInfo = courseService.getBaseCourseInfo(courseId);
        CourseOrder courseOrder = new CourseOrder();
        BeanUtils.copyProperties(baseCourseInfo,courseOrder);
        return courseOrder;
    }
    @GetMapping("ceshi/{courseId}/{id}")
    public Boolean ceshi(@PathVariable String courseId,@PathVariable String id){
        Boolean status = orderClient.getStatus(courseId, id);
        return status;
    }
}
