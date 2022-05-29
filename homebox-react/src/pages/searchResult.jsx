import "../assets/css/searchResult.css"

import CardSearch from "../components/cardSearch"
import Header from "../components/header"
import api from "../api"
import imgJose from "../assets/img/joseRicardoCustumer.png"
import {useEffect, useState} from "react"
import {search} from "../assets/js/search"

function SearchResult() {
    const queryString = window.location.search
    const parameters = new URLSearchParams(queryString)
    const searchValue = parameters.get("search")

    const [searchResult, setSearchResult] = useState([])

    useEffect(() => {
        search(searchValue)
            .then(value => {
                console.log(value)
                setSearchResult(value)
            })
            .catch(err => console.warn(err))
    }, [searchValue])

    return (
        <>
            <Header search={`${searchValue}`} />
            <div className="container">
                <div className="body">
                    <h2>Exibindo resultado para {`"${searchValue}"`}</h2>
                    <div className="cardCustumerDiv">
                        {searchResult.map(item => (
                            <CardSearch
                                img={item.user?.picture ?? imgJose}
                                name={item.user?.name}
                                category={item.user?.category}
                                aval={item.ranking ?? "N/A"}
                                dist={item.distance ?? "N/A"}
                            />
                        ))}
                    </div>
                </div>
            </div>
        </>
    )
}

export default SearchResult
