import Header from "../components/header";
import ProfileClientComp from "../components/profileClientComp";
import CardLastServiceHistory from "../components/cardLastServiceHistory";
import CardDashboardCLient from "../components/cardDashboardClient";
import "../assets/css/profileClient.css"

import { useEffect, useState } from "react"
import api from "../api"


function ProfileClient() {
    const [services, setServices] = useState([])
    useEffect(() => {
        api.get(`/services/list/client/` + JSON.parse(sessionStorage.getItem("user")).id_user
        ).then(({ status, data }) => {
            if (status === 200) {
                setServices(data)
            }
        })
    }, [services])

    api.get(`/users/` + JSON.parse(sessionStorage.getItem("user")).id_user
    ).then(({ status, data }) => {
        if (status === 200) {
            sessionStorage.setItem(
                "user",
                JSON.stringify({ ...data })
            )
        }
    })


    return (
        <>
            <Header />
            <ProfileClientComp
                nome={nome}
                email={email}
                image = {img}
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
                    {services.map(item =>(
                        <CardLastServiceHistory 
                            id = {item.id}
                            service = {item.nameService}
                            price = {item.price}
                            date = {item.date}
                            client = {item.customerId}
                            local = {item.address}
                        />
                ))}
                </div>
            </div>
            <div id="endServiceDivClient" className="endServiceDiv">
                <div className="endServiceDivIn">
                    <p>Enviamos um código para o email do cliente, insira este código abaixo</p>
                    <input id="pin" type="text" />
                    <div className="endServiceDivInButton">
                        <button onClick={finishService}>Finalizar serviço</button>
                        <button onClick={closeendServiceDiv}>Sair</button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ProfileClient

function finishService(){
    var pin = document.getElementById("pin").value
    var id = JSON.parse(sessionStorage.getItem("service")).idService
    
    api.patch(`/schedulings/status/${id}/done`
    ).then(({ status, data }) => {
        if (status === 201) {
            alert("Serviço Finalizado!!!")
            closeendServiceDiv()
        }
    })
}


function closeendServiceDiv() {
    document.getElementById("endServiceDivClient").style.display = "none"
}

const nome = JSON.parse(sessionStorage.getItem("user"))?.name?.split(" ")?.[0] ?? ""
const email = JSON.parse(sessionStorage.getItem("user"))?.email
const img =  JSON.parse(sessionStorage.getItem("user"))?.picture

