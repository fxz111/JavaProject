package com.college.NumService.controller;


import com.college.NumService.service.PromovieService;
import com.college.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/NumService/promovie")
public class PromovieController {
    @Autowired
    private PromovieService promovieService;
    @GetMapping("getnum")
    public R getnum(){
        List<Map<String, Object>> list = promovieService.getnum();
        return R.ok().data("title","电影年度产量").data("list",list);
    }

}

