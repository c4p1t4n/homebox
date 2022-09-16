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
                    <CardLastServiceHistory/>
                    <CardLastServiceHistory/>
                    <CardLastServiceHistory/>
                    <CardLastServiceHistory/>
                    <CardLastServiceHistory/>
                </div>
            </div>
        </>
    )
}

export default ProfileClient


const nome = JSON.parse(sessionStorage.getItem("user"))?.name?.split(" ")?.[0] ?? ""
const email = JSON.parse(sessionStorage.getItem("user"))?.email

