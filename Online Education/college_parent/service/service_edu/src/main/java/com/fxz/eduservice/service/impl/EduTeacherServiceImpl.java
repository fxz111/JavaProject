package com.fxz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.eduservice.entity.EduTeacher;
import com.fxz.eduservice.entity.query.TeacherQuery;
import com.fxz.eduservice.mapper.EduTeacherMapper;
import com.fxz.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-09
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }
        wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageParam, wrapper);
    }

    @Cacheable(key = "'selectIndexList'",value = "teacher")
    @Override
    public List<EduTeacher> getFourTeachers() {
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<EduTeacher> teacherList = baseMapper.selectList(teacherQueryWrapper);
        return teacherList;
    }

    @Override
    public Map<String, Object> getTeacherList(Page<EduTeacher> teacherPage) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        baseMapper.selectPage(teacherPage,wrapper);
        List<EduTeacher> records = teacherPage.getRecords();
        long current = teacherPage.getCurrent();
        long pages = teacherPage.getPages();
        long size = teacherPage.getSize();
        long total = teacherPage.getTotal();
        boolean hasNext = teacherPage.hasNext();//下一页
        boolean hasPrevious = teacherPage.hasPrevious();//上一页
        Map<String,Object> map = new HashMap();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }
}
