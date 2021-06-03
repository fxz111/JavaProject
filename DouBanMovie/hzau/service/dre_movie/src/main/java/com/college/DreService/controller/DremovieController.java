package com.college.DreService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.college.DreService.entity.Dre;
import com.college.DreService.entity.Dremovie;
import com.college.DreService.service.DremovieService;
import com.college.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-05-24
 */
@RestController
@RequestMapping("/DreService/dremovie")
@CrossOrigin
@Api(description = "导演接口")
public class DremovieController {
    @Autowired
    private DremovieService dremovieService;
//    @ApiOperation(value = "导演影片质量")
//    @GetMapping("getScoreEveryDrc1")
//    public R getScoreEveryDrc1(){
//        List<String> dre = new ArrayList<>();
//        List<Integer> bad = new ArrayList<>();
//        List<Integer> mid = new ArrayList<>();
//        List<Integer> good = new ArrayList<>();
//        List<Dremovie> dremovies = dremovieService.list(null);
//        for (int i = 0; i < dremovies.size(); i++) {
//            dre.add(dremovies.get(i).getMovieDirector());
//            bad.add(dremovies.get(i).getBad());
//            mid.add(dremovies.get(i).getMid());
//            good.add(dremovies.get(i).getGood());
//        }
//        return R.ok().data("dre",dre).data("bad",bad).data("mid",mid).data("good",good);
//    }
    //最终接口
    @ApiOperation(value = "导演影片质量分析")
    @GetMapping("getScoreEveryDrc")
    public R getScoreEveryDrc(){
        List<Dre> list = dremovieService.getDreMovie();
        return R.ok().data("title","导演影片分析").data("list",list);
    }
    @GetMapping("getdreList")
    public R getdreList(){
        List<Dremovie> list = dremovieService.list(null);
        return R.ok().data("list",list);
    }

}

