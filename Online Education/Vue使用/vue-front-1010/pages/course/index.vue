<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">全部课程</span>
        </h2>
      </header>
      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl>
            <dt>
              <span class="c-999 fsize14">课程类别</span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li>
                  <a title="全部" href="#" @click.prevent="getAll()">全部</a>
                </li>
                <li v-for="(item,index) in subjectNestedList" :key="index" :class="{active:oneIndex==index}">
                  <a :title="item.title" href="#" @click="getOne(item.id,index)">{{item.title}}</a>
                </li>
                
              </ul>
            </dd>
          </dl>
          <dl>
            <dt>
              <span class="c-999 fsize14"></span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li v-for="(item,index) in subSubjectList" :key="index" :class="{active:twoIndex==index}">
                  <a :title="item.title" href="#" @click="getTwo(item.id,index)">{{item.title}}</a>
                </li>         
              </ul>
            </dd>
          </dl>
          <div class="clear"></div>
        </section>
        <div class="js-wrap">
          <section class="fr">
            <span class="c-ccc">
              <i class="c-master f-fM">1</i>/
              <i class="c-666 f-fM">1</i>
            </span>
          </section>
          <section class="fl">
            <ol class="js-tap clearfix">
              <li :class="{'current bg-orange':buyCountSort!=''}">
                <a title="关注度" href="#" @click="orderByCount()">关注度</a>
                <span :class="{hide:buyCountSort==''}">↓</span>
              </li>
              <li :class="{'current bg-orange':gmtCreateSort!=''}">
                <a title="最新" href="#" @click="orderByTime()">最新</a>
                <span :class="{hide:gmtCreateSort==''}">↓</span>
              </li>
              <li :class="{'current bg-orange':priceSort!=''}">
                <a title="价格" href="#" @click="orderByPrice()">价格&nbsp;
                  <span :class="{hide:priceSort==''}">↓</span>
                </a>
              </li>
            </ol>
          </section>
        </div>
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="data.total == 0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
          </section>
          <!-- /无数据提示 结束-->
          <article class="comm-course-list" v-if="data.total>0">
            <ul class="of" id="bna">
              <li v-for="item in data.items" :key="item.id">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img :src="item.cover" class="img-responsive" alt="听力口语">
                    <div class="cc-mask">
                      <a :href="'/course/'+item.id" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a :href="'/course/'+item.id" :title="item.title" class="course-title fsize18 c-333">{{item.title}}</a>
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span class="fr jgTag bg-green">
                      <i class="c-fff fsize12 f-fA">{{ item.price===0?'免费':'收费' }}</i>
                    </span>
                    <span class="fl jgAttr c-ccc f-fA">
                      <i class="c-999 f-fA">{{item.viewCount}}人学习</i>
                      |
                      <i class="c-999 f-fA">{{item.viewCount}}评论</i>
                    </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"></div>
          </article>
        </div>
        <!-- 公共分页 开始 -->
         <div>
      <div class="paging">
        <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
        <a
          :class="{undisable: !data.hasPrevious}"
          href="#"
          title="首页"
          @click.prevent="gotopage(1)">首</a>
        <a
          :class="{undisable: !data.hasPrevious}"
          href="#"
          title="前一页"
          @click.prevent="gotopage(data.current-1)">&lt;</a>
        <a
          v-for="page in data.pages"
          :key="page"
          :class="{current: data.current == page, undisable: data.current == page}"
          :title="'第'+page+'页'"
          href="#"
          @click.prevent="gotopage(page)">{{ page }}</a>
        <a
          :class="{undisable: !data.hasNext}"
          href="#"
          title="后一页"
          @click.prevent="gotopage(data.current+1)">&gt;</a>
        <a
          :class="{undisable: !data.hasNext}"
          href="#"
          title="末页"
          @click.prevent="gotopage(data.pages)">末</a>
        <div class="clear"/>
      </div>
    </div>
        <!-- 公共分页 结束 -->
      </section>
    </section>
    <!-- /课程列表 结束 -->
  </div>
</template>
<script>
import course from '@/api/course'
export default {
  data(){
    return{
        page:1, //当前页
      data:{},  //课程列表
      subjectNestedList: [], // 一级分类列表
      subSubjectList: [], // 二级分类列表
      searchObj: {}, // 查询表单对象
      oneIndex:-1,
      twoIndex:-1,
      buyCountSort:"",
      gmtCreateSort:"",
      priceSort:""
    }
  },
  created(){
    this.initCourse()
    this.initSubject()

  },
  methods:{
    getAll(){
      this.gotopage(1)
         this.twoIndex = -1
      this.oneIndex = -1
       this.subSubjectList = []
       this.buyCountSort = ""
       this.gmtCreateSort = ""
       this.priceSort = ""
    },
    initCourse(){
      course.getCourseList(1,8,this.searchObj).then(res=>{
        this.data = res.data.data
      })
    },
    //查询课程数据
    initSubject(){
      course.getAllSubject().then(res=>{
        this.subjectNestedList = res.data.data.list
      })
    },
    //分页
    gotopage(page){
      course.getCourseList(page,8,this.searchObj).then(res=>{
        this.data = res.data.data
      })
    },
    //点一级分类查询二级分类
    getOne(OneId,index){
       this.oneIndex = index
      this.twoIndex = -1
      this.searchObj.subjectId = ""
      this.subSubjectList = []
    this.searchObj.subjectParentId = OneId
      this.gotopage(1)  
      for(let i = 0;i<this.subjectNestedList.length;i++){
        var OneSubject = this.subjectNestedList[i]
        if(OneId == OneSubject.id){
          //如果相同就获取二级
          this.subSubjectList = OneSubject.children
        }
      this.searchObj.subjectParentId = ""
      }

    },
    getTwo(TwoId,index){
      //使样式生效
      this.twoIndex = index
      this.searchObj.subjectId = TwoId
      this.gotopage(1)
       this.searchObj.subjectId = ""
    },
    orderByCount(){
      this.buyCountSort = "1"
      this.gmtCreateSort = ""
      this.priceSort = ""
      this.searchObj.buyCountSort = this.buyCountSort
      this.searchObj.gmtCreateSort = this.gmtCreateSort
      this.searchObj.priceSort = this.priceSort
      this.gotopage(1)
    },
    orderByTime(){
      this.buyCountSort = ""
      this.gmtCreateSort = "1"
      this.priceSort = ""
      this.searchObj.buyCountSort = this.buyCountSort
      this.searchObj.gmtCreateSort = this.gmtCreateSort
      this.searchObj.priceSort = this.priceSort
      this.gotopage(1)
    },
    orderByPrice(){
      this.buyCountSort = ""
      this.gmtCreateSort = ""
      this.priceSort = "1"
      this.searchObj.buyCountSort = this.buyCountSort
      this.searchObj.gmtCreateSort = this.gmtCreateSort
      this.searchObj.priceSort = this.priceSort
      this.gotopage(1)
    },

  }
};
</script>
<style scoped>
  .active {
    background: #bdbdbd;
  }
  .hide {
    display: none;
  }
  .show {
    display: block;
  }
</style>