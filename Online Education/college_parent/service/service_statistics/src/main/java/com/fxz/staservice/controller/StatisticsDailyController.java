package com.fxz.staservice.controller;


import com.fxz.commonutils.R;
import com.fxz.staservice.client.UcenterClient;
import com.fxz.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-08
 */
@RestController
@RequestMapping("/staservice/sta")
@CrossOrigin
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService dailyService;
    @PostMapping("registerCount/{time}")
    public R registerCount(@PathVariable String time){
        dailyService.registerCount(time);
        return R.ok();
    }
    //图表显示
    @GetMapping("showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,
                      @PathVariable String begin,
                      @PathVariable String end){
        Map<String,Object> map = dailyService.showData(type,begin,end);
        return R.ok().data(map);
    }
}

