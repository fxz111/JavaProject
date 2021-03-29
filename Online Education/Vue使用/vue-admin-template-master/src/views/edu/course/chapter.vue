<template>

  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="最终发布"/>
    </el-steps>

    <el-button type="primary" @click="openDialog()">添加章节</el-button>

   <ul class="chanpterList">
        <li
            v-for="chapter in chapterList"
            :key="chapter.id">
            <p>
                {{ chapter.title }}

                <span class="acts">
                    <el-button style="" type="primary" @click="OpenVideo(chapter.id)">添加小节</el-button>
                    <el-button style="" type="success" @click="openEditChatper(chapter.id)">编辑</el-button>
                    <el-button type="danger" @click="removeChapter(chapter.id)">删除</el-button>
                </span>
            </p>

            <!-- 视频 -->
            <ul class="chapterList videoList">
                <li
                    v-for="video in chapter.children"
                    :key="video.id">
                    <p>{{ video.title }}

                <span class="acts">
                    
                     <el-button style="" type="success" @click="EditVideo(video.id)">编辑</el-button>
                    <el-button type="danger" @click="removeVideo(video.id)">删除</el-button>
                </span>
                    </p>
                </li>
            </ul>
        </li>
    </ul>

    <div>
        <el-button @click="previous">上一步</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
    </div>
    <!-- 添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
        <el-form :model="chapter" label-width="120px">
            <el-form-item label="章节标题">
                <el-input v-model="chapter.title"/>
            </el-form-item>
            <el-form-item label="章节排序">
                <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
        </div>
    </el-dialog>
    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
  <el-form :model="video" label-width="120px">
    <el-form-item label="课时标题">
      <el-input v-model="video.title"/>
    </el-form-item>
    <el-form-item label="课时排序">
      <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
    </el-form-item>
    <el-form-item label="是否免费">
      <el-radio-group v-model="video.free">
        <el-radio :label="true">免费</el-radio>
        <el-radio :label="false">默认</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="上传视频">
       <el-upload
            :on-success="handleVodUploadSuccess"
            :on-remove="handleVodRemove"
            :before-remove="beforeVodRemove"
            :on-exceed="handleUploadExceed"
            :file-list="fileList"
            :action="BASE_API+'/vodservice/video/uploadVideo'"
            :limit="1"
            class="upload-demo">
        <el-button size="small" type="primary">上传视频</el-button>
        <el-tooltip placement="right-end">
            <div slot="content">最大支持1G，<br>
                支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br>
                GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br>
                MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br>
                SWF、TS、VOB、WMV、WEBM 等视频格式上传</div>
            <i class="el-icon-question"/>
        </el-tooltip>
        </el-upload>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
    <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
  </div>
</el-dialog>


  </div>
</template>
<script>
import chapter from '@/api/chapter'
import video from '@/api/video'
export default {
    data() {
        return {
            saveBtnDisabled:false,
            courseId:'',
            chapterList:[],
            dialogChapterFormVisible: false,
            dialogVideoFormVisible:false,
            chapter:{
              title:'',
              sort:0,
              courseId:''
            },
            video:{
                title: '',
                sort: 0,
                free: 0,
                videoSourceId: '',
                videoOriginalName:'',
                id:'',
            },
            fileList: [],//上传文件列表
            BASE_API: process.env.BASE_API 
        }
    },
    created() {
      if (this.$route.params && this.$route.params.id){
        this.courseId = this.$route.params.id
        this.getAll();
      }
    },
    methods:{
       //上传视频成功调用的方法
        handleVodUploadSuccess(response, file, fileList) {
            //上传视频id赋值
            this.video.videoSourceId = response.data.videoId
            //上传视频名称赋值
            this.video.videoOriginalName = file.name
        },
        
        handleUploadExceed() {
            this.$message.warning('想要重新上传视频，请先删除已上传的视频')
        },
       //删除之前方法
       beforeVodRemove(file,fileList){
          return this.$confirm(`确定移除 ${ file.name }？`);
       },
       //删除方法
       handleVodRemove(){
             //调用接口的删除视频的方法
            video.deleteAliyun(this.video.videoSourceId)
                .then(response => {
                    //提示信息
                    this.$message({
                        type: 'success',
                        message: '删除视频成功!'
                    });
                    //把文件列表清空
                    this.fileList = []
                    //把video视频id和视频名称值清空
                    //上传视频id赋值
                    this.video.videoSourceId = ''
                    //上传视频名称赋值
                    this.video.videoOriginalName = ''
                })
       },
      EditVideo(id){
        this.dialogVideoFormVisible = true
        video.GetById(id).then(res=>{
          this.video = res.data.video
          console.log(this.video)
        })
      },
      OpenVideo(id){
        this.video.id = ''
        this.video.title = ''
        this.video.sort = 0
        this.video.free =0
        this.video.videoSourceId = ''
        this.dialogVideoFormVisible = true
        this.video.chapterId = id
        console.log(this.video)
      },
      removeVideo(id){
        this.$confirm('此操作将删除小节, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {  //点击确定，执行then方法
                //调用删除的方法
                video.deleteVideo(id)
                    .then(response =>{//删除成功
                    //提示信息
                    this.$message({
                        type: 'success',
                        message: '删除小节成功!'
                    });
                    //刷新页面
                    this.getAll()
                })
            }) //点击取消，执行catch方法
      },
      addVideo(){
        this.video.courseId = this.courseId
        video.addVideo(this.video).then(res=>{
          this.dialogVideoFormVisible = false
          this.$message({
                        type: 'success',
                        message: '添加小节成功!'
                    });
          this.getAll()
        })
      },
      updateVideo(){
        video.updateVideo(this.video).then(res=>{
          this.dialogVideoFormVisible = false
          this.$message({
                        type: 'success',
                        message: '修改小节成功!'
                    });
          this.getAll()
        })

      },
      saveOrUpdateVideo(){
        //console.log(this.video)
        if(!this.video.id){
          this.addVideo();
        }else{
          this.updateVideo()
          }
        
      },
      removeChapter(id){
        this.$confirm('此操作将删除章节, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {  //点击确定，执行then方法
                //调用删除的方法
                chapter.deleteById(id)
                    .then(response =>{//删除成功
                    //提示信息
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                    //刷新页面
                    this.getAll()
                })
            }) 
      },
      openEditChatper(id){
        this.dialogChapterFormVisible = true
        chapter.getById(id).then(res=>{
          this.chapter = res.data.chapter
        })

      },
      openDialog(){
        this.dialogChapterFormVisible = true
        this.chapter.title = ''
        this.chapter.sort = 0
      },
      addChapter(){
        this.chapter.courseId = this.courseId
        chapter.addChapter(this.chapter).then(res=>{
          this.dialogChapterFormVisible = false
          this.$message({
                        type: 'success',
                        message: '添加章节信息成功!'
                    });
          this.getAll()
        })

      },
      //修改
      updateChapter(){
        chapter.updateChapter(this.chapter).then(res=>{
          this.dialogChapterFormVisible = false
          this.$message({
                        type: 'success',
                        message: '修改章节信息成功!'
                    });
          this.getAll()
        })

      },
      saveOrUpdate(){
        if(!this.chapter.id){
          this.addChapter()
        }else{
          this.updateChapter()
        }
      },
      //更新
      getAll(){
        chapter.getChapterVideo(this.courseId).then(res=>{
          this.chapterList = res.data.list
        })
      },
        previous() {
            this.$router.push({path:'/course/info/'+this.courseId})
        },
        next() {
            //跳转到第二步
            this.$router.push({path:'/course/publish/'+this.courseId})
        }
    }
}
</script>
<style scoped>
.chanpterList{
    position: relative;
    list-style: none;
    margin: 0;
    padding: 0;
}
.chanpterList li{
  position: relative;
}
.chanpterList p{
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}
.chanpterList .acts {
    float: right;
    font-size: 14px;
}

.videoList{
  padding-left: 50px;
}
.videoList p{
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #DDD;
}

</style>