package com.fxz.staservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxz.staservice.client.UcenterClient;
import com.fxz.staservice.entity.StatisticsDaily;
import com.fxz.staservice.mapper.StatisticsDailyMapper;
import com.fxz.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-08
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public void registerCount(String time) {
        //先删除再添加
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",time);
        baseMapper.delete(wrapper);
        Map<String, Object> map = ucenterClient.getRegisterCount(time).getData();
        Integer countRegister = (Integer) map.get("countRegister");
        StatisticsDaily sta = new StatisticsDaily();
        sta.setRegisterNum(countRegister);//注册人数
        sta.setDateCalculated(time);
        sta.setVideoViewNum(RandomUtils.nextInt(100,200));
        sta.setLoginNum(RandomUtils.nextInt(100,200));
        sta.setCourseNum(RandomUtils.nextInt(100,200));
        //添加数据
        baseMapper.insert(sta);
    }

    @Override
    public Map<String, Object> showData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.select("date_calculated",type);
        List<StatisticsDaily> staList = baseMapper.selectList(wrapper);
        List<String> dataList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < staList.size(); i++) {
            StatisticsDaily daily = staList.get(i);
            dataList.add(daily.getDateCalculated());
            switch (type){
                case "register_num":
                    numList.add(daily.getRegisterNum());
                    break;
                case "login_num":
                    numList.add(daily.getLoginNum());
                    break;
                case "video_view_num":
                    numList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    numList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("dateList",dataList);
        map.put("numList",numList);
          return map;
    }
}
