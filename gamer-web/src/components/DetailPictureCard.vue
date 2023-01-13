<template>
  <el-card :body-style="{ padding: '0px' }">
    <div class="img_div">
      <el-image class="image" width="100%;" :src="url" fit="cover" />
      <div class="mask" v-show="this.$token.getToken() !== ''">
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
      <div class="mid">
        <span>{{ data.chineseName }}</span>
        <el-tag class="ml-2" :type="calPlatform(data.platform)">{{
          data.platform
        }}</el-tag>
      </div>
      <div class="bottom">
        <el-rate
          :model-value="data.stars"
          allow-half
          disabled="true"
          :max="10"
        />
        <el-tag class="ml-2" type="warning">{{ data.playedTime }}小时</el-tag>
      </div>
    </div>
  </el-card>
  <GamerInfo ref="game" :yearId="yearId" />
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
  inject: ["reload"],
  props: {
    url: String,
    time: String,
    platform: String,
    gameName: String,
    data: Object,
    yearId: Number,
  },
  methods: {
    reverseShow(id) {
      this.$refs.game.showDialogWithDetail(id);
    },
    submit(form) {
      let result = this.$gameRequest.updateGameData(form, {});
      result
        .then((res) => {
          if (res.code === -1) {
            this.$gameMessageBox.errorMessageBox(this, "更新失败")
          } else if (res.code === 302) {
            this.$gameMessageBox.errorMessageBox(this, "权限不足")
            this.$token.setToken("");
          } else {
            this.$gameMessageBox.successMessageBox(this, "更新成功")
          }
        })
        .catch((error) => {
          console.log(error);
          this.$gameMessageBox.errorMessageBox(this, "更新失败")
        });
      this.reload();
    },
    deleteById(id) {
      this.$confirm("确定删除？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let result = this.$gameRequest.deleteGame((id));
          result
            .then((res) => {
              if (res.code === -1) {
                this.$gameMessageBox.errorMessageBox(this, "删除失败")
              } else if (res.code === 302) {
                this.$gameMessageBox.errorMessageBox(this, "权限不足")
                this.$token.setToken("");
              } else {
                this.$gameMessageBox.successMessageBox(this, "删除成功")
                this.reload();
              }
            })
            .catch((error) => {
              console.log(error)
              this.$gameMessageBox.errorMessageBox(this, "删除失败")
            });
          }).catch(() => {
            this.$gameMessageBox.infoMessageBox(this, "取消删除")
          })
      },
    calPlatform(platform) {
      console.log(platform);
      if (platform === "Nintendo") {
        return "danger";
      }
      if (platform === "PlayStation") {
        return "primary";
      }
      if (platform === "Xbox") {
        return "success";
      }
      if (platform === "PC") {
        return "info";
      }
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

.mid {
  display: flex;
  justify-content: space-between;
  align-items: center;
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