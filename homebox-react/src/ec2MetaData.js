import axios from "axios"

const metaData = axios.create({
    baseURL: "http://169.254.169.254/latest/meta-data/iam/security-credentials/LabRole"
})
metaData.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

export default metaData