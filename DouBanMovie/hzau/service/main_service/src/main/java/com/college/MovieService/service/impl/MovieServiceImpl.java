package com.college.MovieService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.college.MovieService.client.*;
import com.college.MovieService.entity.Movie;
import com.college.MovieService.mapper.MovieMapper;
import com.college.MovieService.service.MovieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.college.commonutils.R;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FengXZ
 * @since 2021-05-24
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {
    @Autowired
    private scoreClient scoreClient;

    @Autowired
    private hotClient hotClient;

    @Autowired
    private numClient numClient;

    @Autowired
    private dreClient dreClient;

    @Autowired
    private actClient actClient;

    //获取所有年份
    @Cacheable(key = "'yearList'",value = "movie")
    @Override
    public List<String> getYear() {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.select("Movie_time");
        wrapper.orderByDesc("Movie_time");
        List<Movie> movies = baseMapper.selectList(wrapper);
        List<String> res = new ArrayList<>();
        HashMap<String,Boolean> map = new HashMap<>();
        for (int i = 0; i < movies.size(); i++) { //17
            String time = movies.get(i).getMovieTime();
            String[] split = time.split(",");
            String year = split[0];
            map.put(year,!map.containsKey(year));
            if (map.get(year)) res.add(year);
            else continue;
        }
        return  res;
    }

    //获取所有类型
    @Cacheable(key = "'typeList'",value = "movie")
    @Override
    public List<String> getType() {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.select("Movie_type");
        List<Movie> movies = baseMapper.selectList(wrapper);
        List<String> res = new ArrayList<>();
        HashMap<String,Boolean> map = new HashMap<>();
        for (int i = 0; i < movies.size(); i++) { //3
            String type = movies.get(i).getMovieType();
            String[] split = type.split(",");
            for(String s:split){
                map.put(s,!map.containsKey(s));
                if (map.get(s)) res.add(s);
                else continue;
            }
        }
        return res;
    }

    //获取所有地区
    @Cacheable(key = "'areaList'",value = "movie")
    @Override
    public List<String> getArea() {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.select("Movie_area");
        List<Movie> movies = baseMapper.selectList(wrapper);
        List<String> res = new ArrayList<>();
        HashMap<String,Boolean> map = new HashMap<>();
        for (int i = 0; i < movies.size(); i++) { //3
            String area = movies.get(i).getMovieArea();
                map.put(area,!map.containsKey(area));
                if (map.get(area)) res.add(area);
        }
        return res;
    }

    //获取地区产量
    @Cacheable(key = "'areanumsList'",value = "movie")
    @Override
    public List<Map<String,Object>> getAreaNums(List<String> areas) {
        List<Map<String,Object>> res = new ArrayList<>();
        for (int i = 0; i < areas.size(); i++) {
            HashMap<String,Object> map = new HashMap<>();
            map.put("name",areas.get(i));
            String area = areas.get(i);
            QueryWrapper<Movie> wrapper = new QueryWrapper<>();
            wrapper.eq("Movie_area",area);
            Integer count = baseMapper.selectCount(wrapper);
            map.put("value",count);
            res.add(map);
        }
        return res;
    }

    //获取类型产量
    @Cacheable(key = "'typenumsList'",value = "movie")
    @Override
    public List<Map<String,Object>> getTypeNums(List<String> types) {
        List<Map<String,Object>> res = new ArrayList<>();
        for (int i = 0; i < types.size(); i++) {
            HashMap<String,Object> map = new HashMap<>();
            map.put("name",types.get(i));
            String type = types.get(i);
            QueryWrapper<Movie> wrapper = new QueryWrapper<>();
            wrapper.like("Movie_type",type);
            Integer count = baseMapper.selectCount(wrapper);
            map.put("value",count);
            res.add(map);
        }
        return res;
    }

    //获取地区热度
    @Cacheable(key = "'areahotList'",value = "movie")
    @Override
    public List<Map<String, Object>> getAreaHot(List<String> areas) {
        List<Map<String,Object>> res = new ArrayList<>();
        for (int i = 0; i < areas.size(); i++) {
            HashMap<String,Object> map = new HashMap<>();
            String area = areas.get(i);
            map.put("name",area);
            QueryWrapper<Movie> wrapper = new QueryWrapper<>();
            wrapper.eq("Movie_area",area);
            List<Movie> movies = baseMapper.selectList(wrapper);
            double sum = 0;
            for (int j = 0; j < movies.size(); j++) {
               sum = sum+ movies.get(j).getMoviePerson()+ movies.get(j).getReviewAmount()*4;
            }
            map.put("value",sum);
            res.add(map);
        }
        return res;
    }

    //获取年度评分数
    @Cacheable(key = "'yearscoreList'",value = "movie")
    @Override
    public List<Map<String,Object>> getYearScore(List<String> years) {
        List<Map<String,Object>> res = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String,Object> map = new HashMap<>();
            String year = years.get(i);
            map.put("name",year);
            QueryWrapper<Movie> wrapper = new QueryWrapper<>();
            wrapper.like("Movie_time",year);
            Integer count = baseMapper.selectCount(wrapper);
            map.put("value",count);
            res.add(map);
        }
        return res;
    }

    //总接口实现类
    @Override
    public R getTypeYearScore() {
        R getscore = scoreClient.getscore();
        return getscore;
    }

    @Override
    public R getTypeYearHot() {
        R gethot = hotClient.gethot();
        return gethot;
    }

    @Override
    public R getTypeYearPro() {
        R getnum = numClient.getnum();
        return getnum;
    }

    @Override
    public R getDre() {
        R scoreEveryDrc = dreClient.getScoreEveryDrc();
        return scoreEveryDrc;
    }

    @Override
    public R getAct() {
        R scoreEveryAct = actClient.getScoreEveryAct();
        return scoreEveryAct;
    }
}
