import request from '@/utils/request'

export default{
    //讲师列表
    GetTeacherPage(current,limit,teacherQuery){
        return request({
            url: `/eduservice/eduteacher/pageQuery/${current}/${limit}`,
            method: 'post',
            data: teacherQuery
          })

    },
    //删除讲师ById
    deleteById(id){
        return request({
            url:`/eduservice/eduteacher/deleteById/${id}`,
            method: 'delete',
        })
    },
    //新建讲师
    saveTeacher(teacher){
        return request({
            url:`/eduservice/eduteacher/Insert`,
            method: 'post',
            data: teacher
        })
    },
    //修改讲师
    updateTeacher(teacher){
        return request({
            url:`/eduservice/eduteacher/update`,
            method: 'post',
            data: teacher
        })
    },
    //getById
    getTeacherInfo(id){
        return request({
            url:`/eduservice/eduteacher/findById/${id}`,
            method: 'get',
        })
    },
}
