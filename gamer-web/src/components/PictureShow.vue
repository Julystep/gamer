<template>
  <el-carousel :interval="50000" arrow="always">
    <el-carousel-item
      v-for="item in years"
      :key="item"
      width="100%"
      height="100%"
    >
      <h2>{{ item.year }}</h2>
      <el-row class="picture_row" :gutter="20" :key="i">
        <el-col
          v-for="(o, index) in map.get(item.year)"
          :key="o"
          :span="5"
          :offset="index % 4 == 0 ? 2 : 0"
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
  <div class="split"></div>
  <el-row :gutter="20" style="margin-bottom: 60px">
    <el-col :span="6" :offset="12"
      ><h1 style="margin-top: 0px">年度平台统计</h1></el-col
    >
    <el-col :span="6">
      <slot>年份：</slot>
      <el-select
        v-model="year"
        class="m-2"
        placeholder="选择年份"
        size="small"
        width="100%"
        @change="changeData"
      >
        <el-option
          v-for="item in years"
          :key="item.year"
          :label="item.year"
          :value="item.year"
        />
      </el-select>
    </el-col>
  </el-row>
  <el-row style="height: 1200px">
    <el-col :span="12">
      <game_vertical
        id="Nitendo"
        ref="nitendo"
        style="width: 100%; height: 600px"
      />
    </el-col>
    <el-col :span="12"
      ><game_vertical
        id="Playstation"
        ref="playstation"
        style="width: 100%; height: 600px"
    /></el-col>
    <el-col :span="12"
      ><game_vertical id="Xbox" ref="xbox" style="width: 100%; height: 600px"
    /></el-col>
    <el-col :span="12"
      ><game_vertical id="PC" ref="pc" style="width: 100%; height: 600px"
    /></el-col>
  </el-row>
  <el-row :gutter="20" style="margin-bottom: 60px">
    <el-col :span="6" :offset="12"
      ><h1 style="margin-top: 0px">游戏时长汇总</h1></el-col
    >
  </el-row>
  <el-row style="height: 600px">
    <el-col :span="24"
      ><game_category id="history" ref="history" style="width: 100%; height: 100%"
    /></el-col>
  </el-row>
</template>
<script>
import game_vertical from "./charts/Vertical.vue";
import game_category from "./charts/Category.vue";
export default {
  name: "PictureShow",
  data() {
    return {
      map: new Map(),
      years: [],
      year: 0,
    };
  },
  components: {
    game_vertical,
    game_category,
  },
  methods: {
    changeData() {
      this.pintPicture();
    },
    pintPicture() {
      let m = new Map();
      let result = this.$gameRequest.queryGameData();
      result
        .then((res) => {
          if (res.code === -1) {
            this.$gameMessageBox.errorMessageBox(this, "查询失败");
          } else {
            let data = res.data;
            data.forEach((item) => {
              if (!m.has(item.year)) {
                m.set(item.year, new Array());
              }
              m.get(item.year).push(item);
              this.map = m;
            });
            this.initHistory(m);
            this.$refs.nitendo.initPlatform("Nintendo", m.get(this.year));
            this.$refs.playstation.initPlatform(
              "PlayStation",
              m.get(this.year)
            );
            this.$refs.xbox.initPlatform("Xbox", m.get(this.year));
            this.$refs.pc.initPlatform("PC", m.get(this.year));
          }
        })
        .catch(error => {
          console.log(error)
        });
    },
  },
  mounted() {
    let result = this.$gameRequest.queryYears();
    result
      .then((res) => {
        if (res.code === -1) {
          this.$gameMessageBox.errorMessageBox(this, "查询失败");
        } else {
          this.years = res.data;
          this.year = this.years[0].year;
        }
      })
      .catch(error => {
        console.log(error)
        this.$gameMessageBox.errorMessageBox(this, "查询失败");
      });
    this.pintPicture();
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
  background-color: rgb(233, 231, 231);
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: rgb(233, 231, 231);
}
.el-row.picture_row > .el-col {
  height: 400px;
  background-color: rgb(233, 231, 231);
}

.el-row.picture_row {
  margin-top: 20px;
}
.el-image__inner {
  height: 100%;
}
.split {
  margin-top: 60px;
}
</style>
