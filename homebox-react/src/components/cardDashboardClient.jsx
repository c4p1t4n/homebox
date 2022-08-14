import "../assets/css/cardDashboardClient.css"

import accessIcon from "../assets/img/accessIcon.png"
import imgNotFound from "../assets/img/404.gif"


function CardDashboardCLient() {
    return (
        <>
            <div className="cardDashboardClient">
                <img src={imgNotFound} alt="serviço de ..." />
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