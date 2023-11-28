import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        modueList: JSON.parse(window.sessionStorage.getItem('modueList') || '[]'),
        btus: window.sessionStorage.getItem('btus') == null ? "" : window.sessionStorage.getItem('btus'),
        userName:window.sessionStorage.getItem('userName')
    },
    mutations: {
        setmodueList(start, data) {
            // console.log(data)
            start.modueList = data
            sessionStorage.setItem('modueList', JSON.stringify(data))
        },
        setBtus(start, data) {
            // debugger
            // console.log(data)
            start.btus = data
            sessionStorage.setItem('btus', data)
        },
        setUserName(start, data) {
            // debugger
            // console.log(data)
            start.userName = data
            sessionStorage.setItem('userName', data)
        }
    },
    actions: {},
    modules: {}
})