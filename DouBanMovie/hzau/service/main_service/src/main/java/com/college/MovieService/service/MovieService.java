package com.college.MovieService.service;

import com.college.MovieService.entity.Movie;
import com.baomidou.mybatisplus.extension.service.IService;
import com.college.commonutils.R;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FengXZ
 * @since 2021-05-24
 */
public interface MovieService extends IService<Movie> {

    List<String> getYear();

    List<String> getType();

    List<String> getArea();

    List<Map<String,Object>> getTypeNums(List<String> type);

    List<Map<String,Object>> getAreaNums(List<String> area);

    List<Map<String, Object>> getAreaHot(List<String> areas);

    List<Map<String,Object>> getYearScore(List<String> year);

    R getTypeYearScore();

    R getTypeYearHot();

    R getTypeYearPro();


    R getDre();

    R getAct();
}
