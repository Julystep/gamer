<template>
  <div
    :id="id"
    class="main_container"
    style="width: 100%; height: 600px"
  ></div>
</template>
<script>
export default {
  name: "game_veryocal",
  props: {
    id: String,
  },
  methods: {
    initPlatform(id, data) {
      var chartDom = document.getElementById(id);
      var myChart = this.$echarts.init(chartDom);
      var option;
      let result = [];
      if (typeof data !== "undefined") {
        data.forEach((item) => {
          if (item.platform === id) {
            let ob = {};
            ob.value = item.playedTime;
            ob.name = item.chineseName;
            result.push(ob);
          }
        });
      }
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
  }
};
</script>
