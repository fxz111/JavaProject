import request from '@/utils/request'
export default{
    getList() {
        return request({
          url: `ScoreService/scoremovie/getscoreList`,
          method: 'get',
        });
    },
}