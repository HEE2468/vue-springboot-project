<template>
    <div>
        <!--面包屑导航区域-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{path:'/index'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>用户管理</el-breadcrumb-item>
            <el-breadcrumb-item>用户列表</el-breadcrumb-item>
        </el-breadcrumb>

        <!--卡片视图区域-->
        <br>
        <el-card>
            <!-- 搜索与添加区域 -->
            <!-- row行，col列 ，gutter使元素间隔距离有20px-->
            <el-row :gutter="20">
                <el-col :span="8">
                    <el-input placeholder="请输入内容" v-model="queryInfo.name" class="input-with-select">
                        <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
                    </el-input>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" @click="addDialogVisible=true">添加用户</el-button>
                </el-col>
            </el-row>

            <!--用户列表区域-->
            <!--:data="userList" 为table引入数据源-->
            <el-table :data="userList" border stripe>
                <!-- 索引列 -->
                <el-table-column label="序号" type="index" width="80px"></el-table-column>
                <el-table-column label="姓名" prop="username"></el-table-column>
                <el-table-column label="性别" prop="sex">
                    <template slot-scope="scope">
                        <p v-if="scope.row.sex==1">男</p>
                        <p v-if="scope.row.sex==0">女</p>
                    </template>
                </el-table-column>
                <el-table-column label="电话号码" prop="telephone"></el-table-column>
                <el-table-column label="状态">
                    <template slot-scope="scope">
                        <!--{{scope.row}} 插槽会覆盖prop的值，所以可以去掉prop="isExist" -->
                        <el-switch v-model="scope.row.isExist"
                                   :active-value="1"
                                   :inactive-value="0"
                                   @change="userStateChanged(scope.row)"></el-switch>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <!--通过作用域插槽接收数据-->
                    <template slot-scope="scope">
                        <!--修改按钮-->
                        <el-button type="primary" size="mini" icon="el-icon-edit" @click="showEditUserDialog(scope.row)"></el-button>
                        <!--删除按钮-->
                        <el-button type="danger" size="mini" icon="el-icon-delete"></el-button>
                        <!--分配角色按钮-->
                        <el-tooltip effect="dark" content="分配角色" placement="top" :enterable="false">
                            <el-button type="warning" size="mini" icon="el-icon-setting"></el-button>
                        </el-tooltip>

                    </template>
                </el-table-column>
            </el-table>

            <!--分页-->
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="queryInfo.page"
                    :page-sizes="[2, 5, 10, 20,30]"
                    :page-size="queryInfo.rows"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="pageTotal">
            </el-pagination>

        </el-card>

        <!-- 添加用户弹出框 -->
        <el-dialog
                title="添加用户"
                :visible.sync="addDialogVisible"
                width="30%"
               >
            <!--内容主体区-->
            <!--关闭对话框时要调用@close的函数-->
            <el-form :model="addForm"
                     :rules="addFormRules"
                     ref="addFormRef"
                     lable-width="80px"
                     @close="addDialogClosed"
            >
                <!--lable-width指的是lable标签的宽度-->
                <el-form-item label="用户名：" label-width="100px" height="1px">
                    <el-input v-model="addForm.username"></el-input>
                </el-form-item>
                <el-form-item label="电话号码：" label-width="100px">
                    <el-input v-model="addForm.telephone"></el-input>
                </el-form-item>
                <el-form-item label="性别：" prop="sex" label-width="100px" >
                    <el-radio-group v-model="addForm.sex">
                        <el-radio :label="0">女</el-radio>
                        <el-radio :label="1">男</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item>

                </el-form-item>
            </el-form>
                <!-- 底部区域 -->
                <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="addUser">确 定</el-button>
            </span>
        </el-dialog>

        <!--弹出修改用户弹出框-->
        <el-dialog title="修改用户"
                    :visible.sync="editDialogVisible"
                    width="30%"
        >
            <el-form :model="editForm"
                     :rules="editFormRules"
                     ref="editFormRef"
                     lable-width="80px"
                     @close="editDialogClosed"
            >
                <el-form-item label="用户名：" label-width="100px" height="1px">
                    <el-input v-model="editForm.username"></el-input>
                </el-form-item>
                <el-form-item label="电话号码：" label-width="100px">
                    <el-input v-model="editForm.telephone"></el-input>
                </el-form-item>
                <el-form-item label="性别：" label-width="100px" >
                    <el-radio-group v-model="editForm.sex">
                        <el-radio :label="0">女</el-radio>
                        <el-radio :label="1">男</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item>

                </el-form-item>
            </el-form>
            <!-- 底部区域 -->
            <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateUser">确 定</el-button>
            </span>
        </el-dialog>


    </div>
</template>

<script>
    export default {
        name: "UserList",
        data() {
            let checkTelephone = (rule,value,callback)=>{
                const regTelephone = /^((1[3,5,8,7,9][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;
                if(regTelephone.test(value)){
                    return callback();
                }
                callback(new Error('请输入合法的手机号!'));
            };
            return {
                userList: [],
                queryInfo: {
                    name: '',
                    page: 1, // 当前的页数
                    rows: 5 // 每页显示的条数
                },
                pageTotal: 0,
                // 添加用户的对话框是否显示
                addDialogVisible:false,
                editDialogVisible:false,
                addForm:{
                    username:"",
                    password:"123",
                    registerDate:new Date(),
                    isExist:1,
                    sex:0,
                    telephone:""
                },
                // 添加用户表单的验证规则对象
                addFormRules:{
                    // 用户名必填，用户名长度设置
                    username:[{ required:true,message:'请输入用户名',trigger:'blur' },
                              {min:3,max:10,message:"用户名的长度在3~10个字符之间",trigger:'blur'}
                              ],
                    telephone:[
                        { required:true,message:'请输入手机号',trigger:'blur' },
                        { validator:checkTelephone,trigger:'blur'}
                    ]
                },
                editFormRules:{
                    // 用户名必填，用户名长度设置
                    username:[{ required:true,message:'请输入用户名',trigger:'blur' },
                        {min:3,max:10,message:"用户名的长度在3~10个字符之间",trigger:'blur'}
                    ],
                    telephone:[
                        { required:true,message:'请输入手机号',trigger:'blur' },
                        { validator:checkTelephone,trigger:'blur'}
                    ]
                },
                addFormRef:"",
                editForm:{
                    id:"",
                    username:"",
                    sex:0,
                    telephone:""
                },
                editFormRef:"",


            }
        },
        created() {
            this.getUserList();
        },
        methods: {
            getUserList() {
                this.$axios.post("/user/findAllUsers", this.queryInfo).then(response => {
                    if (response.data.code === 200) {
                        this.userList = response.data.data.rows;
                        this.pageTotal = response.data.data.pageTotal;

                    } else {
                        return this.$message.error("获取用户列表失败!");
                    }
                }).catch(error => {
                    console.log(error)
                })
            },
            // 监听pageSize改变的事件
            handleSizeChange(newPageSize) {
                this.queryInfo.rows = newPageSize;
                this.getUserList();
            },
            // 监听页码值改变的事件
            handleCurrentChange(newPage) {
                this.queryInfo.page = newPage;
                this.getUserList();
            },
            userStateChanged(userInfo) {
                this.$axios.post("/user/userStateChanged", userInfo).then(response => {
                    if (response.data.code !== 200) {
                        return this.$message.error(response.data.msg);
                    }
                }).catch(error => {
                    console.log(error)
                })
            },
            // 监听添加用户对话框的关闭事件
            addDialogClosed(){
                this.$refs.addFormRef.resetFileds();
            },
            addUser(){
                // valid是校验后的返回值：true或者false
                this.$refs.addFormRef.validate(valid=>{
                   // 校验失败
                    if(!valid) return;
                    // 发起添加用户的请求
                    this.$axios.post("/user/addUserByUserInfo", this.addForm).then(response => {
                        if (response.data.code !== 200) {
                            return this.$message.error(response.data.msg);
                        }else{
                            this.$message.success(response.data.msg);
                            window.location.reload();
                        }
                    }).catch(error => {
                        console.log(error)
                    })
                })
            },
            showEditUserDialog(scopeInfo){
                this.editForm.id = scopeInfo.id;
                this.editForm.username = scopeInfo.username;
                this.editForm.telephone = scopeInfo.telephone;
                this.editForm.sex = scopeInfo.sex;
                this.editDialogVisible = true;
            },
            editDialogClosed(){
                this.$refs.editFormRef.resetFileds();
            },
            updateUser(){
                this.$refs.editFormRef.validate(valid=>{
                    // 校验失败
                    if(!valid) return;
                    // 发起修改用户的请求
                    this.$axios.post("/user/updateUserByUserInfo", this.editForm).then(response => {
                        if (response.data.code !== 200) {
                            return this.$message.error(response.data.msg);
                        }else{
                            this.$message.success(response.data.msg);
                            window.location.reload();
                        }
                    }).catch(error => {
                        console.log(error)
                    })
                })
            }
        }
    }
</script>

<style scoped>

</style>