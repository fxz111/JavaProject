package com.fxz.staservice.client;

import com.fxz.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //查询某一天的注册人数
    @GetMapping("/ucenter/member/getRegisterCount/{time}")
    public R getRegisterCount(@PathVariable("time") String time);
}
