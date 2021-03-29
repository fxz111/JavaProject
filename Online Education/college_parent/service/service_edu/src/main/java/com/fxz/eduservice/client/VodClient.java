package com.fxz.eduservice.client;

import com.fxz.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("service-vod")
@Component
public interface VodClient {
    @DeleteMapping("vodservice/video/deleteAliyunById/{id}")
    public R deleteById(@PathVariable("id") String id);

    @DeleteMapping("vodservice/video/deleteAliyunVideos")
    public R deleteVideos(@RequestParam("videoList") List<String> videoList);
}
