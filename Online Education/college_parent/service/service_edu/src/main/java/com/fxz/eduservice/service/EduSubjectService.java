package com.fxz.eduservice.service;

import com.fxz.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-18
 */
public interface EduSubjectService extends IService<EduSubject> {

    void importSubjectData(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> getAllSubjects();
}
