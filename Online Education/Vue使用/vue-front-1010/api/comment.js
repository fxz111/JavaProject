import request from '@/utils/request'
export default{
    getPageComment(page,limit,courseId){
        return request({
            url: `/eduservice/educomment/pageComment/${page}/${limit}/${courseId}`,
            method: 'post'
        })
    },
    addComment(comment){
        return request({
            url: `/eduservice/educomment/addComment`,
            method: 'post',
            data: comment
        })
    }
}