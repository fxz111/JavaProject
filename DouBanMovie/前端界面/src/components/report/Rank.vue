<template>
  <div class="com-container">
    <div class="title" @click="showMenu = !showMenu" :style="comStyle">
      <span class="before-icon">▎</span>
      <span>{{ showTitle }}</span>
      <span class="iconfont title-icon" :style="comStyle">&#xe6eb;</span>
      <div class="select-con">
        <div class="select-item" v-show="showMenu" @click.prevent="handleSelect(item.key)" v-for="item in selectTypes" :key="item.key">
          {{ item.text }}
        </div>
      </div>
    </div>
    <div class="com-chart" ref="rankRef"></div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'

export default {
  name: 'Rank',
  data() {
    return {
      // 图表的实例对象
      chartInstance: null,
      // 从服务器中获取的所有数据
      allData: null,
      // 柱形图 区域缩放起点值
      startValue: 0,
      // 柱形图结 区域缩放终点值
      endValue: 9,
      // 定时器
      timerId: null,
      // 是否显示可选项
      showMenu: false,
      // 默认显示的数据类型
      activeName: 'map',
      flag: 0 ,
    }
  },
  created() {
    this.$socket.registerCallBack('rankData', this.getData)
  },
  computed: {
    ...mapState(['theme']),
    // 点击过后需要显示的数组
    selectTypes() {   
      if (!this.allData) 
      {
        return []
      }
      {
      // 过度掉当前选中的类别
      return this.allData.type.filter(item => item.key !== this.activeName) 
      }
    },
    // 显示的标题
    showTitle() {
      if (!this.allData)
      {
        return ''
      }
      {
        return this.allData[this.activeName].data.title
      }
    },
    
  },
  watch: {
    theme() {
      console.log('主题切换了')
      // 销毁当前的图表
      this.chartInstance.dispose()
      // 以最新主题初始化图表对象
      this.initChart()
      // 屏幕适配
      this.screenAdapter()
      // 渲染数据
      this.updateChart()
    },
  },
  mounted() {
    this.initChart()
    // this.getData()
    this.$socket.send({
      action: 'getData',
      socketType: 'rankData',
      chartName: 'rank',
      value: '',
    })
    window.addEventListener('resize', this.screenAdapter)
    // 主动触发 响应式配置
    this.screenAdapter()
  },
  destroyed() {
    window.removeEventListener('resize', this.screenAdapter)
    clearInterval(this.timerId1)
    this.$socket.unRegisterCallBack('rankData')
  },
  methods: {
    // 初始化图表的方法
    initChart() {
      this.chartInstance = this.$echarts.init(this.$refs.rankRef, this.theme)

      const initOption = {
        title: {
          // text: '▎电影类型数量排行',
          left: 20,
          top: 20,
        },
        grid: {
          top: '40%',
          left: '5%',
          right: '5%',
          bottom: '5%',
          // 把x轴和y轴纳入 grid
          containLabel: true,
        },
        tooltip: {
          show: true,
        },
        xAxis: {
          type: 'category',
        },
        yAxis: {
          value: 'value',
        },
        series: [
          {
            type: 'bar',
            label: {
              show: true,
              position: 'top',
              color: 'white',
              rotate: 30,
            },
          },
        ],
      }
      this.chartInstance.setOption(initOption)

      // 鼠标经过关闭 动画效果
      this.chartInstance.on('mouseover', () => {
        clearInterval(this.timerId)
      })
      // 鼠标离开 开启动画效果
      this.chartInstance.on('mouseout', () => {
        this.startInterval()
      })
    },
    // 发送请求，获取数据
    async getData(res) {
      // const { data: res } = await this.$http.get('/rank')
      axios.get('http://localhost:8001/MovieService/movie/getall').then(response=>{
          this.allData = response.data.data
          console.log('++++++',this.allData.map.data.list)
          
          this.updateChart()
          // 开始自动切换
          this.startInterval()      
      }) 
    },
    // 更新图表配置项
    updateChart() {
      // 渐变色数组
      const colorArr = [
        ['#0BA82C', '#4FF778'],
        ['#2E72BF', '#23E5E5'],
        ['#5052EE', '#AB6EE5'],
      ]
      // const colorArr = [
      //   ['#b8e994', '#079992'],
      //   ['#82ccdd', '#0a3d62'],
      //   ['#f8c291', '#b71540'],
      // ]
      if ( this.flag == 0 )
      {
        this.allData.map.data.list.sort((a, b) => b.value - a.value)
              
          // 所有省份组成的数组 
        var provinceInfo = this.allData.map.data.list.map(item => item.name)
        // 所有省份对应的销售金额
        var valueArr = this.allData.map.data.list.map(item => item.value)
      }
      else{
           // 所有省份组成的数组 
        var provinceInfo = this.allData.seller.data.list.map(item => item.name)
        // 所有省份对应的销售金额
        var valueArr = this.allData.seller.data.list.map(item => item.value)
        this.allData.seller.data.list.sort((a, b) => b.value - a.value)
      }
      
      const dataOption = {
        xAxis: {
          data: provinceInfo,
        },
        dataZoom: {
          // 区域缩放组件
          show: false,
          startValue: this.startValue,
          endValue: this.endValue,
        },
        series: [
          {
            data: valueArr,
            itemStyle: {
              color: arg => {
                let targetColorArr = null

                if (arg.value > 35) {
                  targetColorArr = colorArr[0]
                } else if (arg.value > 15) {
                  targetColorArr = colorArr[1]
                } else {
                  targetColorArr = colorArr[2]
                }

                return new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  // 0%
                  { offset: 0, color: targetColorArr[0] },
                  // 100%
                  { offset: 1, color: targetColorArr[1] },
                ])
              },
            },
          },
        ],
      }
      this.chartInstance.setOption(dataOption)
    },
    // 根据图标容器的宽度 计算各属性、标签、元素的大小
    screenAdapter() {
      const titleFontSzie = (this.$refs.rankRef.offsetWidth / 100) * 3.6

      const adapterOption = {
        title: {
          textStyle: {
            fontSize: titleFontSzie,
          },
        },
        series: [
          {
            barWidth: titleFontSzie,
            itemStyle: {
              barBorderRadius: [titleFontSzie / 2, titleFontSzie / 2, 0, 0],
            },
          },
        ],
      }
      this.chartInstance.setOption(adapterOption)
      this.chartInstance.resize()
    },
    // 改变柱形图 区域缩放起始与终点值的函数
    startInterval() {
 
          // 如果存在则关闭
      this.timerId && clearInterval(this.timerId)
      
      this.timerId = setInterval(() => {
        this.startValue++
        this.endValue++
        if (this.endValue > this.allData.map.data.list.length - 1&&this.flag==0) {
          this.startValue = 0
          this.endValue = 9
        }
        if(this.endValue > this.allData.seller.data.list.length - 1&&this.flag==1){
          this.startValue = 0
          this.endValue = 9
        }
        this.updateChart()
      }, 750)
      
      
      
    },
    
    // 当前选中的类型
    handleSelect(currentType) {
      // console.log("选中的当前值:",currentType)
      this.activeName = currentType
      if (this.activeName == 'map')
      this.flag = 0
      else
      this.flag = 1

      this.updateChart()
    },
  },
}
</script>

<style lang="less" scoped>
.title {
  position: absolute;
  left: 50px;
  top: 20px;
  z-index: 999;
  color: white;
  cursor: pointer;

  .before-icon {
    position: absolute;
    left: -20px;
  }
  .title-icon {
    margin-left: 10px;
  }
}
</style>