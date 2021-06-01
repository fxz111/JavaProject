<template>
  <div class="com-container">
    <div class="com-chart" ref="stockRef"></div>
  </div>
</template>

<script>
import echarts from "echarts";
import wordCloud from '@/components/report/wordcloud.js'
import { mapState } from 'vuex'
import axios from 'axios'

  export default {
    name: "Stock",
    data () {
      return {
        // 图表的实例对象
        chartInstance: null,
        // 从服务器中获取的所有数据
        allData: null,
        worddata: []
      }
    },
    computed: {
    ...mapState(['theme']), 
    },
    mounted(){
      this.getData();
      this.initChart();
    },
    watch: {
      theme() {
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
    methods: {
      async getData() {
        axios.get('http://localhost:8007/WordService/wordcloud/getwordcloud').then(response=>{
       //test = response.data.data.list
        var allData = response.data.data.list
        console.log("------:",this.allData) 
        this.initChart(allData)
        //  this.initChart(this.allData)
      })  
      },
      initChart(alldata) {
        this.chartInstance = this.$echarts.init(this.$refs.stockRef,this.theme)
        const option = {
          title: {
            text: '▎词 云',
            left: 20,
            top: 20,
          },
          series: [
            {
              type: "wordCloud",
              //调整词之间的距离
              gridSize: 10,
              //用来调整字的大小范围
              sizeRange: [14,28],
              // rotationRange: [-45, 0, 45, 90],
              // rotationRange: [ 0,90],
              rotationRange: [0, 0],
              //随机生成字体颜色
              // maskImage: maskImage,
              textStyle: {
                normal: {
                  color: function() {
                    return (
                      "rgb(" +
                      Math.round(Math.random() * 255) +
                      ", " +
                      Math.round(Math.random() * 255) +
                      ", " +
                      Math.round(Math.random() * 255) +
                      ")"
                    );
                  }
                }
              },
              //位置相关设置
              // Default to be put in the center and has 75% x 80% size.
              left: "center",
              top: "center",
              right: null,
              bottom: null,
              width: "200%",
              height: "200%",
              //数据
              data: alldata
            }
          ]
        };
      this.chartInstance.setOption(option);
    },
    // 不同分辨率的响应式
    screenAdapter() {
      // 当前比较合适的字体大小
      const titleFontSize = (this.$refs.stockRef.offsetWidth / 100) * 3.6
      console.log('***:',titleFontSize)
      // 响应式的配置项
      const adapterOption = {
        title: {
          textStyle: {
            fontSize: titleFontSize,
          },
        },
        series :{
          sizeRange: [titleFontSize*1.2,titleFontSize*2.1]
        }
      }
      this.chartInstance.setOption(adapterOption)
      this.chartInstance.resize()
    },
    }
  }
</script>

<style lang="less" scoped>
</style>