<template>
    <div class="block">
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>商城用户表</el-breadcrumb-item>
            <el-breadcrumb-item>商城用户表列表</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>

            <!-- 搜索 -->
            <el-row :gutter="20">
                <el-col :span="7">
                    <!--搜索条件 -->
                </el-col>
                <el-col :span="4">

                </el-col>
            </el-row>



            <el-table :data="GoodsUserList" border stripe size='mini'
                      :cell-style="{'text-align':'center'}"
                      :header-cell-style="{'text-align':'center'}">

                    <el-table-column prop="username" label="用户名"></el-table-column>
                    <el-table-column prop="mobile" label="电话"></el-table-column>
                    <el-table-column prop="mgState" label='状态' :formatter="stateFormat" ></el-table-column>
                    <el-table-column prop="gmtCreate" label="创建时间"></el-table-column>
                <el-table-column label="操作" width="180px">
                    <template slot-scope="scope">

                      <el-tooltip class="item" effect="dark" content="查看" placement="top-start" :enterable="false">
                        <el-button type="primary" icon="el-icon-postcard" size="mini"
                                   @click="editMallOrder(scope.row)"></el-button>
                      </el-tooltip>

                        <!-- 修改 -->
                        <el-tooltip class="item" effect="dark" content="编辑" placement="top-start" :enterable="false">
                            <el-button type="primary" icon="el-icon-edit" size="mini"
                                       @click="editGoodsUser(scope.row)"></el-button>
                        </el-tooltip>
                        <!-- 删除 -->
                        <el-tooltip class="item" effect="dark" content="删除" placement="top-start" :enterable="false">
                            <el-button type="danger" icon="el-icon-delete" size="mini" @click="del(scope.row)"></el-button>
                        </el-tooltip>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 分页 -->
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="page.current"
                    :page-sizes="[5,10, 20, 30, 40]"
                    :page-size="page.size"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="page.total"
            ></el-pagination>

            <el-dialog :title="dialogBewrite" :visible.sync="dialogAddGoodsUser" width="50%" @close="closeInit">
                <el-form :model="GoodsUserInfo" ref="addGoodsUserRef" label-width="70px" class="demo-ruleForm">

                        <el-form-item label="用户名">
                            <el-input v-model="GoodsUserInfo.username"></el-input>
                        </el-form-item>
                        <el-form-item label="电话">
                            <el-input v-model="GoodsUserInfo.mobile"></el-input>
                        </el-form-item>
                        <el-form-item label="是否开启">
                          <el-select v-model="GoodsUserInfo.mgState" placeholder="请选择">
                            <el-option :key="1" label="开启" :value="true"></el-option>
                            <el-option :key="2" label="开启" :value="false"></el-option>
                          </el-select>
                        </el-form-item>
                    <el-form-item>
                        <template slot-scope="scope">
                            <el-button type="primary"@click="addGoodsUser()">保存</el-button>
                            <el-button @click="dialogAddGoodsUser=false">取消</el-button>
                        </template>
                    </el-form-item>
                </el-form>
            </el-dialog>

        </el-card>


    </div>
</template>
<script>
    export default {
        data() {
            return {
                GoodsUserList: [],
                page: {
                    current: 1,
                    size: 10,
                    total: 0
                },
                GoodsUserVo: {
                    page: {
                        current: 1,
                        size: 5
                    }
                },
                dialogAddGoodsUser: false,
                GoodsUserInfo: {},
                dialogBewrite:""

            }
        },
        created() {
            this.queryPage()
        },
        methods: {

          editMallOrder(res){
            this.$router.push({ path: '/goodsUserDetails', query: { id: res.id } })
          },

          stateFormat(r,c){
            if (r.mgState === true) {
              return '开启'
            } else {
              return '关闭'
            }
          },

            // 监听每页展示的条数
            handleSizeChange(newSize) {
                this.GoodsUserVo.page.size = newSize
                this.queryPage()
            },
            //监听页码
            handleCurrentChange(newCurrent) {
                this.GoodsUserVo.page.current = newCurrent
                this.queryPage()
            },
            //列表
            async queryPage() {
                const {data: res} = await this.$http.post(
                        '/goodsUser/queryGoodsUserList',
                        this.GoodsUserVo
                )

                if (res.code != 20000)
                return this.$message.error(res.message)
                this.GoodsUserList = res.data.dataList.records
                this.page.total = res.data.dataList.total
                this.page.current = res.data.dataList.current
                this.page.size = res.data.dataList.size
            },
            //查询详情
            async editGoodsUser(row) {
                this.dialogAddGoodsUser=true;
                this.dialogBewrite='编辑商城用户表'
                const id = row.id

                const {data: res} = await this.$http.get(
                        '/goodsUser/getByIdGoodsUser/'+id
                )
                if (res.code != 20000) return this.$message.error(res.message)
                this.GoodsUserInfo=res.data.entity
            },
            closeInit() {
                this.addGoodsUserForm = {}
                this.dialogAddGoodsUser= false
            },
            async del(status) {

                this.$confirm('确认永久删除吗？')
                        .then(async _ => {
                            const { data: res } = await this.$http.delete(
                                    '/goodsUser/removeByIdGoodsUser/'+status.id
                            )
                            if (res.code != 20000) return this.$message.error(res.message)
                            this.queryPage()
                            return this.$message.success(res.message)
                        })
                        .catch(_ => {
                            return  this.$message.info("取消删除")
                        });




            },
            async addGoodsUser(){
                  const { data: res } = await this.$http.post(
                    '/goodsUser/uptUserInfo/',this.GoodsUserInfo
                  )
                  if (res.code !== 20000) return this.$message.error(res.message)
                  this.dialogAddGoodsUser=false
                  this.GoodsUserInfo={}
                  this.queryPage()
                }
        },



    }
</script>