package com.fxz.eduservice.client;

import com.fxz.commonutils.R;
import com.fxz.eduservice.entity.vo.MemberWeb;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-ucenter")
@Component
public interface MemberClient {
    @GetMapping("/ucenter/member/getInfoById/{id}")
    public MemberWeb getInfoById(@PathVariable("id") String id);
}
