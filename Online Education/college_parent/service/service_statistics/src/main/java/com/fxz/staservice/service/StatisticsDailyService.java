package com.fxz.staservice.service;

import com.fxz.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-08
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void registerCount(String time);

    Map<String, Object> showData(String type, String begin, String end);
}
