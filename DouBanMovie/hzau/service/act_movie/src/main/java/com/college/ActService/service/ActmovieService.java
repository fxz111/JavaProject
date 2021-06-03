package com.college.ActService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.college.ActService.entity.Act;
import com.college.ActService.entity.Actmovie;
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
public interface ActmovieService extends IService<Actmovie> {





    List<Act> getActMovie();
}
