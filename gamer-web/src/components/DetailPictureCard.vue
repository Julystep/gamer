<template>
  <el-card :body-style="{ padding: '0px' }">
    <div class="img_div">
      <el-image class="image" width="100%;" :src="url" :fit="fit" />
      <div class="mask">
        <h1>
          <el-row>
            <el-col class="icon" :span="3" :offset="6">
              <h1 @click="print()">
                <el-icon><Edit /></el-icon>
              </h1>
            </el-col>
            <el-col class="icon" :span="3" :offset="6">
              <h1 @click="print1()">
                <el-icon><Delete /></el-icon>
              </h1>
            </el-col>
          </el-row>
        </h1>
      </div>
    </div>
    <div style="padding: 14px; height: 58px">
      <span>异度神剑3</span>
      <div class="bottom">
        <el-tag class="ml-2" type="danger">Switch</el-tag>
        <el-tag class="ml-2" type="warning">{{ time }}小时</el-tag>
      </div>
    </div>
  </el-card>
</template>
<script>
import { Delete, Edit } from "@element-plus/icons";

export default {
  name: "DetailPictureCard",
  components: {
    Delete,
    Edit,
  },
  props: {
    url: String,
    time: String,
    platform: String,
    gameName: String,
  },
  methods: {
    print() {
      console.log('oh click')
    },
    print1() {
      console.log('oh click1111111')
    }
  }
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
}
</style>