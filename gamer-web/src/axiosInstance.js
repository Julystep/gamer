import axios from 'axios'

const API = axios.create( {
    baseURL: window.global.baseURL,
    timeout: 20000
})

export default API