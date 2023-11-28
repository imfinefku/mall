<template>
  <div style="width: 100%">
    <div class="about-header">
      <div class="about-title">
        <i class="el-icon-tickets" style="color: #ff6700;"></i>
        {{ isType ? '发布求购' : '发布闲置' }}
      </div>
    </div>
    <el-card class="sun_main_card">
      <div class="sun_th" v-if="!isType">
        <div class="sun_td">
          <span>名称:</span>
          <div>
            <el-input placeholder="请输入内容" v-model="goodsInfo.goodsName" size="mini"></el-input>
          </div>
        </div>
        <div class="sun_td">
          <span>原价:</span>
          <div>
            <el-input placeholder="请输入内容" size="mini" v-model="goodsInfo.goodsPrice"></el-input>
          </div>
        </div>
        <div class="sun_td">
          <p>现价:</p>
          <div>
            <el-input placeholder="请输入内容" size="mini" v-model="goodsInfo.goodsDiscount"></el-input>
          </div>
        </div>
      </div>

      <div class="sun_th" v-if="isType">
        <div class="sun_td">
          <span>标题:</span>
          <div>
            <el-input placeholder="请输入内容" v-model="MallBuying.buyingName" size="mini"></el-input>
          </div>
        </div>
        <div class="sun_td">
          <span>价钱:</span>
          <div>
            <el-input placeholder="请输入内容" size="mini" v-model="MallBuying.buyingPrice"></el-input>
          </div>
        </div>
        <div class="sun_td">
          <p>新旧程度:</p>
          <div>
            <el-input placeholder="请输入内容" size="mini" v-model="MallBuying.oldAndNew"></el-input>
          </div>
        </div>
      </div>

      <div class="sun_th" v-if="!isType">
        <div class="sun_td">
          <span>排序:</span>
          <div>
            <el-input-number v-model="goodsInfo.sort" :min="0" :max="999" label="描述文字"></el-input-number>
          </div>
        </div>
        <div class="sun_td">
          <span>状态:</span>
          <div>
            <el-select placeholder="请选择" size="mini" v-model="goodsInfo.status">
              <el-option label="开启" value="0"></el-option>
              <el-option label="关闭" value="1"></el-option>
            </el-select>
          </div>
        </div>
        <div class="sun_td">
          <p>产地:</p>
          <div>
            <el-input placeholder="请输入内容" size="mini" v-model="goodsInfo.goodsOrigin"></el-input>
          </div>
        </div>
      </div>
      <!--      图片上传-->
      <div class="upt_img">
        <div class="upt_bj">
          <p>图片</p>
          <div>
            <el-upload ref='uploader' :auto-upload='false' :limit='10'
                       :on-change='handleChange'
                       :on-remove='removeChange'
                       :file-list='fileList'
                       action='#'
                       list-type='picture-card' style=''>
              <!--                <img :src="MallGoodsImgInfo.imgUrl" v-if="MallGoodsImgInfo.imgUrl" alt="" width="100" height="100"/>-->
              <i class='el-icon-plus avatar-uploader-icon'></i>
            </el-upload>
            <!--            <el-button type='warning' size="mini" v-if='imageUrl' @click='uploadGoodsImg' i>上传</el-button>-->
          </div>

        </div>

      </div>
      <!--      详细信息-->
      <div class="upt_img" v-if="!isType">
        <div class="upt_bj">
          <p>描述</p>
          <div class="sun_text">
            <el-input
                type="textarea"
                :rows="4"
                placeholder="请输入内容"
                v-model="goodsInfo.goodsAddress"
            >
            </el-input>

          </div>
        </div>

      </div>

      <div class="upt_img" v-if="isType">
        <div class="upt_bj">
          <p>描述</p>
          <div class="sun_text">
            <el-input
                type="textarea"
                :rows="4"
                placeholder="请输入内容"
                v-model="MallBuying.buyingAddress"
            >
            </el-input>

          </div>
        </div>

      </div>

      <div class="sun_btn">
        <div class="sun_jt_btn">
          <el-button type="success" size="mini" @click="uploadGoodsImg">发布</el-button>
        </div>
        <div class="sun_jt_btn">
          <el-button type="info" size="mini" @click="$router.push({path: '/about'})">取消</el-button>
        </div>
      </div>
    </el-card>
  </div>

</template>

<script>
export default {
  name: "AddIdle",
  data() {
    return {
      goodsInfo: {
        sort: 0,
        imgUrlList: []
      },
      MallBuying: {
        sort: 0,
        imgUrlList: []
      },
      imageUrl: '',
      fileList: [],
      goodsId: this.$route.query.goodsId,
      isType:false
    }
  },
  methods: {
    handleChange(file) {
      let form = new FormData()
      form.append('httpFile', file.raw)
      form.append('goodsId', this.$store.getters.getUser.id)
      this.$axios
          .post('/mallGoods/uploadIdleMallGoodsImg', form
              , {
                headers: {
                  'Content-Type': 'application/x-www-form-urlencoded',
                  'Token': window.sessionStorage.getItem('token')
                }
              })
          .then(res => {
            if (res.data.code === 20000) {
              this.fileList.push({url: 'http://localhost:8001' + res.data.data.path})
              if (!this.isType) {
                this.goodsInfo.imgUrlList.push(res.data.data.path)
              } else {
                this.MallBuying.imgUrlList.push(res.data.data.path)
              }

            }

          })
          .catch(err => {
            return Promise.reject(err);
          });
    },
    removeChange(res) {
      console.log(res)
      // this.filelist = []
      // this.imageUrl = ''
    },
    uploadGoodsImg() {
      // /idle/saveIdleGoods
      if (this.isType){
        // debugger
        this.$axios
            .post("/mallBuying/saveOrUpdateMallBuying", this.MallBuying
            ).then(res => {
          if (res.data.code === 20000) {
            this.$router.push({path: "/about"})
          }
        })
      }else {
        if (this.$route.query.buyingId){
          this.goodsInfo.buyingId=this.$route.query.buyingId
        }

        this.$axios
            .post("/idle/saveIdleGoods", this.goodsInfo
            ).then(res => {
          if (res.data.code === 20000) {
            this.$router.push({path: "/about"})
          }
        })
      }



    },
    initAdd() {
      if (this.goodsId) {
        this.$axios
            .get("/idle/gitByGoodsId/" + this.goodsId).then(res => {
          this.goodsInfo = res.data.data.info
          console.log(this.goodsInfo)
          let arr = this.goodsInfo.imgUrlList

          this.fileList = arr.map(res => {
            return {"url": this.$target + res}
          })

        })
      }
    },

  },
  activated() {
    if (this.$route.query.goodsId) {
      this.goodsId = this.$route.query.goodsId;
      this.initAdd()
    } else {
      this.fileList = []
      this.goodsInfo = {
        sort: 0,
        imgUrlList: []
      }
    }

    if (this.$route.query.isType+0 === 0) {
      // console.log(this.$route.query.isType+0)
      this.isType=false
    }else {
      this.isType=true
    }
  }
}
</script>

<style scoped>
.about-header {
  height: 64px;
  background-color: #fff;
  border-bottom: 2px solid #ff6700;
}

.about-title {
  width: 1225px;
  margin: 0 auto;
  height: 64px;
  line-height: 64px;
  font-size: 28px;
}

.sun_main_card {
  width: 1225px;
  margin: 0 auto;
  background-color: #f5f5f5;
}

.sun_th {
  width: 90%;
  /*height: 1024px;*/
  margin: 0 auto;
  /*background: #0366d6;*/
  display: flex;
  margin-top: 20px;

}

.sun_td {
  width: 30%;
  display: flex;
  align-items: center;
  box-sizing: border-box;
  justify-content: center;
}

.sun_text {
  width: 100%;
}

.sun_td div {
  margin-left: 10px;
}

.upt_img {
  display: flex;
  justify-content: center;
  width: 90%;
  /*padding-left: 10%;*/
  margin-top: 20px;
}

.upt_bj {
  display: flex;
  width: 80%;
}

.upt_bj p {
  width: 5%;
}

.sun_btn {
  margin-top: 100px;
  display: flex;
  justify-content: center;
}

.sun_jt_btn {
  margin-left: 20px;
}
</style>