<!--个人中心-->
<template>
  <div class="about" id="about" name="about">
    <div class="about-header">
      <div class="about-title">
        <i class="el-icon-tickets" style="color: #ff6700;"></i>
        个人中心
      </div>
    </div>
    <div v-if="wh" class="about-content">
      <div class='bode_main main-ka'>
        <div class='center_zx'>
          <div class='msmsm'>
            <div class='ksk'>
              <img src="../assets/imgs/667788.jpg" style="width: 120px;height: 120px;border-radius: 50%">
              <!--              {{ goodsUserInfo.username }}-->
              <p style='color: #1d1c2e;font-size: 20px'>{{ goodsUserInfo.username }}</p>
            </div>
            <el-row style='margin: 20px;color:#000;'>
              <p>{{ goodsUserInfo.gmtCreate }}</p>
              <div style="text-align: center; margin-top: 20px">
                <el-button type='danger' size='mini' @click="Tes">退出登录</el-button>
              </div>
            </el-row>
          </div>
        </div>
        <div class='center_right'>
          <el-tabs>
            <el-tab-pane label='基本信息'>
              <div class='orderMap'>
                <table >
                  <tr>
                    <td height='40' class='orderKey'>用户名:</td>
                    <td class='orderValue'>{{ goodsUserInfo.username }}</td>
                    <td class='orderKey'>手机号:</td>
                    <td class='orderValue'>{{ goodsUserInfo.mobile }}</td>
                  </tr>
                  <tr>
                    <td class='orderKey'>状态:</td>
                    <td :class="goodsUserInfo.mgState===true?'orderValueY orderValue':'orderValueW orderValue'">
                      {{ goodsUserInfo.mgState === true ? '启用' : '禁用' }}
                    </td>
                    <td height='40' class='orderKey'>余额:</td>
                    <td :class="goodsUserInfo.money>0?'orderValueY orderValue':'orderValueW orderValue'">
                      {{ goodsUserInfo.money }}
                    </td>
                  </tr>
                </table>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>


      <div class='bode_main main-ka ' style="margin-top: 20px">
        <div class="sun_left">
          <ul>
            <li>
              <el-button type="text" icon="el-icon-edit" @click="aaa('修改密码')">修改密码</el-button>
            </li>
            <li>
              <el-button type="text" icon="el-icon-s-finance" @click="aaa('充值')">充值</el-button>
            </li>
            <li>
              <el-button type="text" icon="el-icon-s-marketing" @click="aaa('/order')">我的订单</el-button>
            </li>
            <!-- <li>
              <el-button type="text" icon="el-icon-s-order" @click="aaa('闲置')">发布闲置</el-button>
            </li> -->
            <li>
              <el-button type="text" icon="el-icon-shopping-cart-1" @click="aaa('/shoppingCart')">购物车</el-button>
            </li>
            <li>
              <el-button type="text" icon="el-icon-star-on" @click="aaa('/collect')">我的收藏</el-button>
            </li>
            <!-- <li>
              <el-button type="text" icon="el-icon-phone" @click="aaa('求购')">发布求购</el-button>
            </li> -->
            <!-- <li>
              <el-button type="text" icon="el-icon-question" @click="aaa('其他')">疑问</el-button>
            </li> -->
            <!-- <li>
              <el-button type="text"></el-button>
            </li> -->
          </ul>
        </div>
        <div class="sun_right gdt" style="color: #005cc5">

          <div v-if="xg">
            <p style='font-size: 20px ;color: #1b1f23;margin: 10px;font-weight:bold'>修改密码</p>
            <el-form ref="form" :model="xgPassword" label-width="80px">
              <el-form-item label="旧密码">
                <el-input size="mini" v-model="xgPassword.dqPassword"></el-input>
              </el-form-item>
              <el-form-item label="新密码">
                <el-input size="mini" v-model="xgPassword.xqPassword"></el-input>
              </el-form-item>

              <el-form-item label="确定密码">
                <el-input size="mini" v-model="xgPassword.qdPassword"></el-input>
              </el-form-item>
            </el-form>
            <div style="text-align: center; margin-top: 20px">
              <el-button size='mini'>清空</el-button>
              <el-button type='primary' size='mini' @click="changePassword">提交</el-button>
            </div>
          </div>
          <div v-if="cz">
            <p style='font-size: 20px ;color: #1b1f23;margin: 10px;font-weight:bold'>充值</p>
            <el-form ref="form" :model="czForm" label-width="80px">
              <el-form-item label="充值金额">
                <el-input size="mini" v-model="czForm.goodsTopUpMoney"></el-input>
              </el-form-item>
    
            </el-form>
            <div style="text-align: center; margin-top: 20px">
              <el-button type='primary' size='mini' @click="recharge">提交</el-button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%">
      <h5 style="font-size: 30px;color: #0366d6">暂未开通,敬请期待!!!!!!!!!</h5>
      <img
          :src="$target+'/schoolMall/home/jqqd.png'"
          width="200"
          height="200"
      >
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>
<script>
import {mapActions} from "vuex";
export default {
  data() {
    return {
      goodsUserInfo: {},
      czForm: {},
      xgPassword: {},
      cz: true,
      xg: false,
      dialogVisible: false,
      wh: true,
      arr: []
    }

  },
  mounted() {
    this.initUser()
    this.sendIdle()
  },
  methods: {
    ...mapActions(["setUser", "setShowLogin", "setShoppingCart"]),
    aaa(res) {
      this.czForm = {}
      this.xgPassword = {}
      if (res === '充值') {
        this.cz = true
        this.xg = false
      } else if (res === '修改密码') {
        this.cz = false
        this.xg = true
      } else if (res === '其他') {
        this.dialogVisible = true
      } else {
        this.cz = true
        this.xg = false
        if (res === '闲置') {
          this.$router.push({path: '/addIdle', query: {isType: 0}})
        } else if (res === '求购') {
          this.$router.push({path: '/addIdle', query: {isType: 1}})
        } else {
          this.$router.push({path: res})
        }

      }

    },
    Tes() {
      this.visible = false;
      // 清空本地登录信息
      localStorage.setItem("user", "");
      localStorage.setItem("trolleyAmount", "0");
      localStorage.setItem("token", "");
      // 清空vuex登录信息
      this.setUser("");
      this.notifySucceed("成功退出登录");
      this.$router.push({path: '/'})
    },
    initUser() {
      this.$axios
          .get("/user/getByCarryMoneyDetail"
          )
          .then(res => {
            if (res.data.code === 20000) {
              this.goodsUserInfo = res.data.data.dataInfo
            }

          })
          .catch(err => {
            return Promise.reject(err);
          });

    },
    recharge() {
      this.$axios
          .post("/topUp/saveOrUpdateTopUp", this.czForm
          )
          .then(res => {
            if (res.data.code === 20000) {
              this.czForm = {}
              // 添加充值成功
              this.notifySucceed("已提交审核请稍后");
            } else {
              // 添加收藏失败
              this.notifyError("系统繁忙");
            }

          })
          .catch(err => {
            return Promise.reject(err);
          });
    },
    sendIdle() {
      this.$axios
          .post("/idle/getPublishedGoods").then(res => {
        if (res.data.code === 20000) {
          this.arr = res.data.data.info
          console.log(res.data.data.info)
        }
      })
    },
    changePassword() {
      this.$axios
          .post("/user/changePassword", this.xgPassword
          ).then(res => {

        if (res.data.code === 20000) {
          this.visible = false;
          // 清空本地登录信息
          localStorage.setItem("user", "");
          localStorage.setItem("trolleyAmount", "0");
          localStorage.setItem("token", "");
          // 清空vuex登录信息
          // this.setUser("");
          this.notifySucceed("修改密码成功");
          this.notifySucceed("退出登录");
          // location.reload()
          this.$router.push({path: '/'})
          location.reload()
        }

      })
    }
  },
  activated() {
    this.sendIdle()
  }

};
</script>
<style lang="less" scoped>
.gdt {
  overflow-y: scroll;
}

.gdt::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.3);
  border-radius: 5px;
  background-color: rgb(29, 28, 46);
}

.gdt::-webkit-scrollbar {
  width: 5px;
  background-color: rgba(0, 0, 0, 0);
}

.gdt::-webkit-scrollbar-thumb {
  border-radius: 5px;
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, .3);
  background-color: #555;
}

.sun_right {
  width: 55%;
  padding-left: 20px;
  height: 80%;
}

.sun_left {
  width: 40%;
  background: #ffff;
  /*margin-left: 20px;*/
}

.sun_left ul {
  color: white;
  width: 100%;
}

.sun_left ul li {
  width: 32%;
  height: 100px;
  float: left;
  text-align: center;
  line-height: 50px;
  border: 1px solid white;
  /*background: #5f5750;*/
}

.sun_left ul li .el-button {
  text-overflow: ellipsis;
  display: block;
  background: #409eff;
  width: 100%;
  height: 100%;
  border-radius: 0px;
  color: #EEEEEE;
  font-size: 20px;
}

.sun_left ul li :hover {
  text-overflow: ellipsis;
  border-radius: 4px;
  border: 1px solid #ebeef5;
  background-color: #fff;
  overflow: hidden;
  color: #303133;
  transition: .3s;
}

.about {
  background-color: #f5f5f5;
}

.about .about-header {
  height: 64px;
  background-color: #fff;
  border-bottom: 2px solid #ff6700;
}

.about .about-header .about-title {
  width: 1225px;
  margin: 0 auto;
  height: 64px;
  line-height: 64px;
  font-size: 28px;
}

.about .content {
  padding: 20px 0;
  width: 1225px;
  margin: 0 auto;
}

.about .content .goods-list {
  margin-left: -13.7px;
  overflow: hidden;
}

.about .about-content {
  width: 1225px;
  margin: 0 auto;
  background-color: #f5f5f5;
}

.msmsm {
  width: 70%;
  height: 100%;
}

.msmsm img {
  margin-top: 10%;
}

.ksk {
  height: 65%;
  border-bottom: 1px solid #EEEEEE;
}

.center_zx {
  width: 30%;
  height: 90%;
  border-right: 1px solid #EEEEEE;
  margin: 5px;
  display: flex;
  justify-content: center;
  text-align: center;
}

.center_right {
  width: 65%;
  height: 90%;
  margin-left: 2%;

}

.bode_main {
  height: 300px;
  width: 100%;
  /*margin-left: 10%;*/
  display: flex;
  align-items: center;

}

.main-ka {
  /*box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15) !important;*/
  /*border: 1px solid #1D1C2E;*/
  background-color: #fff;
  color: #EEEEEE;
  transition: .3s;
}

.orderMap table {
  width: 100%;
  color: #e2b1b1;
  margin-top: 20px;
}

.orderKey {
  width: 120px;
  font-size: 15px;
  text-align: center;
}

.orderValue {
  width: 400px;
}


.su_card {
  margin-top: 20px;
  width: 100%;
  //background: rebeccapurple;


}

.image {
  width: 100%;
  height: 150px;
}

.aaww {
  margin-left: 10px;
  margin-top: 10px;
  width: 20%;
  display: inline-block;

  //float:left;
  //flex: 1;


}

.sun_sp {
  //display: flex;
  //box-sizing: border-box;
  width: 100%;
  height: 330px;
  flex-shrink: 0;
  overflow-x: auto;
  white-space: nowrap;


}
</style>
