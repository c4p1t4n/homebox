import Header from "../components/header";
import ProfileClientComp from "../components/profileClientComp";
import CardDashboardCLient from "../components/cardDashboardClient";
import "../assets/css/profileClient.css"


function ProfileClient() {
    return (
        <>
            <Header />
            <ProfileClientComp />
            <div className="divDashboardClint">
                <h4 className="dashH4">Outros moradores de(a) Vila Madalena se interessam por serviços de</h4>
                <div className="dashboard">
                    <CardDashboardCLient />
                </div>
            </div>
        </>
    )
}

export default ProfileClient