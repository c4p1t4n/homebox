import axios from "axios"

const metaData = axios.create({
    baseURL: "http://169.254.169.254/"
})

export default metaData