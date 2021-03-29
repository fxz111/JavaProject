import request from '@/utils/request'

export default {
    //查询前n条banner数据
  getListBanner() {
    return request({
      url: '/cms/bannerfront/getAllBanner',
      method: 'get'
    })
  }
}