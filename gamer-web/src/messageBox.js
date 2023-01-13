export default {
    errorMessageBox(_vue, message = "error") {
        _vue.$message({
            message: message,
            type: "error",
        });
    },
    successMessageBox(_vue, message = "success") {
        _vue.$message({
            message: message,
            type: "success",
        });
    },
    infoMessageBox(_vue, message = "info") {
        _vue.$message({
            message: message,
            type: "info",
        });
    }
} 