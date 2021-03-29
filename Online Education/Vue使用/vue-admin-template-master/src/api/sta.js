import request from '@/utils/request'
export default {
    //1 生成统计数据
    createSta(time) {
        return request({
            url: `/staservice/sta/registerCount/${time}`,
            method: 'post',
          })
    },
    //图表显示
    showSta(search){
        return request({
            url: `/staservice/sta/showData/${search.type}/${search.begin}/${search.end}`,
            method: 'get',
          })
    }
}
