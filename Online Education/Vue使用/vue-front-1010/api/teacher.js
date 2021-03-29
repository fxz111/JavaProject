import request from '@/utils/request'

export default {
    //分页查询讲师
  getTeacherList(page,limit) {
    return request({
      url: `/eduservice/teacherFront/getFrontTeacher/${page}/${limit}`,
      method: 'post',
    })
  },
   //得到讲师详情ById
   getTeacherInfo(teacherId) {
    return request({
      url: `/eduservice/teacherFront/getTeacherFrontInfo/${teacherId}`,
      method: 'post',
    })
  }



}