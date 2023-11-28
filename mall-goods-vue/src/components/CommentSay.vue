<template>
  <div class="su_card" >

    <div class="sun_main" v-for="item in discussList" :index="item.id" :key="item.id">
      <div class="sun_img">
        <img src="https://inq2019.oss-cn-shenzhen.aliyuncs.com/userIng/moren.png" alt="">
        <p class="sun_name">{{ item.userName }}</p>
      </div>
      <div class="sun_send_pf">
        <div v-if="type==='商品'">
          <el-rate v-model="item.score" disabled></el-rate>
        </div>
        <p style="width: 95%">{{item.news}}</p>

        <p style="margin-top: 15px;font-size: 8px; color: rgb(99 96 96)">{{ item.creationTime }}</p>
      </div>
      <div class="sun_hhh" v-if="type==='商品'">
        <p class="sun_zt">名称:<span class="zt">{{ item.goods.goodsName }}</span></p>
        <p class="sun_zt">类型:<span class="zt">{{ item.goods.goodsType === 0 ? "官方自营" : "二手闲置" }}</span></p>
        <p class="sun_zt">价格:<span class="zt">{{ item.goods.goodsPrice }}</span></p>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "CommentSay",
  data() {
    return {}
  },
  props: ["discussList", "type"]
}
</script>

<style lang="less" scoped>
.su_card {
  .sun_main {
    width: 100%;
    display: flex;
    border-bottom: #eeeeee solid 1px;
    margin: 20px;
    //头像样式
    .sun_img {
      width: 10%;
      //display: flex;
      //border-right: #eeeeee solid 1px;
      padding: 10px;

      img {
        width: 50px;
        height: 50px;
        display: block;
        border-radius: 50%;
        margin: auto;
      }

      .sun_name {
        font-size: 10px;
        text-align: center;
      }
    }

    //评分主题
    .sun_send_pf {
      font-size: 10px;
      margin-left: 20px;
      width: 70%;
      display: flex;
      justify-content: center;
      //align-items: center;
      flex-direction: column;
    }

    .sun_hhh {
      font-size: 12px;
      padding-left: 20px;
      margin-bottom: 10px;
      border-left: #eeeeee solid 1px;

      .sun_zt {
        margin: 5px;

        .zt {
          color: #eac1c1;
        }
      }
    }

  }
}
</style>