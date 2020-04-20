import Vue from 'vue'
import VueRouter from 'vue-router'
import Records from '../components/Records.vue'
import SearchRecords from '../components/SearchRecords.vue'

//path
Vue.use(VueRouter)

const routes = [
        
        {
            path: '/records',
            component: Records
        },
        {
            path: '/searchrecords',
            component: SearchRecords
        }
            ]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
