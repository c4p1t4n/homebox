import axios from "axios"

const api = axios.create({
    // baseURL: "http://10.0.0.39:8080/"
    baseURL: "http://localhost:8080/"
})
api.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

export default api
