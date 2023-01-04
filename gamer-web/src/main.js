import { createApp } from 'vue'
import App from './App.vue'
import Detail from './components/Detail.vue'
import Picture from './components/PictureShow.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { createRouter, createMemoryHistory } from 'vue-router'
import axios from './axiosInstance.js'
import token from './token.js'
import * as echarts from "echarts"

const routes = [
    {path: '/detail', component: Detail, name: 'Detail'},
    {path: '/', component: Picture}
]

const router = createRouter({
    history: createMemoryHistory(),
    routes
})


const main = createApp(App)
main.config.globalProperties.$axios = axios
main.config.globalProperties.$token = token
main.config.globalProperties.$echarts = echarts
main.use(router)
main.use(ElementPlus)
main.mount('#app')
