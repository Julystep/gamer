<template>
  <el-dialog
    v-model="subDialogTableVisible"
    title="表单处理"
    :close-on-click-modal="false"
    width="20%"
  >
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="用户名" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleForm)"
          >立即登录</el-button
        >
        <el-button @click="resetForm(ruleForm)">重置</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
  <script>
export default {
  inject: ["reload"],
  data() {
    return {
      subDialogTableVisible: false,
      ruleForm: {
        name: "",
        password: "",
      },
      rules: {
        name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        password: [
          { required: true, message: "请输入密码", trigger: "change" },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      console.log(formName);
      this.$axios
        .post(
          "/login?username=" + formName.name + "&password=" + formName.password
        )
        .then((res) => {
          if (res.status === 200 && res.data.code === 0) {
            this.$token.setToken(res.data.data.tokenValue);
            this.$message({
              message: "登录成功",
              type: "success",
            });
            this.subDialogTableVisible = false;
            this.reload();
          } else {
            this.$message({
              message: "登录失败",
              type: "danger",
            });
          }
        });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    showDialog() {
      this.$axios
        .post(
          "/isLogin",
          {},
          {
            headers: { token: this.$token.getToken() },
          }
        )
        .then((res) => {
          if (res.status === 200 && res.data.code === 0) {
            this.$message({
              message: "已登录",
              type: "success",
            });
          } else {
            this.$token.setToken("");
            this.subDialogTableVisible = true;
          }
        });
    },
  },
};
</script>