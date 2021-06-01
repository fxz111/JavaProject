package com.college.ScoreService.service.impl;

import com.college.ScoreService.entity.Scoremovie;
import com.college.ScoreService.mapper.ScoremovieMapper;
import com.college.ScoreService.service.ScoremovieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FengXZ
 * @since 2021-05-27
 */
@Service
public class ScoremovieServiceImpl extends ServiceImpl<ScoremovieMapper, Scoremovie> implements ScoremovieService {

    @Override
    public List<Map<String, Object>> getscore() {
        List<Map<String, Object>> res = new ArrayList<>();
        List<Scoremovie> scoremovieList = baseMapper.selectList(null);
        for (int i = 0; i < scoremovieList.size(); i++) {
            HashMap<String,Object> map = new HashMap<>();
            String movieType = scoremovieList.get(i).getMovieType();
            map.put("name",movieType);
            String nums = scoremovieList.get(i).getNum();
            String[] split = nums.split("-");
            List<Double> list = new ArrayList<>();
            for (int j = 0; j < split.length; j++) {
                list.add(Double.parseDouble(split[j]));
            }
            map.put("list",list);
            res.add(map);
        }
        return res;
    }
}
