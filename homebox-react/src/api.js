import axios from "axios"

const api = axios.create({
    //baseURL: "http://localhost:8080/"
    baseURL:"https://6286d0a7e9494df61b2db935.mockapi.io/"
})

export default api
