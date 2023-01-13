<template>
  <div :id="id" class="main_container" style="width: 100%; height: 600px"></div>
</template>
  <script>
export default {
  name: "game_category",
  props: {
    id: String,
  },
  methods: {
    initHistory(m) {
      var chartDom = document.getElementById("history");
      var myChart = this.$echarts.init(chartDom);
      var option;
      let source = [];
      source.push(["product", "Nintendo", "PlayStation", "Xbox", "PC"]);
      m.forEach((values, key) => {
        let sourceItem = [];
        sourceItem.push(key);
        let nintendoNum = 0;
        let playStationNum = 0;
        let xboxNum = 0;
        let pcNum = 0;
        values.forEach((item) => {
          if (item.platform === "Nintendo") {
            nintendoNum += item.playedTime;
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
        sourceItem.push(nintendoNum);
        sourceItem.push(playStationNum);
        sourceItem.push(xboxNum);
        sourceItem.push(pcNum);
        source.push(sourceItem);
      });
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
  },
};
</script>
  