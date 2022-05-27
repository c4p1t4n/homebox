import "../assets/css/searchResult.css"

import CardSearch from "../components/cardSearch"
import Header from "../components/header"

import imgJose from "../assets/img/joseRicardoCustumer.png"

function SearchResult() {
    return (
        <>
            <Header />
            <div className="container">
                <div className="body">
                    <h2>Exibindo resultado para {"'Pintor'"}</h2>
                    <div className="cardCustumerDiv">
                        <CardSearch
                            img={imgJose}
                            name={"José Ricardo"}
                            category={"Pintor"}
                            aval={4}
                            dist={2}
                        />
                    </div>
                </div>
            </div>
        </>
    )
}

export default SearchResult
