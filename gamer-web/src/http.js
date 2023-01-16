import axios from 'axios'
import token from './token.js'

const http = axios.create( {
    baseURL: window.global.baseURL,
    timeout: 20000
})


function headersDeal(headers) {
    headers.token = token.getToken()
}

export default {
    // http Get 请求
    async httpGet(url, params = {}, headers = {}) {
        // let resultBody = new Map()
        let resultBody = {}
        headersDeal(headers)
    
        await http
        .get(url, params, {headers: headers})
        .then(res => {
            if (res.status === 200 && res.data.code === 0) {
                // resultBody.set('code', 0)
                // resultBody.set('data', res.data.data)
                resultBody.code = 0
                resultBody.data = res.data.data
            } else if (res.status === 302){
                resultBody.code = 302
            } else {
                resultBody.code = -1
            }
        })
        return resultBody
    },
    // http Post 请求
    async httpPost(url, params = {}, headers = {}) {
        let resultBody = new Map()
        headersDeal(headers)
        console.log(headers)
        await http
        .post(url, params, {headers: headers})
        .then(res => {
            if (res.status === 200 && res.data.code === 0) {
                resultBody.code = 0
                resultBody.data = res.data.data
            } else if (res.status === 302){
                resultBody.code = 302
            } else {
                resultBody.code = -1
            }
        })
        return resultBody
    }
}