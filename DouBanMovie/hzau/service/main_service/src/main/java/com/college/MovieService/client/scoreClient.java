package com.college.MovieService.client;

import com.college.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("score-movie")
@Service
public interface scoreClient {
    @GetMapping("/ScoreService/scoremovie/getscore")
    public R getscore();
}
