package com.fxz.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.commonutils.R;
import com.fxz.eduservice.entity.EduTeacher;
import com.fxz.eduservice.entity.query.TeacherQuery;
import com.fxz.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.StyledEditorKit;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-09
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/eduteacher")
@CrossOrigin
public class EduTeacherController {;
    @Autowired
    private EduTeacherService eduTeacherService;

    //查询所有讲师
    @ApiOperation("讲师列表")
    @GetMapping("findAll")
    public R list(){
        List<EduTeacher> list = eduTeacherService.list(null);
        System.out.println(1);
        return R.ok().data("items", list);

    }

    //逻辑删除讲师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("deleteById/{id}")
    public R removeById(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag == true){return R.ok();}
        else{return R.error();}

    }

    //分页查询讲师的
    @ApiOperation("分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current",value = "当前页数",required = true)@PathVariable long current,
                             @ApiParam(name = "limit",value = "每页最大数",required = true)@PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        eduTeacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    //分页查询
    @ApiOperation("分页组合条件查询")
    @PostMapping("pageQuery/{current}/{limit}")
    public R pageQuery(@ApiParam(name = "current",value = "当前页数",required = true)@PathVariable long current,
                       @ApiParam(name = "limit",value = "每页最大数",required = true)@PathVariable long limit,
                       @ApiParam(name = "teacherQuery",value = "查询条件对象")@RequestBody(required = false) TeacherQuery teacherQuery ) {
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        eduTeacherService.pageQuery(pageTeacher,teacherQuery);
        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    //新建讲师
    @ApiOperation("新建讲师")
    @PostMapping("Insert")
    public R insertTeacher(@ApiParam(name = "eduTeacher",value = "讲师对象",required = true)@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.save(eduTeacher);
        if(b) {
            return R.ok();
        } else{
            return R.error();
        }
    }

    //id查询
    @ApiOperation("id查询讲师")
    @GetMapping("findById/{id}")
    public R findById(@ApiParam(name = "id",value = "讲师ID",required = true)@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item",teacher);
    }

    //更新讲师
    @ApiOperation("更新讲师")
    @PostMapping("update")
    public R updateTeacher(@ApiParam(name = "eduTeacher",value = "讲师对象",required = true)@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }

    }


}

