<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style>
  /*全局样式表*/
html,body,#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  height: 100%;
  padding: 0;
  margin: 0;
}

  .el-card{
    box-shadow: 0 1px 1px rgba(0,0,0,1.15) !important;
  }
  .el-table{
    margin-top: 10px;
  }
  .el-pagination{
    margin-top: 10px;
  }

  @import "./assets/css/style.css";
</style>
