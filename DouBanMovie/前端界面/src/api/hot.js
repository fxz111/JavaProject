import request from '@/utils/request'
export default{
    getList() {
        return request({
          url: `HotService/hotmovie/gethotList`,
          method: 'get',
        });
    },
}