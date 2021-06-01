package com.college.ScoreService.service;

import com.college.ScoreService.entity.Scoremovie;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FengXZ
 * @since 2021-05-27
 */
public interface ScoremovieService extends IService<Scoremovie> {

    List<Map<String, Object>> getscore();
}
