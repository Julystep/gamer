import axios from 'axios'

const API = axios.create( {
    baseURL: '/',
    timeout: 20000
})

export default API