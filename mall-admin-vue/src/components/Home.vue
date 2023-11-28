<template>
    <el-container class="home_con">
        <el-header>
            <div @click="fhHome">
                <span>电商商城管理端</span>
            </div>
            <div class="slsls">
                <el-dropdown trigger="click">

                    <div class="el-dropdown-link">
                        <!--                               src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3782931768,770985946&fm=26&gp=0.jpg"-->

                        <el-avatar
                                src="https://p26-tt.byteimg.com/large/pgc-image/64ea9f995b954df88fad1fb138338831"
                        ></el-avatar>
                        <span>{{userNameAll}}</span>
                        <i class="el-icon-arrow-down el-icon--right"></i>
                    </div>

                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item @click.native="getPersonalCenter">个人中心</el-dropdown-item>
                        <el-dropdown-item @click.native="loginOut">退出</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
            <!--      <el-button type="info" @click="loginOut">退出</el-button>-->
        </el-header>
        <el-container>
            <el-aside :width="isColl ? '64px':'200px'">
                <div class="toggle_clike" @click="coll">|||</div>
                <!-- 菜单 -->
                <el-menu
                        background-color="#252241"
                        text-color="#fff"
                        active-text-color="#459DF7"
                        :unique-opened="true"
                        :collapse="isColl"
                        :collapse-transition="false"
                        :router="true"
                        :default-active="pathth"
                >

                    <el-menu-item index="/welcome" key="welcome">
                        <i class="el-icon-house"></i>
                        <span slot="title">首页</span>
                    </el-menu-item>
                    <el-submenu :index="'/'+mode.id" v-for="mode in modelist" :key="mode.id">
                        <!-- 一级目录 -->
                        <template slot="title">
                            <i :class="mode.icon"></i>
                            <span> {{mode.menuName}}</span>
                        </template>
                        <!-- 二级目录 -->
                        <el-menu-item
                                :index="'/'+itme.menuPath"
                                v-for="itme in mode.children"
                                :key="itme.id"
                                @click="getPath('/'+itme.menuPath)"
                        >
                            <template slot="title">
                                <i :class="itme.icon"></i>
                                <span>{{itme.menuName}}</span>
                            </template>
                        </el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <el-main>
                <router-view></router-view>
            </el-main> 
        </el-container>
    </el-container>
</template>
<script>
  import { mapState } from 'vuex'
  import personalCenter from './user/personalCenter'

  export default {
    methods: {
      loginOut() {
        window.sessionStorage.clear()
        window.location.reload
        this.$router.push('/login')
      },
      getModeList() {
        this.pathth = window.sessionStorage.getItem('path')
      },
      coll() {
        this.isColl = !this.isColl
      },
      getPath(pathAll) {
        window.sessionStorage.removeItem('path')
        window.sessionStorage.setItem('path', pathAll)
        this.pathth = pathAll
      },
      getPersonalCenter() {
        console.log(window.localStorage.getItem('userId'))
        this.$router.push('/personalCenter')
      },
      fhHome() {
        this.$router.push('/welcome')
      }
    },
    created() {
      this.getModeList()
      this.modelist = this.modueList
      this.userNameAll = this.userName

    },
    computed: {
      ...mapState(['modueList', 'userName'])
    },
    data() {
      return {
        modelist: [],
        isColl: false,
        pathth: '',
        userNameAll: ''
      }
    }

  }
</script>
<style lang="less" scoped>
    .el-header {
        background-color: #fff;
        
        display: flex;
        justify-content: space-between;
        padding-left: 0;
        align-items: center;
        color: #000;
        font-size: 20px;

        div {
            display: flex;
            align-items: center;
           

            span {
                margin-left: 15px;
            }
        }
    }

    .el-aside {
        background-color: #252241;

        .el-menu {
            border-right: none;
        }
    }

    .el-main {
        background-color: #dadadd;

    }

    .home_con {
        height: 100%;
    }

    .toggle_clike {
        //background-color: #485068;
        background-color: #040229;
        font-size: 10px;
        line-height: 24px;
        color: #ffffff;
        text-align: center;
        letter-spacing: 0.2em;
        cursor: pointer;
    }

    .el-dropdown-link {
        display: flex;
        align-items: center;
        margin: 0;
        padding: 0;
        color: #5f87ce;

        i {
            color: white;
        }

        /*span{*/
        /*    white-space: nowrap;*/
        /*    text-overflow: ellipsis;*/
        /*    overflow: hidden;*/
        /*}*/
    }

    .slsls {
        width: 10%;
        display: flex;
        justify-content: center;
    }
</style>
