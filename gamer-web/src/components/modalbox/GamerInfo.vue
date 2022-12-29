<template>
  <el-dialog
    v-model="subDialogTableVisible"
    title="表单处理"
    :close-on-click-modal="false"
  >
    <el-form :model="form" label-width="120px">
      <el-form-item label="游戏名称">
        <el-input v-model="form.gameName" />
      </el-form-item>
      <el-form-item label="中文名称">
        <el-input v-model="form.chineseName" />
      </el-form-item>
      <el-form-item label="游戏平台">
        <el-select v-model="form.platform" placeholder="请选择游戏平台">
          <el-option label="Switch" value="Switch" />
          <el-option label="PlayStation" value="PlayStation" />
          <el-option label="Xbox" value="Xbox" />
        </el-select>
      </el-form-item>
      <el-form-item label="游玩时长">
        <el-input v-model="form.playedTime" />
      </el-form-item>
      <el-form-item label="评分">
        <el-rate :max="10" show-score="true" v-model="form.stars" allow-half />
      </el-form-item>
      <el-form-item label="封面">
        <el-upload
          action="#"
          :file-list="form.file"
          :multiple="false"
          :auto-upload="false"
          :before-upload="beforeUpload"
          list-type="picture-card"
          ref="upload"
          limit="1"
        >
          <el-button link :type="plain"
            ><el-icon><Plus /></el-icon
          ></el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="年份">
        <el-input v-model="form.year" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
import { Plus } from "@element-plus/icons";
export default {
  name: "GamerInfo",
  components: {
    Plus,
  },
  data() {
    return {
      form: {
        gameName: "",
        chineseName: "",
        platform: "",
        playedTime: "",
        stars: 0,
        year: "",
      },
      subDialogTableVisible: false,
      fileList: [],
      param: "",
    };
  },
  methods: {
    showDialog() {
      this.subDialogTableVisible = true;
    },
    onSubmit() {
      this.$refs.upload.submit()
      this.param.append("gameName", this.form.gameName);
      this.param.append("chineseName", this.form.chineseName);
      this.param.append("platform", this.form.platform);
      this.param.append("playedTime", this.form.playedTime);
      this.param.append("stars", this.form.stars);
      this.param.append("year", this.form.year);
      this.$parent.submit(this.param);
    },
    beforeUpload(file) {
      this.param = new FormData()
      this.fileList.push(file);
      let images = [...this.fileList];
      images.forEach((img) => {
        this.param
        this.param.append('file', img); // 把单个图片重命名，存储起来（给后台）
      });
      return new Promise((reject) => {
        return reject(false)
      })
    }
  },
};
</script>
<style scoped>
</style>