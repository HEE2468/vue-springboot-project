<template>

    <el-container class="home-container">
        <!-- 头部区域 -->
        <el-header>
            <div>
                <img style="width: 30px;height: 30px" src="../assets/logo.png" alt="">
                <span>电商后台管理系统</span>
            </div>
            <el-button type="info" @click="logout">退出</el-button>
        </el-header>

        <!--页面主体区域-->
        <el-container>
            <!--侧边栏-->
            <el-aside :width="isCollapse?'64px':'200px'">
                <div class="toggle-button" @click="toggleCollapse">|||</div>
                <el-menu
                        default-active="2"
                        class="el-menu-vertical-demo"
                        background-color="#333744"
                        text-color="#fff"
                        active-text-color="#409EFF"
                        :collapse="isCollapse"
                        :collapse-transition="false"
                        router
                >
                    <el-submenu :index="item.id" v-for="item in menuList" :key="item.id">
                        <template slot="title">
                            <!--图标-->
                            <i :class="item.menuIcon"></i>
                            <!--文本-->
                            <span>{{item.menuName}}</span>
                        </template>
                        <el-menu-item :index="subMenu.path" v-for="subMenu in item.subMenuList" :key="subMenu.id">
                            <template slot="title">
                                <i :class="subMenu.subMenuIcon"></i>
                                <span>{{subMenu.subMenuName}}</span>
                            </template>
                        </el-menu-item>
                    </el-submenu>


                </el-menu>
            </el-aside>
            <!--右侧内容主体-->
            <el-main>
                <!--路由占位符-->
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>

</template>

<script>
    export default {
        name: "Index",
        data(){
            return{
                menuList:[],
                // 是否折叠
                isCollapse:false,
            }
        },
        created(){
            this.getMenuList()
        },
        methods:{
            logout(){

            },
            getMenuList(){
                this.$axios.post('/menu/findAllMenu').then(response => {
                    this.menuList = response.data.data;
                }).catch(error => {
                    alert('请求失败')
                })
            },
            toggleCollapse(){
                this.isCollapse = !this.isCollapse;
            }
        }

    }
</script>

<style lang="less" scoped>
    .home-container{
        height: 100%;
    }
    .el-header{
        background-color: #373d41;
        display: flex;
        /*左右贴边对齐*/
        justify-content: space-between;
        padding-left: 0;
        /*按钮不贴边*/
        align-items: center;
        color: #fff;
        font-size: 20px;
        /*标题居中对齐*/
        > div{
            display:flex;
            /*纵向对齐*/
            align-items: center;
            span{
                margin-left: 15px;
            }
        }

    }
    .el-aside{
        background-color: #333744;
        .el-menu{
            border-right:none;
        }
    }
    .el-main{
        background-color: #eaedf1;
    }

    .toggle-button{
        background-color: #4A5064;
        font-size: 12px;
        line-height: 24px;
        color: #fff;
        text-align: center;
        /*文字间距*/
        letter-spacing: 0.2em;
    }

</style>

