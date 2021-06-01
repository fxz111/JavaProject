package com.college.WordService.service;

import com.college.WordService.entity.Wordcloud;
import com.baomidou.mybatisplus.extension.service.IService;
import com.college.WordService.entity.Wordquery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FengXZ
 * @since 2021-05-31
 */
public interface WordcloudService extends IService<Wordcloud> {

    List<Wordquery> getList();
}
