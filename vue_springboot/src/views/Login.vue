<template>
    <div class="login-container">
        <div class="login-form-rows">
            <div>用户登录</div>
            <el-form :model="loginData"
                     :rules="rules"
                     class="login-from"
                     ref="form"
            >

                <!-- 用户名 -->
                <el-form-item>
                    <el-input placeholder="请输入用户名" v-model="loginData.username">
                        <span slot="prepend"></span>
                    </el-input>
                </el-form-item>

                <!-- 密码 -->
                <el-form-item>
                    <el-input
                       placeholder="请输入密码"
                       type="password"
                       v-model="loginData.password"
                    >
                        <span slot="prepend"></span>
                    </el-input>
                </el-form-item>

                <!-- 登录按钮 -->
                <div >
                    <el-button @click="submitLogin" type="primary">登录</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<!-- vue中，导入方法要加花括号{} -->
<script>

    export default {
        name: "Login",
        data(){
            return{
                loginData:{
                    username:"",
                    password:""
                },
                rules:{
                    username:[{required:true,message:"用户名不能为空!",trigger:"blur"}],
                    password:[{required:true,message:"密码不能为空!",trigger:"blur"}]
                }
            }
        },
        methods:{
            submitLogin(){
                this.$axios.post('/user/login',{
                    username: this.loginData.username,
                    password: this.loginData.password
                }).then(response => {
                    /*if (response === "success") {
                        console.log(response.data)
                    }*/
                }).catch(error => {
                    alert('请求失败')
                })

            }
        }
    }
</script>

<style scoped>

</style>