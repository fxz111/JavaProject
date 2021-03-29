import request from '@/utils/request'

export default {
  //条件分页课程查询的方法
  getCourseList(page,limit,search) {
    return request({
      url: `/eduservice/coursefront/getFrontCourse/${page}/${limit}`,
      method: 'post',
      data: search
    })
  },
  //查询所有分类的方法
  getAllSubject() {
    return request({
      url: '/eduservice/subject/getAllSubject',
      method: 'get'
    })
  },
  //得到课程详细信息
  getCourseInfo(courseId){
    return request({
        url: `/eduservice/coursefront/getFrontCourseInfo/${courseId}`,
        method: 'get',
      })
  }
}