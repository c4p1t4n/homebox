import "../assets/css/cardDashboardClient.css"

import accessIcon from "../assets/img/accessIcon.png"
import imgLoading from "../assets/img/iconLoading.gif"


function CardDashboardCLient() {
    return (
        <>
            <div className="cardDashboardClient">
                <img className="serviceIMG" src={imgLoading} alt="serviço de ..." />
                <p>xxxxxxxxxxxxx</p>
                <div className="divAcess">
                    <p>ACESSAR</p>
                    <img className="iconAccess" src={accessIcon} alt="seta para acessar o serviço ..." />
                </div>
            </div>
        </>
    )
}

export default CardDashboardCLient