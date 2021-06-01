package com.college.HotService.service;

import com.college.HotService.entity.Hotmovie;
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
public interface HotmovieService extends IService<Hotmovie> {

    List<Map<String, Object>> gethot();
}
