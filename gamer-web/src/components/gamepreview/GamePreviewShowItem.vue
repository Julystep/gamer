<template>
  <div class="img_div">
    <el-image class="image" :src="url" fit="fill" />
    <div class="mask">
      <h1>
        <p class="gameName">超级马里奥</p>
      </h1>
    </div>
  </div>
</template>
<script>
export default {
  name: "GamePreviewShowItem",
  data() {
    return {
      url: "/picture/123/mario3dword.jpg",
    };
  },
};
</script>
<style scoped>
.image {
  border-radius: 5px;
  height: 100%;
  width: 100%;
}
.img_div {
  position: relative;
  border-radius: 10px;
  display: block;
  width: 100%;
  height: 100%;
}
.mask {
  position: absolute;
  color: #ffffff;
  top: 0;
  right: 0;
  width: 100%;
  height: 100%;
}
.mask .h1 p {
  text-align: center;
}
.gameName {
    margin-top: 50%;
    margin-left: 10px;
    margin-bottom: 0px;
    font-size: 18px;
}
.img_div:hover .mask {
  opacity: 1;
  cursor: pointer;
}
</style>
