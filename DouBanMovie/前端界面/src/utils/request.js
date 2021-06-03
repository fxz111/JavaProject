// import axios from 'axios'
// import Qs from 'qs'

// // 创建一个axios实例
// const service = axios.create({
//   baseURL: 'http://localhost:9001/',
//   // transformRequest:[function(data){
//   //   return Qs.stringify(data)
//   // }],
//   timeout: 3000
// });

// 添加请求拦截器
// service.interceptors.request.use(config => {
//   // 打开可能会报错 x-token不被允许
//   // config.headers['x-token'] = 'x-xxxxxxxxxxxxxxxxxxx';
//   return config;
// },error => {
//   // 请求错误做些事
//   return Promise.reject(error);
// });

// // 添加相应拦截器
// service.interceptors.response.use(response => {
//   const res = response.data;
//   // 如果返回的状态码不是200 就主动报错
//   if(res.state != 200){
//     return Promise.reject(res.msg || 'error');
//   }
//   return response;
// },error => {
//   // 返回接口的错误信息
//   return Promise.reject(error);
// })

// export default service
import axios from 'axios';

const service = axios.create({
    // process.env.NODE_ENV === 'development' 来判断是否开发环境
    // easy-mock服务挂了，暂时不使用了
   baseURL: 'http://localhost:9001/',
    timeout: 500000
});

service.interceptors.request.use(
    config => {
        return config;
    },
    error => {
        console.log(error);
        return Promise.reject();
    }
);

service.interceptors.response.use(
    response => {
        if (response.status === 200) {
            return response.data;
        } else {
            Promise.reject();
        }
    },
    error => {
        console.log(error);
        return Promise.reject();
    }
);

export default service;
