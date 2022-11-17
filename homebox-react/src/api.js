import axios from "axios"

const api = axios.create({
    baseURL: "http://54.198.76.123:8080/"
    // baseURL: "http://localhost:8000/"
})
api.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

export default api