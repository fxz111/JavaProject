package com.fxz.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxz.commonutils.JwtUtils;
import com.fxz.commonutils.R;
import com.fxz.eduorder.entity.TOrder;
import com.fxz.eduorder.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-06
 */
@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
public class TOrderController {
    @Autowired
    private TOrderService orderService;
    @PostMapping("createOrder/{courseId}")
    public R createOrder(@PathVariable String courseId,
                         HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo  = orderService.createOrder(courseId,memberId);
        return R.ok().data("orderNo",orderNo);

    }
    @GetMapping("getOrder/{orderId}")
    public R getOrder(@PathVariable String orderId){
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        TOrder order = orderService.getOne(wrapper);
        return R.ok().data("order",order);
    }
    //根据课程id和用户id查询订单状态，1为支付，0为未支付
    @GetMapping("getStatus/{courseId}/{memberId}")
    public Boolean getStatus(@PathVariable String courseId,
                       @PathVariable String memberId){
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);
        int count = orderService.count(wrapper);
        if(count==0){
            //未找到对应的成功订单，返回false
            return false;
        }else{
            //找到对应订单，返回true
            return true;
        }

    }


}

