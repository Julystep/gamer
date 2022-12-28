<template>
  <div class="common-layout">
        <el-container>
            <el-header>
              <el-menu :default-active="activeIndex" mode="horizontal" :ellipsis="false">
                <el-menu-item index="0">
                  <h1><router-link style="text-decoration: none" to="/">GAMER-WEB</router-link></h1>
                </el-menu-item>
                <div class="flex-grow" />
                <el-sub-menu index="1">
                  <template #title><h1> 年度游戏预览 </h1></template>
                  <el-menu-item index="2-1"><router-link style="text-decoration: none" to="/detail">2020</router-link></el-menu-item>
                  <el-menu-item index="2-2"><router-link style="text-decoration: none" to="/detail">2021</router-link></el-menu-item>
                  <el-menu-item index="2-3"><router-link style="text-decoration: none" to="/detail">2022</router-link></el-menu-item>
                </el-sub-menu>
                <div class="flex-grow" />
              </el-menu>
            </el-header>
            <el-main>
              <router-view></router-view>
            </el-main>
        </el-container>
    </div>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.el-menu.el-menu--horizontal {
  border: none;
}

.flex-grow {
  flex-grow: 0.6;
}
.el-main {
  margin-top: 60px;
}
.picture-container {
  margin-top: 60px;
}
</style>
