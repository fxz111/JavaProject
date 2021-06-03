package com.college.ActService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.college.ActService.entity.Act;
import com.college.ActService.entity.Actmovie;
import com.college.ActService.entity.movie;
import com.college.ActService.mapper.ActmovieMapper;
import com.college.ActService.service.ActmovieService;
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
 * @since 2021-05-24
 */
@Service
public class ActmovieServiceImpl extends ServiceImpl<ActmovieMapper, Actmovie> implements ActmovieService {



    @Override
    public List<Act> getActMovie() {
        List<Act> res = new ArrayList<>();
        List<Actmovie> list = baseMapper.selectList(null);
        for (int i = 0; i < list.size(); i++) {
            Act dre = new Act();
            String movieActor = list.get(i).getMovieActor();
            dre.setName(movieActor);
            List<movie> movies = new ArrayList<>();
            QueryWrapper<Actmovie> wrapper = new QueryWrapper<>();
            wrapper.eq("movie_actor",movieActor);
            Actmovie actmovie = baseMapper.selectOne(wrapper);
            movie movie = new movie();
            movie.setName("bad");
            movie.setValue(actmovie.getBad());
            movies.add(movie);
            movie = new movie();
            movie.setName("mid");
            movie.setValue(actmovie.getMid());
            movies.add(movie);
            movie = new movie();
            movie.setName("good");
            movie.setValue(actmovie.getGood());
            movies.add(movie);
            dre.setChildren(movies);
            res.add(dre);
        }
        return res;
    }
}
