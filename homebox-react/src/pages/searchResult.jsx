import "../assets/css/searchResult.css"

import CardSearch from "../components/cardSearch"
import Header from "../components/header"
import imgJose from "../assets/img/joseRicardoCustumer.png"
import {useEffect, useState} from "react"
import {search} from "../assets/js/search"
import api from "../api"

function SearchResult() {
    const queryString = window.location.search
    const parameters = new URLSearchParams(queryString)
    const searchValue = parameters.get("search")
    const {id_user} = JSON.parse(sessionStorage.getItem("user"))

    const [searchResult, setSearchResult] = useState([])

    useEffect(() => {
        if (id_user) {
            api.post("/search", {idUser: id_user, value: searchValue}).then(
                response => {
                    if (response.status === 201) {
                        console.log("SUCESSO")
                    } else {
                        console.log(response)
                    }
                }
            )
        }
        search(searchValue)
            .then(value => {
                console.log(value)
                setSearchResult(value)
            })
            .catch(err => {
                setSearchResult([])
            })
    }, [searchValue, id_user])

    return (
        <>
            <Header search={`${searchValue}`} />
            <div className="container">
                <div className="body">
                    <h2>Exibindo resultado para {`"${searchValue}"`}</h2>
                    <div className="cardCustumerDiv">
                        {searchResult
                            ? searchResult.map(item => (
                                  <CardSearch
                                      img={item.user?.picture ?? imgJose}
                                      name={item.user?.name}
                                      category={item?.category}
                                      ranking={item.ranking ?? "N/A"}
                                      dist={item.distance ?? "N/A"}
                                  />
                              ))
                            : ""}
                    </div>
                </div>
            </div>
        </>
    )
}

export default SearchResult
