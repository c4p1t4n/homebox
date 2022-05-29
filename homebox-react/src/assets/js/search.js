import api from "../../api"

const sortThroughRating = (a, b) => {
    if (!a && !b) return 0
    if ((a && b && a.rating > b.rating) || a) return 1
    if ((a && b && a.rating < b.rating) || b) return -1
    return 0
}

const search = async search => {
    return api.post(`users/search/${search}`).then(({status, data}) => {
        if (status === 204) {
            return "NENHUM RESULTADO ENCONTRADO"
        } else if (status === 200) {
            let searchResult = data
            searchResult = searchResult.sort(sortThroughRating)
            return searchResult
        }
    })
}

export {search}
