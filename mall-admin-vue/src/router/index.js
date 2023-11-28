import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'

import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import users from '../components/user/users.vue'
import personalCenter from '../components/user/personalCenter.vue'
import RoleList from '../components/role/RoleList.vue'
import RoleUpt from '../components/role/upt.vue'
import store from '@/store/index'
import Model from '../components/model/ModelList.vue'
import LogList from '../components/log/logList'
import MallGoodsMa from '../components/goods/MallGoods'
import MallGoodsTypeMa from '../components/goods/MallGoodsType'
import OrderListMaMap from '../components/orderList/MallOrder'
import OrderDetailMa from '../components/orderList/orderDetail'
import GoodsUserListMaMap from '../components/goodsUser/GoodsUser'
import GoodsUserDetailsMa from '../components/goodsUser/GoodsUserDetails'
import GoodsTopUpMa from '../components/topUp/GoodsTopUp'


Vue.use(VueRouter)

const usersRouter = { path: '/users', component: users }
const rolesRouter = { path: '/roles', component: RoleList }
const resumeRouter = { path: '/resume', component: RoleUpt }
const efaultModue = { path: '/', component: Welcome }
const modelRouter = { path: '/model', component: Model }
const logList = { path: '/logList', component: LogList }
const MallGoodsType = { path: '/MallGoodsType', component: MallGoodsTypeMa }
const MallGoods = { path: '/MallGoods', component: MallGoodsMa }
const OrderListMa = { path: '/orderList', component: OrderListMaMap }
const GoodsUserMa = { path: '/goodsUserList', component: GoodsUserListMaMap }
const GoodsTopUpList = { path: '/goodsTopUp', component: GoodsTopUpMa }


const udgmentAuthority = {
    'users': usersRouter,
    'roles': rolesRouter,
    'resume': resumeRouter,
    'model': modelRouter,
    'MallGoodsType':MallGoodsType,
    'MallGoods':MallGoods,
    'orderList':OrderListMa,
    'goodsUserList':GoodsUserMa,
    'goodsTopUp':GoodsTopUpList,
    '#': efaultModue,
    '/':efaultModue
}

const routes = [
    { path: '/login', component: Login },
    { path: '/', redirect: "/login" },
    {
        path: '/home',
        component: Home,
        redirect: '/welcome',
        children: [{
                path: "/welcome",
                component: Welcome
            },
            {
                path: "/personalCenter",
                component: personalCenter
            },
            {
                path: "/orderDetail",
                component: OrderDetailMa
            },
            {
                path: "/goodsUserDetails",
                component: GoodsUserDetailsMa
            }

        ]
    }
]

const router = new VueRouter({
    routes
})

router.beforeEach((to, from, next) => {
    // debugger
    if (to.path === '/login') return next();
    const tokenStr = window.sessionStorage.getItem("token")
    if (!tokenStr) return next('/login')
    next()
})

export function initRouter() {

    const AllRouters = router.options.routes
    const modueListAA = store.state.modueList

    modueListAA.forEach(itme => {
        itme.children.forEach(two => {
            two.children.forEach(upt => {
                const temp = udgmentAuthority[upt.menuPath]
                if (temp != undefined && temp != null){
                    AllRouters[2].children.push(temp)
                }

            })
        })
    })
    // console.log(AllRouters)
    router.addRoutes(AllRouters)
}

export default router
