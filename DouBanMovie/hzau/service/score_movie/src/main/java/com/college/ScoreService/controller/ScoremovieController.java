package com.college.ScoreService.controller;


import com.college.ScoreService.entity.Scoremovie;
import com.college.ScoreService.service.ScoremovieService;
import com.college.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-05-27
 */
@RestController
@RequestMapping("/ScoreService/scoremovie")
@CrossOrigin
public class ScoremovieController {
    @Autowired
    private ScoremovieService scoremovieService;
    @GetMapping("getscore")
    public R getscore(){
        List<Map<String, Object>> list = scoremovieService.getscore();
        return R.ok().data("title","电影年度评分").data("list",list);
    }

}

