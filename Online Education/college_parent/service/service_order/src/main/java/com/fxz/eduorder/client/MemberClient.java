package com.fxz.eduorder.client;

import com.fxz.commonutils.orderVo.MemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface MemberClient {
    @GetMapping("/ucenter/member/getMemerOrder/{memberId}")
    public MemberOrder getMemberOrder(@PathVariable("memberId") String memberId);
}
