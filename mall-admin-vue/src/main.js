import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import axios from 'axios'
import store from './store'
import echarts from 'echarts'


Vue.config.productionTip = false
//请求拦截器
axios.interceptors.request.use(config => {
  // console.log(config)
  config.headers.Token = window.sessionStorage.getItem('token')
  return config
})
axios.interceptors.response.use(
  response => {
    // console.log(response)
    // debugger
    if (response.data.code == 50008) {
      Vue.prototype.$message({
        type: 'error',
        message: '身份已过期，请重新登录'
      })
      window.sessionStorage.clear()
      router.push('/login')
    }
      if (response.data.code == 50013) {
      axios.post('/mallSysUser/uptToken'
        , window.sessionStorage.getItem('token')).then(res => {
        const data = res.data
        // console.log(data)
        if (data.code == 20000) {
          // response.headers.Token = data.token
          console.log(data)
          window.sessionStorage.setItem('token', data.data.token)
          window.location.reload();
          return response
        } else {

          Vue.prototype.$message({
            type: 'error',
            message: '身份已过期，请重新登录'
          })
          window.sessionStorage.clear()
          router.push('/login')
        }

      })
      return response
    }

    if (response.data.code == 50014) {
      debugger
      // $message.error("token失效")


      Vue.prototype.$message({
        type: 'error',
        message: '以在其他地方登录'
      })
      window.sessionStorage.clear()
      router.push('/login')
      return response

    }
    const token = response.headers.tokens
    if (token != null) {
      window.sessionStorage.setItem('token', token)
    }

    return response


  }
)

Vue.prototype.$http = axios
axios.defaults.baseURL = 'http://127.0.0.1:8001/sys'
Vue.prototype.$sunPath = axios.defaults.baseURL

Vue.prototype.$echarts = echarts
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')


