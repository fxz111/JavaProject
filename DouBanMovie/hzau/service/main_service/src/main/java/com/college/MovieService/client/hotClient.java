package com.college.MovieService.client;

import com.college.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("hot-movie")
@Service
public interface hotClient {
    @GetMapping("/HotService/hotmovie/gethot")
    public R gethot();
}
