package com.college.MovieService.client;

import com.college.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("num-movie")
@Service
public interface numClient {
    @GetMapping("/NumService/promovie/getnum")
    public R getnum();
}
