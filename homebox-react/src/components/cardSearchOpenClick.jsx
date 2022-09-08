import CardOpenSearchClick from "../components/cardOpenSearchClick"
import DynamicStars from "../components/dynamicStart"
import joseRicardo from "../assets/img/joseRicardoCustumer.png"
import iconCloseBlue from "../assets/img/iconCloseBlue.png"

function cardSearchOpenClick(props) {

    const close = e => {
        console.log("close")
        document.getElementById(props.name + props.id_user).innerHTML = ""
    };



    return (
        <>

            <div id="cardSearchOpenID" className="cardSearchOpen">
                <div className="cardSearchOpenDiv">
                    <div className="cardSearchOpenDivIconClose">
                        <img onClick={close} src={iconCloseBlue} alt="Icone de fechar" />
                    </div>
                    <div className="cardSearchOpenDivTopInfo">
                        <div className="cardSearchOpenDivTopInfoInside">
                            <div className="cardSearchOpenDivTopInfoInsideP">
                                <p>{props.name}</p>
                                <p>{props.category}</p>
                            </div>
                            <img className="imgProvider" src={props.img} alt="Imagem do prestador" />
                            <div className="cardSearchOpenDivTopInfoInsideAvarage">
                                <p>{props.rating.toFixed(1)}/5</p>
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



// const closeDivProviderCard = e => {
//     e.preventDefault()
//     const openDivProviderCardOpen = document.getElementById("cardSearchOpenID")
//     console.log(openDivProviderCardOpen)
//     openDivProviderCardOpen.style.display = "none"
// }