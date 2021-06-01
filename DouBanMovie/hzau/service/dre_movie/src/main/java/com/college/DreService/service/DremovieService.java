package com.college.DreService.service;

import com.college.DreService.entity.Dre;
import com.college.DreService.entity.Dremovie;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FengXZ
 * @since 2021-05-24
 */
public interface DremovieService extends IService<Dremovie> {

    List<Dre> getDreMovie();
}
