package com.college.MovieService.client;

import com.college.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("act-movie")
@Service
public interface actClient {
    @GetMapping("/ActService/actmovie/getScoreEveryAct")
    public R getScoreEveryAct();
}
