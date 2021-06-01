package com.college.WordService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.college.WordService.entity.Wordcloud;
import com.college.WordService.entity.Wordquery;
import com.college.WordService.mapper.WordcloudMapper;
import com.college.WordService.service.WordcloudService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FengXZ
 * @since 2021-05-31
 */
@Service
public class WordcloudServiceImpl extends ServiceImpl<WordcloudMapper, Wordcloud> implements WordcloudService {

    @Override
    public List<Wordquery> getList() {
        List<Wordquery> res= new ArrayList<>();
        QueryWrapper<Wordcloud> wrapper = new QueryWrapper<>();
        List<Wordcloud> wordclouds = baseMapper.selectList(wrapper);
        for (int i = 0; i < wordclouds.size(); i++) {
            Wordquery wordquery = new Wordquery();
            wordquery.setName(wordclouds.get(i).getWord());
            wordquery.setValue(wordclouds.get(i).getNum());
            res.add(wordquery);
        }
        return res;
    }
}
