<template>
  <div class="com-container">
    <div class="com-chart" ref="mapRef"></div>
  </div>
</template>

<script>
import '@/components/report/world.js'
import { mapState } from 'vuex'
import axios from 'axios'

  export default {
    name: "Map",
    data() {
      return {
        // axios实例对象
        axiosInstance: null,
        // 图表的实例对象
        chartInstance: null,
        // 从服务器中获取的所有数据
        allData: null,
      };
    },
    computed: {
    ...mapState(['theme']),
    },
    watch: {
      theme() {
        // 销毁当前的图表
        this.chartInstance.dispose()
        // 以最新主题初始化图表对象
        this.initChart()
        // 屏幕适配
        this.screenAdapter()
      },
    },
    mounted() {
      this.getData();
    },
    methods: {
    async getData() {
        axios.get('http://localhost:8001/MovieService/movie/getAreaHot').then(response=>{
       //test = response.data.data.list
        this.allData = response.data.data.list
         //console.log("------",this.allData) 
         this.initChart(this.allData)
      })  
      },
      
      // 不同分辨率的响应式
    screenAdapter() {
      // 当前比较合适的字体大小
      const titleFontSize = (this.$refs.mapRef.offsetWidth / 100) * 3.6
      // 响应式的配置项
      const adapterOption = {
        title: {
          textStyle: {
            fontSize: titleFontSize,
          },
        },
        legend: {
          // 图例宽度
          itemWidth: titleFontSize / 2,
          // 图例高度
          itemHeight: titleFontSize / 2,
          // 间隔
          itemGap: titleFontSize / 2,
          textStyle: {
            fontSize: titleFontSize / 2,
          },
        },
      }
      // this.chartInstance.setOption(adapterOption)
      this.chartInstance.resize()
    },
    initChart(allData) {
      this.chartInstance = this.$echarts.init(this.$refs.mapRef,this.theme);
      

    // 把配置和数据放这里
    this.chartInstance.setOption({
        title: {
          text: '▎全球电影热度',
          left: 20,
          top: 20,
        },
        dataRange: {
            show: false,
            min: 0,
            max: 10000000,
            text: ['High', 'Low'],
            realtime: true,
            
            color: ['orangered', 'yellow', 'lightskyblue']
          },
        tooltip: {
            trigger: 'item'
          },
        geo: {
            map: 'world',
            label: {
              emphasis: {
                show: false
              }
            },
            zoom: 1.1,
            roam: true, //允许拖动和缩放
            silent: false,
          },
          series: [{
            type: 'map',
            mapType: 'world',
            // zoom: 1.18,
            name:'热度',
            geoIndex: 0,
            mapLocation: {
              y: 100
            },
            data:allData,

            symbolSize: 12,
            label: {
              normal: {
                show: false
              },
              emphasis: {
                show: false
              }
            },
            itemStyle: {
              normal: {
                // 地图的填充色
                areaColor: '#2a333d',
                borderColor: '#000'
              },
              emphasis: {
                borderColor: '#fff',
                borderWidth: 1,
                areaColor: '#2a333d',
              }
            }
          }],
        });
      }
    }
  }
</script>

<style lang="less" scoped>
</style>