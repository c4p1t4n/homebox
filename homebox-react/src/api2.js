import axios from "axios"

const viaCep = axios.create({
    baseURL: "http://viacep.com.br/ws/"
})
viaCep.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

export default viaCep