<template>

    <div class="login-container">
        <div class="background">
            <img :src="imgSrc" width="100%" height="100%" alt="" />
        </div>
        <div class="login-form-rows">
            <div class="login-form-text">用户登录</div>
            <br>
            <el-form :model="loginData"
                     :rules="rules"
                     class="login-from"
                     ref="form"
            >

                <!-- 用户名 -->
                <el-form-item class="edit-btn">
                    <el-input placeholder="请输入用户名" v-model="loginData.username">
                        <span slot="prepend"></span>
                    </el-input>
                </el-form-item>

                <!-- 密码 -->
                <el-form-item class="edit-btn">
                    <el-input
                       placeholder="请输入密码"
                       type="password"
                       v-model="loginData.password"
                    >
                        <span slot="prepend"></span>
                    </el-input>
                </el-form-item>

                <!-- 登录按钮 -->
                <div class="login-btn-out">
                    <el-button @click="submitLogin" class="loginBtn">登录</el-button>
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
                imgSrc:require('../assets/bg.jpg'),
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
                     if(response.data === "success"){
                         this.$router.push({ name: "index" });
                     }
                }).catch(error => {
                    alert('请求失败')
                })

            }
        }
    }
</script>

<style scoped>

    .background{
        width:100%;
        height:100%;  /**宽高100%是为了图片铺满屏幕 */
        z-index:-1;
        position: absolute;
    }

    .login-form-rows{
        position: absolute;
        z-index:1;
        top: 40%;
        left: 38%;
        width: 400px;
        height: 230px;
        background-color: rgba(220,38,38,0.1);
        border-radius: 5px;
    }
    .login-form-text{
        text-align: center;
        font-weight: bold;
        font-size: 24px;
        color: #fff;
        margin-top: 10px;
    }
    .edit-btn{
        width: 350px;
        margin-left: 25px;
    }
    .login-btn-out{
        text-align: center;
    }
    .loginBtn{
        background-color: #A30014;
        color: #fff;
        border:none;
    }
    .loginBtn:hover{
        background-color: red;
    }
</style>