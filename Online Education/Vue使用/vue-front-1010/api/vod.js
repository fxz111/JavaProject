import request from '@/utils/request'

export default {
    //分页查询讲师
  getPlayAuth(id) {
    return request({
      url: `/vodservice/video/getPlayAuth/${id}`,
      method: 'get',
    })
  },
}