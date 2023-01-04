<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <el-menu mode="horizontal" :ellipsis="false">
          <el-menu-item index="1">
            <h1>
              <router-link style="text-decoration: none" to="/"
                >GAMER-WEB</router-link
              >
            </h1>
          </el-menu-item>
          <div class="flex-grow" />
          <el-sub-menu index="2">
            <template #title><h1>年度游戏预览</h1></template>
            <el-menu-item
              v-for="(year, index) in years"
              :key="year"
              :index="1 - index"
            >
              <router-link
                style="text-decoration: none"
                :to="{ path: '/detail', query: { year: year } }"
                >{{ year }}</router-link
              >
            </el-menu-item>
          </el-sub-menu>
          <el-menu-item index="1">
            <h4 @click="login">
              <el-icon><UserFilled /></el-icon>
            </h4>
          </el-menu-item>
          <div class="flex-grow" />
        </el-menu>
      </el-header>
      <el-main>
        <router-view :key="key" v-if="isRouterAlive"></router-view>
      </el-main>
    </el-container>
  </div>
  <loginModal ref="login"/>
</template>

<script>
import { UserFilled } from "@element-plus/icons";
import loginModal from "./components/modalbox/loginModal.vue";
export default {
  name: "App",
  provide() {
    return {
      reload: this.reload,
    };
  },
  computed: {
    key() {
      return this.$route.name
        ? this.$route.name + new Date()
        : this.$route + new Date();
    },
  },
  components: {
    UserFilled,
    loginModal
  },
  data() {
    return {
      isRouterAlive: true,
      years: [],
    };
  },
  methods: {
    reload() {
      this.isRouterAlive = false;
      this.$nextTick(function () {
        this.isRouterAlive = true;
      });
    },
    login() {
      this.$refs.login.showDialog();
    }
  },
  mounted() {
    this.$axios.get("/query/years").then((res) => {
      if (res.status === 200 && res.data.code === 0) {
        this.years = res.data.data;
      } else {
        this.$message({
          message: "查询失败",
          type: "error",
        });
      }
    });
  },
};
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
  flex-grow: 1;
}
.el-main {
  margin-top: 60px;
}
.picture-container {
  margin-top: 60px;
}
</style>
