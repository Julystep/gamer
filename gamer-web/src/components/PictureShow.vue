<template>
  <el-carousel :interval="50000" arrow="always">
    <el-carousel-item
      v-for="item in years"
      :key="item"
      width="100%"
      height="100%"
    >
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
  <div class="split"></div>
  <el-row style="height: 1200px">
    <el-col :span="12"
      ><div
        id="Switch"
        class="main_container"
        style="width: 100%; height: 600px"
      ></div
    ></el-col>
    <el-col :span="12"
      ><div
        id="PlayStation"
        class="main_container"
        style="width: 100%; height: 600px"
      ></div
    ></el-col>
    <el-col :span="12"
      ><div
        id="Xbox"
        class="main_container"
        style="width: 100%; height: 600px"
      ></div
    ></el-col>
    <el-col :span="12"
      ><div
        id="PC"
        class="main_container"
        style="width: 100%; height: 600px"
      ></div
    ></el-col>
  </el-row>
  <el-row style="height: 600px">
    <el-col :span="24"
      ><div
        id="history"
        class="main_container"
        style="width: 100%; height: 100%"
      ></div
    ></el-col>
  </el-row>
</template>
<script>
export default {
  name: "PictureShow",
  data() {
    return {
      map: new Map(),
      years: [],
      year: 2022,
    };
  },
  methods: {
    initHistory(m) {
      var chartDom = document.getElementById("history");
      var myChart = this.$echarts.init(chartDom);
      var option;
      let source = [];
      source.push(["product", "Switch", "PlayStation", "Xbox", "PC"])
      m.forEach((values, key) => {
        let sourceItem = [];
        sourceItem.push(key);
        let switchNum = 0;
        let playStationNum = 0;
        let xboxNum = 0;
        let pcNum = 0;
        values.forEach((item) => {
          if (item.platform === "Switch") {
            switchNum += item.playedTime;
          }
          if (item.platform === "PlayStation") {
            playStationNum += item.playedTime;
          }
          if (item.platform === "Xbox") {
            xboxNum += item.playedTime;
          }
          if (item.platform === "PC") {
            pcNum += item.playedTime;
          }
        });
        sourceItem.push(switchNum);
        sourceItem.push(playStationNum);
        sourceItem.push(xboxNum);
        sourceItem.push(pcNum);
        source.push(sourceItem);
      })
      option = {
        legend: {},
        tooltip: {},
        dataset: {
          source: source,
        },
        xAxis: { type: "category" },
        yAxis: {},
        // Declare several bar series, each will be mapped
        // to a column of dataset.source by default.
        series: [
          { type: "bar" },
          { type: "bar" },
          { type: "bar" },
          { type: "bar" },
        ],
      };

      option && myChart.setOption(option);
    },
    initPlatform(id, data) {
      var chartDom = document.getElementById(id);
      var myChart = this.$echarts.init(chartDom);
      var option;
      let result = []
      data.forEach(item => {
        if (item.platform === id) {
          let ob = {}
          ob.value = item.playedTime
          ob.name = item.chineseName
          result.push(ob)
        }
      })
      console.log(result)
      option = {
        title: {
          text: id,
          subtext: "Game in year",
          left: "center",
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          orient: "vertical",
          left: "left",
        },
        series: [
          {
            name: "Access From",
            type: "pie",
            radius: "50%",
            data: result,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };

      option && myChart.setOption(option);
    },
  },
  mounted() {
    let m = new Map();
    this.$axios.get("/query").then((res) => {
      if (res.status === 200 && res.data.code === 0) {
        let data = res.data.data;
        data.forEach((item) => {
          if (!m.has(item.year)) {
            m.set(item.year, new Array());
            this.years.push(item.year);
          }
          m.get(item.year).push(item);
          this.map = m;
        });
        this.initHistory(m);
        this.initPlatform("Switch", m.get(this.year));
        this.initPlatform("PlayStation", m.get(this.year));
        this.initPlatform("Xbox", m.get(this.year));
        this.initPlatform("PC", m.get(this.year));
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
