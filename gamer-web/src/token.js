export default {
    token: '',
    setToken(token) {
        this.token = token
        localStorage.setItem("token", token)
    },
    getToken() {
        return localStorage.getItem("token")
    }
}