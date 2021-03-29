<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程详情 开始 -->
    <section class="container">
      <section class="path-wrap txtOf hLh30">
        <a href="#" title class="c-999 fsize14">首页</a>
        \
        <a href="#" title class="c-999 fsize14">{{courseWebInfo.subjectLevelOne}}</a>
        \
        <span class="c-333 fsize14">{{courseWebInfo.subjectLevelTwo}}</span>
      </section>
      <div>
        <article class="c-v-pic-wrap" style="height: 357px;">
          <section class="p-h-video-box" id="videoPlay">
            <img :src="courseWebInfo.cover" :alt="courseWebInfo.title" class="dis c-v-pic">
          </section>
        </article>
        <aside class="c-attr-wrap">
          <section class="ml20 mr15">
            <h2 class="hLh30 txtOf mt15">
              <span class="c-fff fsize24">{{courseWebInfo.title}}</span>
            </h2>
            <section class="c-attr-jg">
              <span class="c-fff">价格：</span>
              <b class="c-yellow" style="font-size:24px;">￥{{courseWebInfo.price}}</b>
            </section>
            <section class="c-attr-mt c-attr-undis">
              <span class="c-fff fsize14">主讲： {{courseWebInfo.teacherName}}&nbsp;&nbsp;&nbsp;</span>
            </section>
            <section class="c-attr-mt of">
              <span class="ml10 vam">
                <em class="icon18 scIcon"></em>
                <a class="c-fff vam" title="收藏" href="#" >收藏</a>
              </span>
            </section>
            <section v-if="isbuyCourse || Number(courseWebInfo.price) === 0" class="c-attr-mt">
              <a  title="立即观看" class="comm-btn c-btn-3">立即观看</a>
            </section>
             <section v-else class="c-attr-mt">
              <a @click="createOrder()" title="立即购买" class="comm-btn c-btn-3">立即购买</a>
            </section>
          </section>
        </aside>
        <aside class="thr-attr-box">
          <ol class="thr-attr-ol ">
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">购买数</span>
                <br>
                <h6 class="c-fff f-fM mt10">{{courseWebInfo.buyCount}}</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">课时数</span>
                <br>
                <h6 class="c-fff f-fM mt10">{{courseWebInfo.lessonNum}}</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">浏览数</span>
                <br>
                <h6 class="c-fff f-fM mt10">{{courseWebInfo.viewCount}}</h6>
              </aside>
            </li>
          </ol>
        </aside>
        <div class="clear"></div>
      </div>
      <!-- /课程封面介绍 -->
      <div class="mt20 c-infor-box">
        <article class="fl col-7">
          <section class="mr30">
            <div class="i-box">
              <div>
                <section id="c-i-tabTitle" class="c-infor-tabTitle c-tab-title">
                  <a name="c-i" class="current" title="课程详情">课程详情</a>
                </section>
              </div>
              <article class="ml10 mr10 pt20">
                <div>
                  <h6 class="c-i-content c-infor-title">
                    <span>课程介绍</span>
                  </h6>
                  <div class="course-txt-body-wrap">
                    <section class="course-txt-body">
                      <p v-html="courseWebInfo.description">{{courseWebInfo.description}}</p>
                    </section>
                  </div>
                </div>
                <!-- /课程介绍 -->
                <div class="mt50">
                  <h6 class="c-g-content c-infor-title">
                    <span>课程大纲</span>
                  </h6>
                  <section class="mt20">
                    <div class="lh-menu-wrap">
                      <menu id="lh-menu" class="lh-menu mt10 mr10">
                        <ul>
                          <!-- 文件目录 -->
                          <li class="lh-menu-stair" v-for="chapter in chapterVoList" :key="chapter.id">
                            <a href="javascript: void(0)" :title="chapter.title" class="current-1">
                              <em class="lh-menu-i-1 icon18 mr10"></em>{{chapter.title}}
                            </a>
                           <ol class="lh-menu-ol" style="display: block;">
                              <li class="lh-menu-second ml30" v-for="video in chapter.children" :key="video.id">
                                <a :href="'/player/'+video.videoSourceId" target="_blank">
                                  <span class="fr">
                                    <i class="free-icon vam mr10">免费试听</i>
                                  </span>
                                  <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em>{{video.title}}
                                </a>
                              </li>
                           </ol>
                         </li>
                        </ul>
                      </menu>
                    </div>
                  </section>
                </div>
                <!-- /课程大纲 -->
              </article>
            </div>
          </section>
        </article>
        <aside class="fl col-3">
          <div class="i-box">
            <div>
              <section class="c-infor-tabTitle c-tab-title">
                <a title href="javascript:void(0)">主讲讲师</a>
              </section>
              <section class="stud-act-list">
                <ul style="height: auto;">
                  <li>
                    <div class="u-face">
                      <a href="#">
                        <img :src="courseWebInfo.avatar" width="50" height="50" alt>
                      </a>
                    </div>
                    <section class="hLh30 txtOf">
                      <a class="c-333 fsize16 fl" href="#">{{courseWebInfo.teacherName}}</a>
                    </section>
                    <section class="hLh20 txtOf">
                      <span class="c-999">{{courseWebInfo.intro}}</span>
                    </section>
                  </li>
                </ul>
              </section>
            </div>
          </div>
        </aside>
        <div class="clear"></div>
      </div>
    </section>
    <!-- /课程详情 结束 -->

<div class="mt50 commentHtml"><div>
     <h6 class="c-c-content c-infor-title" id="i-art-comment">
       <span class="commentTitle">课程评论</span>
     </h6>
     <section class="lh-bj-list pr mt20 replyhtml">
       <ul>
         <li class="unBr">
           <aside class="noter-pic">
             <img width="50" height="50" class="picImg" src="~/assets/photo/customer/03.jpg" >
             </aside>
           <div class="of">
             <section class="n-reply-wrap">
               <fieldset>
                 <textarea name="" v-model="comment.content" placeholder="输入您要评论的文字" id="commentContent"></textarea>
               </fieldset>
               <p class="of mt5 tar pl10 pr10">
                 <span class="fl "><tt class="c-red commentContentmeg" style="display: none;"></tt></span>
                 <input type="button" @click="addComment()" value="回复" class="lh-reply-btn">
               </p>
             </section>
           </div>
         </li>
       </ul>
     </section>
     <section class="">
         <section class="question-list lh-bj-list pr">
           <ul class="pr10">
             <li v-for="(comment,index) in data.items" :key="index">
                 <aside class="noter-pic">
                   <img width="50" height="50" class="picImg" :src="comment.avatar">
                   </aside>
                 <div class="of">
                   <span class="fl"> 
                   <font class="fsize12 c-blue"> 
                     {{comment.nickname}}</font>
                   <font class="fsize12 c-999 ml5">评论：</font></span>
                 </div>
                 <div class="noter-txt mt5">
                   <p>{{comment.content}}</p>
                 </div>
                 <div class="of mt5">
                   <span class="fr"><font class="fsize12 c-999 ml5">{{comment.gmtCreate}}</font></span>
                 </div>
               </li>
             </ul>
         </section>
       </section>
       <!-- 公共分页 开始 -->
       <div class="paging">
           <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
           <a
           :class="{undisable: !data.hasPrevious}"
           href="#"
           title="首页"
           @click.prevent="gotoPage(1)">首</a>
           <a
           :class="{undisable: !data.hasPrevious}"
           href="#"
           title="前一页"
           @click.prevent="gotoPage(data.current-1)">&lt;</a>
           <a
           v-for="page in data.pages"
           :key="page"
           :class="{current: data.current == page, undisable: data.current == page}"
           :title="'第'+page+'页'"
           href="#"
           @click.prevent="gotoPage(page)">{{ page }}</a>
           <a
           :class="{undisable: !data.hasNext}"
           href="#"
           title="后一页"
           @click.prevent="gotoPage(data.current+1)">&gt;</a>
           <a
           :class="{undisable: !data.hasNext}"
           href="#"
           title="末页"
           @click.prevent="gotoPage(data.pages)">末</a>
           <div class="clear"/>
       </div>
       <!-- 公共分页 结束 -->
     </div>
   </div>
  </div>
</template>

<script>
import course from '@/api/course'
import comment from '@/api/comment'
import order from '@/api/order'
export default {
 data(){
    return{
      data:{},
      courseWebInfo:{},
      chapterVoList:{},
      courseId:'',
      page:1,
      limit:4,
      total:10,
      comment:{},
      isbuyCourse:false,
    }
 },
  created(){
    this.courseId = this.$route.params.id
    this.getCourseInfo();
    this.getCommentPage()
  },
  methods:{
    createOrder(){
      order.createOrder(this.courseId).then(res=>{
       this.$router.push({path:'/order/'+res.data.data.orderNo})
      })
    },
    getCourseInfo(){
      course.getCourseInfo(this.courseId).then(res=>{
          if(res.data.success){
            this.courseWebInfo = res.data.data.courseWebInfo
           this.chapterVoList = res.data.data.chapterVoList
           this.isbuyCourse = res.data.data.status
          }else{
            this.$message({
                        type: 'warning',
                        message: '请先登录!'
                    });
              this.$router.push({path:'/login'})
          }
           
        console.log(res.data.data)
      })
  },

  getCommentPage(){
    comment.getPageComment(this.page,this.limit,this.courseId).then(res=>{
      this.data = res.data.data
      //console.log(this.data)
    })
  },
  gotoPage(page){
    comment.getPageComment(page,this.limit,this.courseId).then(res=>{
      this.data = res.data.data
    })
  },
  addComment(){
    this.comment.courseId = this.courseId
    this.comment.teacherId = this.courseWebInfo.teacherId
    comment.addComment(this.comment).then(res=>{
      console.log(res.data)
      if(res.data.success){
        this.comment.content = ''
        this.getCommentPage()
      }else{
         this.$message({
                        type: 'warning',
                        message: '请先登录!'
                    });
      }
    })
  }
  }
};
</script>
