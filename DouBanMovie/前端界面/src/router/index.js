import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from "views/Home"
import SellerPage from 'views/SellerPage'
import TrendPage from 'views/TrendPage'
import MapPage from 'views/MapPage'
import RankPage from 'views/RankPage'
import HotPage from 'views/HotPage'
import StockPage from 'views/StockPage'
import Login from '@/components/login/Login'
import home from '@/components/main/Home'
Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    name: "Home",
    component: home,
    children: [
      {
        path: "/main",
        name: "dashboard",
        meta: {
            title: '系统首页'
        },
        component: () => import (
        "@/components/main/Main.vue")
    }, 
    {
      path: "/movie",
        name: "movie",
        meta: {
            title: '电影表格'
        },
        component: () => import (
        "@/components/main/movie.vue")
    },
    {
      path: "/dre",
        name: "dre",
        meta: {
            title: '导演表格'
        },
        component: () => import (
        "@/components/main/dre.vue")
    }, {
      path: "/act",
        name: "act",
        meta: {
            title: '演员表格'
        },
        component: () => import (
        "@/components/main/act.vue")
    }, {
      path: "/hot",
        name: "hot",
        meta: {
            title: '热度表格'
        },
        component: () => import (
        "@/components/main/hot.vue")
    }, {
      path: "/score",
        name: "score",
        meta: {
            title: '评分表格'
        },
        component: () => import (
        "@/components/main/score.vue")
    }, {
      path: "/wordcloud",
        name: "wordcloud",
        meta: {
            title: '词云表格'
        },
        component: () => import (
        "@/components/main/wordcloud.vue")
    },

    ]
  },
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/home',
    component: Home
  },
  {
    path: '/sellerpage',
    component: SellerPage
  },
  {
    path: '/trendpage',
    component: TrendPage
  },
  {
    path: '/mappage',
    component: MapPage
  },
  {
    path: '/hotpage',
    component: HotPage
  },
  {
    path: '/stockpage',
    component: StockPage
  },
  {
    path: '/rankpage',
    component: RankPage
  }
]

const router = new VueRouter({
  routes
})

export default router