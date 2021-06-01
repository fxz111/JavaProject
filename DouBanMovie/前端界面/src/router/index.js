import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from "views/Home"
import SellerPage from 'views/SellerPage'
import TrendPage from 'views/TrendPage'
import MapPage from 'views/MapPage'
import RankPage from 'views/RankPage'
import HotPage from 'views/HotPage'
import StockPage from 'views/StockPage'

Vue.use(VueRouter)

const routes = [{
    path: '/',
    redirect: '/home'
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