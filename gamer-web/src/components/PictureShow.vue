<template>
    <el-carousel :interval="50000" arrow="always">
      <el-carousel-item v-for="item in 4" :key="item" width="100%" height="100%">
        <h2> 2022 </h2>
        <el-row class="picture_row" :gutter="20" v-for="i in 2" :key="i">
          <el-col v-for="(o, index) in 4" :key="o" :span="5" :offset="index == 0 ? 2 : 0" style="padding:10px">
            <el-image style="width: 100%; height: 100%; border-radius: 25px;" :src="url" :fit="cover"/>
          </el-col>
        </el-row>
      </el-carousel-item>
    </el-carousel>
</template>
<script>
export default {
    name: 'PictureShow',
    data() {
      return {
        'url': 'http://127.0.0.1:8083/picture/Xenoblade_Chronicles/Xenoblade_Chronicles3.jpg'
      }
    },
    computed: {
      computeOffset(index) {
        if (index == 0) return 1;
        else return 2;
      }
    },
    created() {
      // 获取后端数据，拿到近三年的游戏预览

    }
}
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
  color: white;
  font-style: oblique;
}

.el-carousel__item:nth-child(2n) {
  background-color: black;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: black;
}
.el-row.picture_row > .el-col {
  height: 400px;
  background-color: black;
}

.el-row.picture_row {
  margin-top: 20px;
}
.el-image__inner{
  height: 100%;
}
</style>
