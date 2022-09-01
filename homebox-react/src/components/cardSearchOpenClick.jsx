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
                                <p>{props.name}</p>
                                <p>{props.category}</p>
                            </div>
                            <img className="imgProvider" src={props.img} alt="Imagem do prestador" />
                            <div className="cardSearchOpenDivTopInfoInsideAvarage">
                                <p>{Math.round(props.rating).toFixed(1)}</p>
                                <DynamicStars
                                    rating={props.rating} />
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
