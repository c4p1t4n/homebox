import "../assets/css/searchResult.css"
import VLibras from "@djpfs/react-vlibras"
import iconLoading from "../assets/img/iconLoading.gif"
import CardSearch from "../components/cardSearch"
import CardSearchOpenClick from "../components/cardSearchOpenClick"
import Header from "../components/header"
import profileImg from "../assets/img/profileIcon.png"
import { useEffect, useState } from "react"
import { search } from "../assets/js/search"


function SearchResult() {
    const queryString = window.location.search
    const parameters = new URLSearchParams(queryString)
    const searchValue = parameters.get("search")

    const [searchResult, setSearchResult] = useState([])

    useEffect(() => {
        search(searchValue, JSON.parse(sessionStorage.getItem("user")).id_user)
            .then(value => {
                document.getElementById("loadingDiv").style.display = "none"
                if (!value[0]) {
                    document.getElementById("titleSearch").innerHTML = `Nenhum resultado para "${searchValue}"`;
                }
                console.log(value)
                console.log(!value)
                setSearchResult(value)
            })
            .catch(err => {
                setSearchResult([])
            })
    }, [searchValue])
    if (!(sessionStorage.getItem('user'))) {
        window.location.href = "/login"
    }
    return (
        <>
            <div id="loadingDiv">
                <img src={iconLoading} alt="Carregando a pagina" />
            </div>
            <VLibras />
            <Header search={`${searchValue}`} />
            <div className="container">
                <div className="body">
                    <h2 id="titleSearch">Exibindo resultado para {`"${searchValue}"`}</h2>
                    <div className="cardCustumerDiv">
                        {searchResult
                            ? searchResult.map(item => (
                                <CardSearch
                                    img={item.user?.picture ?? profileImg}
                                    name={item.user?.name}
                                    category={item?.category}
                                    rating={item.rating ?? "N/A"}
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

