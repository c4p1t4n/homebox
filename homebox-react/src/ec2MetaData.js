import axios from "axios"

const metaData = axios.create({
    baseURL: "https://s5voewdlqi4lqbifidcackw2540ncjek.lambda-url.us-east-1.on.aws"
})

export default metaData