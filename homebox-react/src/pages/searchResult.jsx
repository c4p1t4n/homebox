import "../assets/css/searchResult.css"

import CardSearch from "../components/cardSearch"
import Header from "../components/header"
import api from "../api"
import imgJose from "../assets/img/joseRicardoCustumer.png"
import { useEffect, useState } from "react"

function SearchResult() {
    const [custumer, setCustumer] = useState([]);

    useEffect(() => {
        api.get("/musicas").then((resposta) => {
            console.log(resposta.data)
            setCustumer(resposta.data)
        })
    }, [])

    return (
        <>
            <Header />
            <div className="container">
                <div className="body">
                    <h2>Exibindo resultado para {"'Pintor'"}</h2>
                    <div className="cardCustumerDiv">
                        {
                            custumer.map((itemMusica) => (
                                <CardSearch name={itemMusica.nome}
                                img ={itemMusica.img}
                                dist={itemMusica.number}
                                aval={itemMusica.number}
                                     />
                            ))
                        }
                    </div>
                </div>
            </div>
        </>
    )
}

export default SearchResult
