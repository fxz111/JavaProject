package com.fxz.eduorder.controller;


import com.fxz.commonutils.R;
import com.fxz.eduorder.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-06
 */
@RestController
@RequestMapping("/eduorder/tpaylog")
@CrossOrigin
public class TPayLogController {
    @Autowired
    private TPayLogService payLogService;
    @GetMapping("getPayLog/{orderNo}")
    public R getPayLog(@PathVariable String orderNo){
        Map map = payLogService.getPayLog(orderNo);
        System.out.println("======="+map);
        return R.ok().data(map);
    }
    //获取支付状态
    @GetMapping("QueryPayStatus/{orderNo}")
    public R QueryPayStatus(@PathVariable String orderNo){
        Map<String,String> map = payLogService.QueryPayStatus(orderNo);
        System.out.println("++++++++"+map);
        if (map==null){
            return R.error().message("支付出错!");
        }
        if (map.get("trade_state").equals("SUCCESS")){
            payLogService.updatePayOrder(map);
            return R.ok().message("支付成功!");
        }
        return R.ok().code(25000).message("支付中...");
    }

}

