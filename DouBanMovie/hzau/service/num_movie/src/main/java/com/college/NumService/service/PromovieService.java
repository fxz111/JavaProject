package com.college.NumService.service;

import com.college.NumService.entity.Promovie;
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
public interface PromovieService extends IService<Promovie> {

    List<Map<String, Object>> getnum();
}
