<template>
  <el-card :body-style="{ padding: '0px' }">
    <div class="img_div">
      <el-image class="image" width="100%;" :src="url" :fit="fit" />
      <div class="mask">
        <h1>
          <el-row>
            <el-col class="icon" :span="3" :offset="6">
              <h1 @click="reverseShow(data.id)">
                <el-icon><Edit /></el-icon>
              </h1>
            </el-col>
            <el-col class="icon" :span="3" :offset="6">
              <h1 @click="deleteById(data.id)">
                <el-icon><Delete /></el-icon>
              </h1>
            </el-col>
          </el-row>
        </h1>
      </div>
    </div>
    <div style="padding: 14px; height: 58px">
      <span>{{ data.chineseName }}</span>
      <div class="bottom">
        <el-tag class="ml-2" type="danger">{{ data.platform }}</el-tag>
        <el-tag class="ml-2" type="warning">{{ data.playedTime }}小时</el-tag>
      </div>
    </div>
  </el-card>
  <GamerInfo ref="game" />
</template>
<script>
import { Delete, Edit } from "@element-plus/icons";
import GamerInfo from "./modalbox/GamerInfo.vue";
export default {
  name: "DetailPictureCard",
  components: {
    Delete,
    Edit,
    GamerInfo,
  },
  inject: ['reload'],
  props: {
    url: String,
    time: String,
    platform: String,
    gameName: String,
    data: Object,
  },
  methods: {
    reverseShow(id) {
      this.$refs.game.showDialogWithDetail(id);
    },
    submit(form) {
      this.$axios.post("/update", form).then((res) => {
        if (res.status === 200 && res.data.code === 0) {
          this.$message({
            message: "更新成功",
            type: "success",
          });
          this.reload();
        } else {
          this.$message({
            message: "更新失败",
            type: "error",
          });
        }
      });
    },
    deleteById(id) {
      this.$confirm("确定删除？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios.post("/delete/" + id).then((res) => {
            console.log(res);
            if (res.status === 200 && res.data.code === 0) {
              this.$message({
                message: "删除成功",
                type: "success",
              });
              this.reload()
            } else {
              this.$message({
                message: "删除失败",
                type: "error",
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
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

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  height: 300px;
  display: block;
  cursor: pointer;
}
.img_div {
  border-radius: 10px;
  display: block;
  position: relative;
}
.mask {
  position: absolute;
  background: rgba(101, 101, 101, 0.6);
  color: #ffffff;
  opacity: 0;
  top: 0;
  right: 0;
  width: 100%;
  height: 100%;
}
.mask .el-row .el-col {
  margin-top: 20%;
  text-align: center;
}
.img_div:hover .mask {
  opacity: 1;
  cursor: pointer;
}
</style>