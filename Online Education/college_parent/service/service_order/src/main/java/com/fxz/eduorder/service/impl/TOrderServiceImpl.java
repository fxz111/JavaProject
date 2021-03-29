package com.fxz.eduorder.service.impl;

import com.fxz.commonutils.orderVo.CourseOrder;
import com.fxz.commonutils.orderVo.MemberOrder;
import com.fxz.eduorder.client.CourseClient;
import com.fxz.eduorder.client.MemberClient;
import com.fxz.eduorder.entity.TOrder;
import com.fxz.eduorder.mapper.TOrderMapper;
import com.fxz.eduorder.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-06
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Autowired
    private CourseClient courseClient;
    @Autowired
    private MemberClient memberClient;
    @Override
    public String createOrder(String courseId, String memberId) {
        //courseId => 课程信息
        CourseOrder courseOrder = courseClient.getCourseOrder(courseId);
        //member => 会员信息
        MemberOrder memberOrder = memberClient.getMemberOrder(memberId);
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseOrder.getTitle());
        order.setCourseCover(courseOrder.getCover());
        order.setTeacherName(courseOrder.getTeacherName());
        order.setTotalFee(courseOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(memberOrder.getMobile());
        order.setNickname(memberOrder.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
