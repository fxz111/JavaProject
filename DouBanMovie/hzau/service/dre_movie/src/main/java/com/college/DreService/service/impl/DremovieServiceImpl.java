package com.college.DreService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.college.DreService.entity.Dre;
import com.college.DreService.entity.Dremovie;
import com.college.DreService.entity.movie;
import com.college.DreService.mapper.DremovieMapper;
import com.college.DreService.service.DremovieService;
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
public class DremovieServiceImpl extends ServiceImpl<DremovieMapper, Dremovie> implements DremovieService {

    @Override
    public List<Dre> getDreMovie() {
        List<Dre> res = new ArrayList<>();
        List<Dremovie> list = baseMapper.selectList(null);
        for (int i = 0; i < list.size(); i++) {
            Dre dre = new Dre();
            String movieDirector = list.get(i).getMovieDirector();
            dre.setName(movieDirector);
            List<movie> movies = new ArrayList<>();
            QueryWrapper<Dremovie> wrapper = new QueryWrapper<>();
            wrapper.eq("movie_director",movieDirector);
            Dremovie dremovie = baseMapper.selectOne(wrapper);
            movie movie = new movie();
            movie.setName("bad");
            movie.setValue(dremovie.getBad());
            movies.add(movie);
            movie = new movie();
            movie.setName("mid");
            movie.setValue(dremovie.getMid());
            movies.add(movie);
            movie = new movie();
            movie.setName("good");
            movie.setValue(dremovie.getGood());
            movies.add(movie);
            dre.setChildren(movies);
            res.add(dre);
        }
        return res;
    }
}
