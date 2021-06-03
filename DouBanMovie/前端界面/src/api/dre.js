import request from '@/utils/request'
export default{
    getList() {
        return request({
          url: `DreService/dremovie/getdreList`,
          method: 'get',
        });
    },
}