import "../assets/css/homePage.css"

import Card from "../components/card"
import Footer from "../components/footer"
import Header from "../components/header"
import HeaderProfileOpen from "../components/headerProfileOpen"
import FrequentSearchCard from "../components/frequentSearchCard"
import FrequentSearchCardDistance from "../components/frequentSearchCardDistance"

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

const frequent_search = "'pintor'"

function Home() {
    return (
        <>

            <Header />
            <div className="container">
                <div className="body">
                    <h2>Serviços mais acessados por você</h2>
                    <div className="cards">
                        <Card
                            categoria="Pintor"
                            icon={brushIcon}
                            class="card"
                            iconAccesses={accessIcon}
                        />
                        <Card
                            categoria="Eletricista"
                            icon={electrician}
                            class="cardBlue"
                            color="white"
                            iconAccesses={accessIconWhite}
                        />
                        <Card
                            categoria="Pintor"
                            icon={brushIcon}
                            class="card"
                            iconAccesses={accessIcon}
                        />
                    </div>
                    <br />
                    <br />
                    <h2>Recomendações para você</h2>
                    <br />
                    <br />
                    <h3>Porque você pesquisou por {frequent_search}</h3>
                    <div className="cardCustumerDiv1">
                        <img
                            className="arrows"
                            src={arrowLeft}
                            alt="Seta para esquerda"
                        />
                        <div className="cardCustumerDiv2">
                            <FrequentSearchCard
                                class={"cardCustumer3"}
                                img={imgMaria}
                                name={"Maria Antonia"}
                                category={"Pintora"}
                                stars={5}
                                starImg={img5Stars}
                            />
                            <FrequentSearchCard
                                class={"cardCustumer3"}
                                img={imgJose}
                                name={"José Ricardo"}
                                category={"Pintor"}
                                stars={4}
                                starImg={img4Stars}
                            />
                            <FrequentSearchCard
                                class={"cardCustumer3"}
                                img={imgLeo}
                                name={"Leonardo Silveira"}
                                category={"Pintor"}
                                stars={3}
                                starImg={img3Stars}
                            />
                        </div>
                        <img
                            className="arrows"
                            src={arrowRight}
                            alt="Seta para direita"
                        />
                    </div>
                    <br />
                    <br />
                    <h3>Próximos de você</h3>
                    <div className="cardCustumerDistanceDiv">
                        <FrequentSearchCardDistance
                            class={"cardCustumerDistance"}
                            img={imgMaria}
                            name={"Maria Antonia"}
                            category={"Pintora"}
                            stars={5}
                            starImg={img5Stars}
                            dist={"2.2 KM de distância"}
                        />
                        <FrequentSearchCardDistance
                            class={"cardCustumerDistance"}
                            img={imgJose}
                            name={"José Ricardo"}
                            category={"Pintor"}
                            stars={4}
                            starImg={img4Stars}
                            dist={"2.2 KM de distância"}
                        />
                        <FrequentSearchCardDistance
                            class={"cardCustumerDistance"}
                            img={imgLeo}
                            name={"Leonardo Silveira"}
                            category={"Pintor"}
                            stars={3}
                            starImg={img3Stars}
                            dist={"2.2 KM de distância"}
                        />
                    </div>
                </div>
            </div>
            <Footer />
        </>
    )
}

export default Home
