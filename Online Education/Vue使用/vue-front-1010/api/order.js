import request from '@/utils/request'

export default {
    //生成订单
  createOrder(courseId) {
    return request({
      url: `/eduorder/order/createOrder/${courseId}`,
      method: 'post'
    })
  },
   //查询订单ById
   getOrder(orderId) {
    return request({
      url: `/eduorder/order/getOrder/${orderId}`,
      method: 'get'
    })
  },
   //生成二维码
   getPayLog(orderNo) {
    return request({
      url: `/eduorder/tpaylog/getPayLog/${orderNo}`,
      method: 'get'
    })
  },
   //查询状态
   QueryPayStatus(orderNo) {
    return request({
      url: `/eduorder/tpaylog/QueryPayStatus/${orderNo}`,
      method: 'get'
    })
  },
}