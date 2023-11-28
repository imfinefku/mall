<template>
  <div class="login_conn">
    <img style="width: 100%; hight: 100%" src="../assets/images/6666.png">
    <div class="login_box">
      <div style="width: 60%;height: 100%">
        <img src="../assets/images/65544.jpg" width="100%" height="100%" >
      </div>
      <div style=" background-color: #ffffff;width: 40%;height: 100%;text-align: center">
        <h1 style="margin-top: 50px;color: #409eff"></h1>
        <el-form class="form_login" :rules="loginRules" :model="user" ref="loginForm">
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user-solid" v-model="user.username"></el-input>
          </el-form-item>

          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-unlock" v-model="user.password" show-password>></el-input>
          </el-form-item>

          <el-form-item class="btns">
            <el-button type="primary" @click="login" style="width: 100%;">登录</el-button>
            <el-button type="info" @click="LoginReset" style="width: 100%;margin-left: 0px;margin-top: 5px" >重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>
import { initRouter } from '../router/index'

export default {
  data() {
    return {
      user: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 5, max: 15, message: '长度在 5 到 15 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 5, max: 15, message: '长度在 5 到 15 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    //   重置方面
    LoginReset() {
      this.user = {}
      this.$refs.loginForm.resetFields()
    },
    // 登录
    login() {
      this.$refs.loginForm.validate(async valid => {
        if (!valid) return
        const { data: res } = await this.$http.post('/mallSysUser/login', this.user)
        if (res.code !== 20000) return this.$message.error(res.message)
        // 存token
        window.sessionStorage.setItem('token', res.data.token)
        // console.log(res.data.modul)
        this.$store.commit('setmodueList', res.data.modul)
        this.$store.commit('setBtus', res.data.btu)
        this.$store.commit('setUserName', res.data.userName)
        initRouter()
        this.$router.push('/home')
        this.$message.success('登录成功')
      })
    }
  }
}
</script>

<style lang="less" scoped>
.login_conn {
  //background-image: url("../assets/ba.jpg");
  background-color: #1D1C2E;
  height: 100%;
  background-size: 100% 100%;
  box-sizing: border-box;
  overflow: hidden;
}

.login_box {
  width: 60%;
  height: 50%;
  position: absolute;
  left: 20%;
  top: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  margin-left: 30%;
  justify-content: flex-end;
  .img_box {
    //box-shadow: 0 0 10px #dddddd;
    height: 130px;
    width: 130px;
    padding: 10px;
    position: absolute;
    left: 50%;
    background-color: #ffffff;
  }
}

.btns {
  //display: flex;
  justify-content: flex-end;
  margin-top: 50px;
}

.form_login {
  position: absolute;
  bottom: 40px;
  width: 40%;
  padding: 0 20px;
  box-sizing: border-box;

}
</style>
