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
      let result = this.$gameRequest.login(formName.name, formName.password)
      result.then(res => {
        if (res.code === -1) {
          this.$gameMessageBox.errorMessageBox(this, "登录失败")
        } else {
          console.log(res.data.tokenValue)
          this.$token.setToken(res.data.tokenValue);
          this.$gameMessageBox.successMessageBox(this, "登录成功")
          this.subDialogTableVisible = false
          this.reload()
        }
      }).catch(error => {
        console.log(error)
        this.$gameMessageBox.errorMessageBox(this, "登录失败")
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    showDialog() {
      let result = this.$gameRequest.isLogin()
      result.then(res => {
        if (res.code === -1) {
          this.$token.setToken("");
          this.subDialogTableVisible = true;
        } else {
          this.$gameMessageBox.successMessageBox(this, "已登录")
        }
      }).catch(error => {
        console.log(error)
      })
    },
  },
};
</script>