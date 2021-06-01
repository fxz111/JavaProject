package com.college.MovieService.client;

import com.college.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("dre-movie")
@Service
public interface dreClient {
    @GetMapping("/DreService/dremovie/getScoreEveryDrc")
    public R getScoreEveryDrc();
}
