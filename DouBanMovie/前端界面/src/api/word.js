import request from '@/utils/request'
export default{
    getList() {
        return request({
          url: `WordService/wordcloud/getwordList`,
          method: 'get',
        });
    },
}