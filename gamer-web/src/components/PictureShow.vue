<template>
  <el-carousel :interval="50000" arrow="always">
    <el-carousel-item v-for="item in years" :key="item" width="100%" height="100%">
      <h2>{{ item }}</h2>
      <el-row class="picture_row" :gutter="20" :key="i">
        <el-col
          v-for="(o, index) in map.get(item)"
          :key="o"
          :span="5"
          :offset="index == 0 ? 2 : 0"
          style="padding: 10px"
        >
          <el-image
            style="width: 100%; height: 100%; border-radius: 25px"
            :src="o.picturePath"
            fit="cover"
            v-if="index < 8"
          />
        </el-col>
      </el-row>
    </el-carousel-item>
  </el-carousel>
</template>
<script>
export default {
  name: "PictureShow",
  data() {
    return {
      map: new Map(),
      years: []
    };
  },
  mounted() {
    this.$axios.get("/query").then((res) => {
      if (res.status === 200 && res.data.code === 0) {
        let data = res.data.data;
        let m = new Map()
        data.forEach(item => {
          if (!m.has(item.year)) {
            m.set(item.year, new Array());
            this.years.push(item.year)
          }
          m.get(item.year).push(item)
          this.map = m;
        })
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
<style>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel.el-carousel--horizontal {
  height: 950px;
}

.el-carousel.el-carousel--horizontal > .el-carousel__container {
  height: 100%;
}

h2 {
  text-align: center;
  color: black;
  font-style: oblique;
}

.el-carousel__item:nth-child(2n) {
  background-color: white;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: white;
}
.el-row.picture_row > .el-col {
  height: 400px;
  background-color: white;
}

.el-row.picture_row {
  margin-top: 20px;
}
.el-image__inner {
  height: 100%;
}
</style>
