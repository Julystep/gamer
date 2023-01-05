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
        v-if="index < data.length - 1"
        :data="o"
        :url="o.picturePath"
        :gameName="o.chineseName"
        :platform="o.platform"
        :time="o.playedTime"
        :yearId="yearId"
      />
      <DetailPictureAddCard
        v-if="this.$token.getToken() !== '' && index >= data.length - 1"
        :yearId="yearId"
      />
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
      data: [],
      yearId: this.$route.query.id,
    };
  },
  computed: {
    computeOffset(index) {
      if (index == 0) return 1;
      else return 2;
    },
    show(index) {
      return this.$token.getToken() !== "" && index >= 2;
    },
  },
  methods: {
    queryData() {
      this.$axios
        .post(
          "/isLogin",
          {},
          {
            headers: { token: this.$token.getToken() },
          }
        )
        .then((res) => {
          if (res.status !== 200 || res.data.code !== 0) {
            this.$token.setToken("");
          } 
        });
      this.$axios.get("/query/" + this.$route.query.id).then((res) => {
        if (res.status === 200 && res.data.code === 0) {
          this.data = res.data.data;
          this.data.push(1);
        } else {
          this.$message({
            message: "查询失败",
            type: "error",
          });
        }
      });
    },
  },
  mounted() {
    this.queryData();
  },
};
</script>
<style scoped>
</style>
