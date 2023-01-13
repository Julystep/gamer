import http from './http'

export default {
    queryYears(params = {}, headers = {}) {
        return http.httpGet(queryYearsUrl, params, headers)     
    },
    queryGameData(id = -1, params = {}, headers = {}) {
        let aimUrl = queryGameDataUrl
        if (id !== -1) {
            aimUrl = aimUrl + "/" + id
        }
        return http.httpGet(aimUrl, params, headers)
    },
    updateGameData(params = {}, headers = {}) {
        return http.httpPost(updateGameUrl, params, headers)
    },
    deleteGame(id, params={}, headers={}) {
        return http.httpPost(deleteGameUrl + '/' + id, params, headers)
    },
    saveGame(params={}, headers={}) {
        return http.httpPost(saveGameUrl, params, headers)
    },
    isLogin() {
        return http.httpPost(isLoginUrl)
    },
    login(username, password) {
        let aimUrl = loginUrl + "?username=" + username + "&password=" + password
        return http.httpPost(aimUrl)
    }
}

let queryYearsUrl = '/query/years'
let queryGameDataUrl = '/query'
let updateGameUrl = '/update'
let deleteGameUrl = '/delete'
let saveGameUrl = '/save'
let isLoginUrl = '/isLogin'
let loginUrl = '/login'