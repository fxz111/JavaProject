package com.college.WordService.controller;


import com.college.WordService.entity.Wordcloud;
import com.college.WordService.entity.Wordquery;
import com.college.WordService.service.WordcloudService;
import com.college.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-05-31
 */
@CrossOrigin
@RestController
@RequestMapping("/WordService/wordcloud")
public class WordcloudController {
    @Autowired
    private WordcloudService wordcloudService;
    @GetMapping("getwordcloud")
    public R getwordcloud(){
        List<Wordquery> list = wordcloudService.getList();
        return R.ok().data("list",list);
    }
    @GetMapping("getwordList")
    public R getwordList(){
        List<Wordcloud> list = wordcloudService.list(null);
        return R.ok().data("list",list);
    }
}

