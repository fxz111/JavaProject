package com.fxz.eduorder.client;

import com.fxz.commonutils.orderVo.CourseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-edu")
public interface CourseClient {
    @GetMapping("/eduservice/coursefront/getCourseOrder/{courseId}")
    public CourseOrder getCourseOrder(@PathVariable("courseId") String courseId);
}
