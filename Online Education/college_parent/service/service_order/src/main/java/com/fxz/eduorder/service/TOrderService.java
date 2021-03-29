package com.fxz.eduorder.service;

import com.fxz.eduorder.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-06
 */
public interface TOrderService extends IService<TOrder> {

    String createOrder(String courseId, String memberId);
}
