import request from '@/utils/request'
export default{
    getList(page,limit,moviename) {
        return request({
          url: `MovieService/movie/getmovieList/${page}/${limit}`,
          method: 'post',
          data : moviename
        });
    },
}
