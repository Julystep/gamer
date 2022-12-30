<template>
  <el-row class="detail_row" :gutter="20">
    <el-col
      v-for="(o, index) in data"
      :key="o"
      :span="5"
      :offset="index % 4 == 0 ? 2 : 0"
      style="padding: 10px"
    >
      <DetailPictureCard
        v-if="index < data.length-1"
        :data="o"
        :url="o.picturePath"
        :gameName="o.chineseName"
        :platform="o.platform"
        :time="o.playedTime"
      />
      <DetailPictureAddCard v-else />
    </el-col>
  </el-row>
</template>
<script>
import DetailPictureCard from "./DetailPictureCard.vue";
import DetailPictureAddCard from "./DetailPictureAddCard.vue";
export default {
  name: "GameDetail",
  components: {
    DetailPictureCard,
    DetailPictureAddCard,
  },
  data() {
    return {
      url: "http://127.0.0.1:8083/picture/Xenoblade_Chronicles/Xenoblade_Chronicles3.jpg",
      time: 100,
      data: [],
    };
  },
  computed: {
    computeOffset(index) {
      if (index == 0) return 1;
      else return 2;
    },
  },
  methods: {},
  mounted() {
    this.$axios.get("/query/2022").then((res) => {
      this.data = res.data.data
      this.data.push(1)
      console.log(this.data)
    });
  },
};
</script>
<style scoped>
</style>
