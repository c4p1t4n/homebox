import { useEffect, useState } from "react"
import api from "../api"
import "../assets/css/homePage.css"
import Plumber from "../assets/img/plumber.png"
import Card from "../components/card"
import Footer from "../components/footer"
import iconLoading from "../assets/img/iconLoading.gif"

import Header from "../components/header"
import FrequentSearchCard from "../components/frequentSearchCard"
import FrequentSearchCardDistance from "../components/frequentSearchCardDistance"

import profileImg from "../assets/img/profileIcon.png"
import brushIcon from "../assets/img/brushIcon.png"
import electrician from "../assets/img/electrician.png"
import accessIcon from "../assets/img/accessIcon.png"
import accessIconWhite from "../assets/img/accessIconWhite.png"
import arrowLeft from "../assets/img/arrowLeft.png"
import arrowRight from "../assets/img/arrowRigth.png"
import imgMaria from "../assets/img/MariaAntoniaCustumer.png"
import imgJose from "../assets/img/joseRicardoCustumer.png"
import imgLeo from "../assets/img/leonardoCustumer.png"
import img5Stars from "../assets/img/fiveStarts.png"
import img4Stars from "../assets/img/fourStars.png"
import img3Stars from "../assets/img/threeStars.png"
import VLibras from "@djpfs/react-vlibras"

const frequent_search = "'pintor'"

function Home() {
    const [worker, setWorker] = useState([])

    useEffect(() => {
        api.get(
            `/users/recomendation/${JSON.parse(sessionStorage.getItem("user")).id_user
            }`
        ).then(({ status, data }) => {
            document.getElementById("loadingDivHome").style.display = "none"
            if (status === 200) {
                console.log(data)
                setWorker(data)
            }
        })
    }, [])

    return (
        <>
            <VLibras />
            <Header />
            <div className="container">
                <div className="body">
                    <h2>Serviços mais acessados por você</h2>
                    <div className="cards">
                        <Card
                            categoria="Pintura"
                            icon={brushIcon}
                            class="card"
                            iconAccesses={accessIcon}
                        />
                        <Card
                            categoria="Elétrica"
                            icon={electrician}
                            class="cardBlue"
                            color="white"
                            iconAccesses={accessIconWhite}
                        />
                        <Card
                            categoria="Encanamento"
                            icon={Plumber}
                            class="card"
                            iconAccesses={accessIcon}
                        />
                    </div>
                    <br />
                    <br />
                    <h2>Recomendações para você</h2>
                    <div className="cardCustumerDiv1">
                        <div id="loadingDivHome">
                            <img src={iconLoading} alt="Carregando a pagina" />
                        </div>
                        <div className="cardCustumerDiv2">
                            {worker.map(item => (
                                <FrequentSearchCardDistance
                                    class={"cardCustumer3"}
                                    img={item.user?.picture ?? profileImg}
                                    name={item.user?.name}
                                    category={item?.category}
                                    rating={item.rating}
                                    ratingStr={item.rating ?? "N/A"}
                                    dist={item.distance ?? "N/A"}
                                />
                            ))}
                        </div>
                    </div>
                    <br />
                </div>
            </div>
            <Footer />
        </>
    )
}

export default Home
