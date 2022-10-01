import Header from "../components/header";
import ProfileClientComp from "../components/profileClientComp";
import CardDashboardCLient from "../components/cardDashboardClient";
import CardLastServiceHistory from "../components/cardLastServiceHistory";
import "../assets/css/profileClient.css"


function ProfileClient() {
    return (
        <>
            <Header />
            <ProfileClientComp
                nome={nome}
                email={email}
            />
            <div className="divDashboardClint">
                <h4 className="dashH4">Outros moradores de(a) Vila Madalena se interessam por serviços de</h4>
                <div className="dashboard">
                    <CardDashboardCLient />
                    <CardDashboardCLient />
                    <CardDashboardCLient />
                </div>
            </div>
            <div className="historyOfServiceClientDiv">
                <h3>Historico de Serviços</h3>
                <div className="historyOfServiceClientDivOverflow">
                    <CardLastServiceHistory />
                    <CardLastServiceHistory />
                </div>
            </div>
            <div id="endServiceDivClient" className="endServiceDiv">
                <div className="endServiceDivIn">
                    <p>Enviamos um código para o email do cliente, insira este código abaixo</p>
                    <input type="text" />
                    <div className="endServiceDivInButton">
                        <button>Finalizar serviço</button>
                        <button onClick={closeendServiceDiv}>Sair</button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ProfileClient

function closeendServiceDiv() {
    document.getElementById("endServiceDivClient").style.display = "none"
}

const nome = JSON.parse(sessionStorage.getItem("user"))?.name?.split(" ")?.[0] ?? ""
const email = JSON.parse(sessionStorage.getItem("user"))?.email

