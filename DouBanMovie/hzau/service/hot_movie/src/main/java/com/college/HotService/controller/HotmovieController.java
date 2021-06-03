package com.college.HotService.controller;


import com.college.HotService.entity.Hotmovie;
import com.college.HotService.service.HotmovieService;
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
@CrossOrigin
@RestController
@RequestMapping("/HotService/hotmovie")
public class HotmovieController {
    @Autowired
    private HotmovieService hotmovieService;
    @GetMapping("gethot")
    public R gethot(){
        List<Map<String, Object>> list = hotmovieService.gethot();
        return R.ok().data("title","电影年度热度").data("list",list);
    }
    @GetMapping("gethotList")
    public R gethotList(){
        List<Hotmovie> list = hotmovieService.list(null);
        return R.ok().data("list",list);
    }
}

