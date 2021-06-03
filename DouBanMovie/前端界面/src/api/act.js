import request from '@/utils/request'
export default{
    getList() {
        return request({
          url: `ActService/actmovie/getactList`,
          method: 'get',
        });
    },
}