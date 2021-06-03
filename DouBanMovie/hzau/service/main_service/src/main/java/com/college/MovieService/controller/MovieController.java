package com.college.MovieService.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.college.MovieService.entity.Movie;
import com.college.MovieService.service.MovieService;
import com.college.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-05-24
 */
@RestController
@RequestMapping("/MovieService/movie")
@CrossOrigin()
@Api(description = "电影接口")
public class MovieController {
    @Autowired
    private MovieService movieService;

    //电影分页查询
    @ApiOperation(value = "电影分页查询")
    @PostMapping ("getmovieList/{page}/{limit}")
    public R getmovieList(@PathVariable long page,
                          @PathVariable long limit,
                          @RequestBody(required = false) Movie movie){
        Page<Movie> pageCourse = new Page<>(page,limit);
        movieService.pageQuery(pageCourse,movie);
        long total = pageCourse.getTotal();//总记录数
        List<Movie> records = pageCourse.getRecords(); //数据list集合
        return R.ok().data("total",total).data("list",records);
    }

    //地区热度（地图）
    @ApiOperation(value = "地区热度")
    @GetMapping("getAreaHot")
    public R getAreaHot(){
        List<String> areas = movieService.getArea();
        List<Map<String,Object>> list = movieService.getAreaHot(areas);
        return R.ok().data("list",list);
    }

    //各年份电影总数
    @ApiOperation(value = "各年份电影总数")
    @GetMapping("getScoreEveryYear")
    public R getScoreEveryYear(){
        List<String> year = movieService.getYear();
        List<Map<String,Object>> list = movieService.getYearScore(year);
        return R.ok().data("list",list);
    }

    //导演影片质量和演员影片质量
    @ApiOperation(value = "导演影片质量and演员影片质量")
    @GetMapping("getActDre")
    public R getActDre(){
        R list1 = movieService.getDre();
        R list2 = movieService.getAct();
        List<Map<String,Object>> list = new ArrayList<>();
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("key","map");
        map1.put("text","导演影片分析");
        list.add(map1);
        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("key","seller");
        map2.put("text","演员影片分析");
        list.add(map2);
        return R.ok().data("type",list).data("map",list1).data("seller",list2);
    }

    //类型数量and区域数量
    @ApiOperation(value = "类型数量and区域数量")
    @GetMapping("getall")
    public R getall(){
        List<String> type = movieService.getType();
        List<String> area = movieService.getArea();
        List<Map<String,Object>> list1 = movieService.getTypeNums(type);
        List<Map<String,Object>> list2 = movieService.getAreaNums(area);
        List<Map<String,Object>> list = new ArrayList<>();
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("key","map");
        map1.put("text","类型电影产量");
        list.add(map1);
        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("key","seller");
        map2.put("text","区域电影产量");
        list.add(map2);
        return R.ok().data("type",list).data("map",R.ok().data("title","类型电影产量").data("list",list1)).data("seller",R.ok().data("title","区域电影产量").data("list",list2));
    }


    //电影年度热度and评分and产量
    @ApiOperation(value = "电影年度热度and评分and产量")
    @GetMapping("getAll")
    public R getAll(){
        R list1 = movieService.getTypeYearScore();
        R list2 = movieService.getTypeYearHot();
        R list3 = movieService.getTypeYearPro();
        List<String> year = new ArrayList<>();
        for (int i = 1000; i <= 1011; i++) {
            year.add(String.valueOf(i*2));
        }
        List<Map<String,Object>> list = new ArrayList<>();
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("key","map");
        map1.put("text","电影年度评分");
        list.add(map1);
        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("key","seller");
        map2.put("text","电影年度热度");
        list.add(map2);
        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("key","common");
        map3.put("text","电影年度产量");
        list.add(map3);
        return R.ok().data("year",year).data("type",list).data("map",list1).data("seller",list2).data("common",list3);
    }



}

