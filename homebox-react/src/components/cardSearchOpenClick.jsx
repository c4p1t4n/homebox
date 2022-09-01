import CardOpenSearchClick from "../components/cardOpenSearchClick"
import DynamicStars from "../components/dynamicStart"
import joseRicardo from "../assets/img/joseRicardoCustumer.png"
import iconCloseBlue from "../assets/img/iconCloseBlue.png"

function cardSearchOpenClick(props) {
    return (
        <>

            <div id="cardSearchOpenID" className="cardSearchOpen">
                <div className="cardSearchOpenDiv">
                    <div className="cardSearchOpenDivIconClose">
                        <img onClick={teste} src={iconCloseBlue} alt="Icone de fechar" />
                    </div>
                    <div className="cardSearchOpenDivTopInfo">
                        <div className="cardSearchOpenDivTopInfoInside">
                            <div className="cardSearchOpenDivTopInfoInsideP">
                                <p>Jose Ricardo</p>
                                <p>Pintor</p>
                            </div>
                            <img className="imgProvider" src={joseRicardo} alt="Imagem do prestador" />
                            <div className="cardSearchOpenDivTopInfoInsideAvarage">
                                <p>Avaliação: 4.0</p>
                                <DynamicStars
                                    rating={4.0} />
                            </div>
                        </div>
                    </div>
                    <div className="cardSearchOpenDivServices">
                        <CardOpenSearchClick />
                        <CardOpenSearchClick />
                        <CardOpenSearchClick />

                    </div>
                    <button className="buttonInitChat">Iniciar Chat</button>
                </div>
            </div>
        </>
    )
}


export default cardSearchOpenClick


function teste() {
    document.getElementById("cardSearchOpenID").style.display = "none"
}