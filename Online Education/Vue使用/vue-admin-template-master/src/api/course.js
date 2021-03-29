import request from '@/utils/request'
export default {
    //1 添加课程信息
    addCourseInfo(courseInfo) {
        return request({
            url: '/eduservice/course/addCourseInfo',
            method: 'post',
            data:courseInfo
          })
    },
    //2 查询所有讲师
    getListTeacher() {
        return request({
            url: '/eduservice/eduteacher/findAll',
            method: 'get'
          })
    },
    //Id查询
    getById(id) {
        return request({
            url: '/eduservice/course/getCourseInfo/'+id,
            method: 'get'
          })
    },
    //修改课程信息
    updateCourse(courseInfo) {
        return request({
            url: '/eduservice/course/updateCourseInfo',
            method: 'post',
            data: courseInfo
          })
    },
    //课程确认信息
    getPublishCourseInfo(id) {
        return request({
            url: '/eduservice/course/getPublishCourseInfo/'+id,
            method: 'get',
          })
    },
    //发布课程
    publihCourse(id){
        return request({
            url: '/eduservice/course/publishCourse/'+id,
            method: 'post',
          })
    },
    //
    getListCourse(current,limit,courseQuery){
        return request({
            url: `/eduservice/course/getCourseList/${current}/${limit}`,
            method: 'post',
            data: courseQuery
          })
    },
    //删除
    deleteById(id){
        return request({
            url: `/eduservice/course/deleteById/${id}`,
            method: 'delete',
          })
    }
}