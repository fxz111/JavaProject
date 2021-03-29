package com.fxz.eduorder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxz.eduorder.entity.TOrder;
import com.fxz.eduorder.entity.TPayLog;
import com.fxz.eduorder.mapper.TPayLogMapper;
import com.fxz.eduorder.service.TOrderService;
import com.fxz.eduorder.service.TPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.eduorder.utils.HttpClient;
import com.fxz.servicebase.exceptionhandler.GuliException;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-06
 */
@Service
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements TPayLogService {
    @Autowired
    private TOrderService orderService;
    @Override
    public Map getPayLog(String orderNo) {
        try{
            //根据orderNo得到订单详细信息
            QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            TOrder order = orderService.getOne(wrapper);
            //将订单信息放进map集合
            Map m = new HashMap();
            m.put("appid","wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", order.getCourseTitle()); //课程标题
            m.put("out_trade_no", orderNo); //订单号
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");
            m.put("trade_type", "NATIVE");

            //创建client，并将map转换成xml格式放入client并发送，返回xml字符串
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            client.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();
            String content = client.getContent();
            //将xml字符串转换成map集合
            Map<String, String> StringMap = WXPayUtil.xmlToMap(content);
            Map map = new HashMap();
            map.put("order_No",orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", StringMap.get("result_code"));  //返回二维码操作状态码
            map.put("code_url", StringMap.get("code_url"));        //二维码地址
            //返回map
            return map;


        }catch (Exception e){
            throw new GuliException(20001,"生成二维码失败");
        }


    }

    @Override
    public Map<String, String> QueryPayStatus(String orderNo) {
       try{
           Map m = new HashMap();
           m.put("appid", "wx74862e0dfcf69954");
           m.put("mch_id", "1558950191");
           m.put("out_trade_no", orderNo);
           m.put("nonce_str", WXPayUtil.generateNonceStr());
           //发送client
           HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
           client.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
           client.setHttps(true);
           client.post();
           String content = client.getContent();
           Map<String, String> stringMap = WXPayUtil.xmlToMap(content);
           return stringMap;

       }catch (Exception e){
            throw new GuliException(20001,"查询出错");
       }
    }

    @Override
    public void updatePayOrder(Map<String, String> map) {
        String orderNo = map.get("out_trade_no");
        //根据orderNo查询order
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        TOrder order = orderService.getOne(wrapper);
        //跟新order
        order.setStatus(1);
        orderService.updateById(order);
        //添加订单
        TPayLog payLog = new TPayLog();
        payLog.setOrderNo(orderNo);//订单号
        payLog.setPayTime(new Date()); //订单完成时间
        payLog.setPayType(1);//支付类型 1微信
        payLog.setTotalFee(order.getTotalFee());//总金额(分)
        payLog.setTradeState(map.get("trade_state"));//支付状态
        payLog.setTransactionId(map.get("transaction_id")); //流水号
        payLog.setAttr(JSONObject.toJSONString(map));
        baseMapper.insert(payLog);

    }
}
