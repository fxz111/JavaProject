package com.fxz.eduservice.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fxz.eduservice.entity.EduSubject;
import com.fxz.eduservice.entity.excel.ExcelSubject;
import com.fxz.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxz.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubject> {
    public EduSubjectService subjectService;
    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(ExcelSubject excelSubject, AnalysisContext analysisContext) {
        if (excelSubject == null) {
            throw new GuliException(20001, "文件数据为空");
        }

        EduSubject existOneSubject = this.existOneSubject(subjectService, excelSubject.getOneSubjectName());
        if(existOneSubject == null) { //没有相同一级分类，进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(excelSubject.getOneSubjectName());//一级分类名称
            subjectService.save(existOneSubject);
        }

        //获取一级分类id值
        String pid = existOneSubject.getId();

        //添加二级分类
        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, excelSubject.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(excelSubject.getTwoSubjectName());//二级分类名称
            subjectService.save(existTwoSubject);
        }
    }
    private EduSubject existOneSubject(EduSubjectService subjectService,String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
