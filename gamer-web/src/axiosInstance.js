import axios from 'axios'

const API = axios.create( {
    baseURL: 'http://127.0.0.1:8083',
    timeout: 20000
})

export default API