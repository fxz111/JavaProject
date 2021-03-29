package com.fxz.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("service-order")
@Component
public interface OrderClient {
    //根据课程id和用户id查询订单状态，1为支付，0为未支付
    @GetMapping("/eduorder/order/getStatus/{courseId}/{memberId}")
    public Boolean getStatus(@PathVariable("courseId") String courseId,
                             @PathVariable("memberId") String memberId);
}
