package com.fxz.staservice.schedule;

import com.fxz.staservice.service.StatisticsDailyService;
import com.fxz.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Task {
    @Autowired
    private StatisticsDailyService dailyService;
    //定时器，每天凌晨一点刷新，保证数据定时跟新
    @Scheduled(cron = "0 0 1 * * ?")
    public void task(){
        dailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
