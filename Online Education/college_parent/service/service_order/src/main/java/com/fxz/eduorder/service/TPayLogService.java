package com.fxz.eduorder.service;

import com.fxz.eduorder.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-06
 */
public interface TPayLogService extends IService<TPayLog> {

    Map getPayLog(String orderNo);

    Map<String, String> QueryPayStatus(String orderNo);

    void updatePayOrder(Map<String, String> map);
}
