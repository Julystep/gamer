<template>
  <div class="div_item" v-for="year in years" :key="year">
    <div class="title">
      <h1>{{ year.year }}</h1>
    </div>
    <el-row>
      <el-col class="outer" :span="9" :offset="3">
        <el-row class="inner first">
          <el-col class="inner_col first" :span="11">
            <GamePreviewShowItem />
          </el-col>
          <el-col class="inner_col" :span="11">
            <GamePreviewShowItem />
          </el-col>
        </el-row>
        <el-row class="inner">
          <el-col class="inner_col first" :span="11">
            <GamePreviewShowItem />
          </el-col>
          <el-col class="inner_col" :span="11">
            <GamePreviewShowItem />
          </el-col>
        </el-row>
      </el-col>
      <el-col class="outer" :span="9" style="margin-left: 10px">
        <GamePreviewShowItem />
      </el-col>
    </el-row>
  </div>
</template>
<script>
import GamePreviewShowItem from "./GamePreviewShowItem.vue";
export default {
  name: "GamePreview",
  data() {
    return {
      years: [],
    };
  },
  components: {
    GamePreviewShowItem,
  },
  methods: {},
  mounted() {
    let result = this.$gameRequest.queryYears();
    result
      .then((res) => {
        if (res.code === -1) {
          this.$gameMessageBox.errorMessageBox(this, "查询失败");
        } else {
          this.years = res.data;
        }
      })
      .catch((error) => {
        console.log(error);
        this.$gameMessageBox.errorMessageBox(this, "查询失败");
      });
  },
};
</script>
<style scoped>
.outer {
  height: 400px;
  min-height: 36px;
  /* background: #d3dce6; */
}
.div_item {
  margin-top: 40px;
}
.title {
  text-align: center;
  height: 50px;
}
.inner {
    height: 195px;
}
.inner.first {
    margin-bottom: 10px;
}

.inner_col {
    height: 100%;
}
.inner_col.first {
    margin-right: 3%;
    margin-left: 4%
}
</style>
