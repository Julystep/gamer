import { createApp } from 'vue'
import App from './App.vue'
import Detail from './components/Detail.vue'
import Picture from './components/PictureShow.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { createRouter, createMemoryHistory } from 'vue-router'

const routes = [
    {path: '/detail', component: Detail},
    {path: '/', component: Picture}
]

const router = createRouter({
    history: createMemoryHistory(),
    routes
})

const main = createApp(App)
main.use(router)
main.use(ElementPlus)
main.mount('#app')
