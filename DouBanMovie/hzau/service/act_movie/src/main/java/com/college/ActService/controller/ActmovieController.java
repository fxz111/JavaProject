package com.college.ActService.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.college.ActService.entity.Act;
import com.college.ActService.entity.Actmovie;
import com.college.ActService.service.ActmovieService;
import com.college.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/ActService/actmovie")
@CrossOrigin
@Api(description = "演员接口")
public class ActmovieController {
    @Autowired
    private ActmovieService actmovieService;
//    @ApiOperation("演员影片质量")
//    @GetMapping("getScoreEveryAct1")
//    public R getScoreEveryAct1(){
//        List<String> act = new ArrayList<>();
//        List<Integer> bad = new ArrayList<>();
//        List<Integer> mid = new ArrayList<>();
//        List<Integer> good = new ArrayList<>();
//        List<Actmovie> list = actmovieService.list(null);
//        for (int i = 0; i < list.size(); i++) {
//            act.add(list.get(i).getMovieActor());
//            bad.add(list.get(i).getBad());
//            mid.add(list.get(i).getMid());
//            good.add(list.get(i).getGood());
//        }
//        return R.ok().data("act",act).data("bad",bad).data("mid",mid).data("good",good);
//    }
    //最终接口
    @ApiOperation("演员影片质量分析")
    @GetMapping("getScoreEveryAct")
    public R getScoreEveryAct(){
        List<Act> list  = actmovieService.getActMovie();
        return R.ok().data("title","演员影片分析").data("list",list);
    }
    @GetMapping("getactList")
    public R getactList(){
        List<Actmovie> list = actmovieService.list(null);
        return R.ok().data("list",list);
    }


}

