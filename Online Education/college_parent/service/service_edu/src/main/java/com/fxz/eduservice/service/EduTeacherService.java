package com.fxz.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.eduservice.entity.query.TeacherQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-09
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    List<EduTeacher> getFourTeachers();

    Map<String, Object> getTeacherList(Page<EduTeacher> teacherPage);
}
