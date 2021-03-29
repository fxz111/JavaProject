import request from '@/utils/request'
export default {
    //1 获取章节小节信息
    getChapterVideo(courseId) {
        return request({
            url: `/eduservice/chapter/getAllCV/${courseId}`,
            method: 'get'
          })
    },
    //添加
    addChapter(chapter) {
        return request({
            url: `/eduservice/chapter/addChapter`,
            method: 'post',
            data: chapter
          })
    },
    //修改
    updateChapter(chapter) {
        return request({
            url: `/eduservice/chapter/update`,
            method: 'post',
            data: chapter
          })
    },
    //删除
    deleteById(chapterId) {
        return request({
            url: `/eduservice/chapter/deleteById/${chapterId}`,
            method: 'delete'
          })
    },
    //id查询
    getById(chapterId){
        return request({
            url: `/eduservice/chapter/getById/${chapterId}`,
            method: 'get'
          })
    }

}