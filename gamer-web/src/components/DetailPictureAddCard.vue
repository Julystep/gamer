<template>
  <el-card :body-style="{ padding: '0px' }" @click="reverseShow()">
    <div class="img_div">
      <div class="mask">
        <el-row>
          <el-col :span="4" :offset="10">
            <h1 class="h1">
              <el-icon class="icon"><Plus /></el-icon>
            </h1>
          </el-col>
        </el-row>
      </div>
    </div>
  </el-card>
  <GamerInfo ref="game" />
</template>
<script>
import { Plus } from "@element-plus/icons";
import GamerInfo from "./modalbox/GamerInfo.vue";
export default {
  name: "DetailPictureAddCard",
  data() {
    return {
      dialogTableVisible: false,
    };
  },
  components: {
    Plus,
    GamerInfo,
  },
  inject: ['reload'],
  methods: {
    reverseShow() {
      this.$refs.game.showDialog();
    },
    submit(form) {
      this.$axios.post("/save", form).then((res) => {
        if (res.status === 200 && res.data.code === 0) {
          this.$message({
            message: "新增成功",
            type: "success",
          });
        } else {
          this.$message({
            message: "新增失败",
            type: "error",
          });
        }
        this.reload()
      });
    },
  },
};
</script>
<style scoped>
.el-row.detail_row > .el-col {
  height: 100px;
  background-color: white;
}
.time {
  font-size: 12px;
  color: #999;
}
.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.img_div {
  height: 386px;
  display: block;
  position: relative;
}
.mask {
  position: absolute;
  background: rgba(101, 101, 101, 0.6);
  color: #ffffff;
  opacity: 1;
  top: 0;
  right: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
}
.mask .el-row .el-col {
  margin-top: 40%;
  text-align: center;
  font-size: 25px;
}
.img_div:hover {
  cursor: pointer;
}
</style>