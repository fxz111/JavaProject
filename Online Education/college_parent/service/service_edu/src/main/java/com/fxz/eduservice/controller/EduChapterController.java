package com.fxz.eduservice.controller;


import com.fxz.commonutils.R;
import com.fxz.eduservice.entity.EduChapter;
import com.fxz.eduservice.entity.chapter.ChapterVo;
import com.fxz.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-02-19
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;
    @GetMapping("getAllCV/{courseId}")
    public R getAll(@PathVariable(required = true) String courseId){
        List<ChapterVo> list = chapterService.getAllCV(courseId);
        return R.ok().data("list",list);
    }
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return R.ok();
    }
    @GetMapping("getById/{chapterId}")
    public R getById(@PathVariable String chapterId){
        EduChapter eduChapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",eduChapter);
    }
    @PostMapping("update")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return R.ok();
    }
    @DeleteMapping("deleteById/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        boolean flag =chapterService.deleteChapter(chapterId);
        if(flag){
            return R.ok();
        }
        else {
            return R.error();
        }
    }


}

