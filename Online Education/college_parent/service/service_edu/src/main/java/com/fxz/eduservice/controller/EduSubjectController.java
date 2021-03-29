package com.fxz.eduservice.controller;


import com.fxz.commonutils.R;
import com.fxz.eduservice.entity.subject.OneSubject;
import com.fxz.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-18
 */
@Api(description = "课程分类管理")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addsubject")
    public R addSubject(MultipartFile file){
        subjectService.importSubjectData(file,subjectService);
        return R.ok();
    }
    //课程分类
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list = subjectService.getAllSubjects();
        return R.ok().data("list",list);
    }

}

