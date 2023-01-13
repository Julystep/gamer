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
      let result = this.$gameRequest.isLogin()
      result.then(res => {
        if (res === -1) {
          this.$token.setToken("");
        }
      }).catch(error => {
        console.log(error)
      })
      let result2 = this.$gameRequest.queryGameData(this.$route.query.id)
      result2.then(res => {
        if (res === -1) {
          this.$gameMessageBox.errorMessageBox(this, "查询失败")
        } else {
          this.data = res.data;
          this.data.push(1);
        }
      }).catch(error => {
        console.log(error)
        this.$gameMessageBox.errorMessageBox(this, "查询失败")
      })
    },
  },
  mounted() {
    this.queryData();
  },
};
</script>
<style scoped>
</style>
