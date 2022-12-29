<template>
    <el-row class="detail_row" :gutter="20">
      <el-col v-for="(o, index) in 10" :key="o" :span="5" :offset="index % 4 == 0 ? 2 : 0" style="padding:10px;">
        <DetailPictureCard v-if="index < 9" :url="url" :gameName="异度神剑3" :platform="Switch" :time="100" />
        <DetailPictureAddCard v-else/>
      </el-col>
    </el-row>
    <el-upload
    v-model:file-list="fileList"
    class="upload-demo"
    action=""
    multiple
    :on-change="success"
    :limit="3"
    :auto-upload="false"
  >
    <el-button type="primary" @click="show">Click to upload</el-button>
    <template #tip>
      <div class="el-upload__tip">
        jpg/png files with a size less than 500KB.
      </div>
    </template>
  </el-upload>
</template>
<script>

import DetailPictureCard from './DetailPictureCard.vue'
import DetailPictureAddCard from './DetailPictureAddCard.vue'
export default {
    name: 'GameDetail',
    components: {
      DetailPictureCard,
      DetailPictureAddCard
    },
    data() {
      return {
        'url': 'http://127.0.0.1:8083/picture/Xenoblade_Chronicles/Xenoblade_Chronicles3.jpg',
        'time': 100,
        fileList: []
      }
    },
    computed: {
      computeOffset(index) {
        if (index == 0) return 1;
        else return 2;
      }
    },
    methods: {
      show() {
        console.log(this.fileList)
      },
      success(uploadFile) {
        console.log(uploadFile)
      }
    }
}
</script>
<style scoped>
</style>
