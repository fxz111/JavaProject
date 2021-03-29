package com.fxz.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxz.eduservice.entity.EduSubject;
import com.fxz.eduservice.entity.excel.ExcelSubject;
import com.fxz.eduservice.entity.subject.OneSubject;
import com.fxz.eduservice.entity.subject.TwoSubject;
import com.fxz.eduservice.listen.SubjectExcelListener;
import com.fxz.eduservice.mapper.EduSubjectMapper;
import com.fxz.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-18
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Override
    public void importSubjectData(MultipartFile file, EduSubjectService subjectService) {
        try{
            InputStream in = file.getInputStream();
            EasyExcel.read(in, ExcelSubject.class,new SubjectExcelListener(subjectService)).sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllSubjects() {
        //查询一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> OneSubject = baseMapper.selectList(wrapperOne);
        //查询二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> TwoSubject = baseMapper.selectList(wrapperTwo);
        //创建最终集合
        List<OneSubject> finalSubjectList = new ArrayList<>();
        //封装一级分类
        for (int i = 0; i < OneSubject.size(); i++) {
            EduSubject eduSubject = OneSubject.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);
            //封装二级分类
            List<TwoSubject> twoFinalSubject = new ArrayList<>();
            for (int j = 0; j < TwoSubject.size(); j++) {
                EduSubject twoSubject = TwoSubject.get(j);
                //判断二级分类pid和一级分类id是否一样
                if(twoSubject.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject1 = new TwoSubject();
                    BeanUtils.copyProperties(twoSubject,twoSubject1);
                    twoFinalSubject.add(twoSubject1);
                }
            }
            oneSubject.setChildren(twoFinalSubject);
            finalSubjectList.add(oneSubject);
        }


        return finalSubjectList;
    }
}
