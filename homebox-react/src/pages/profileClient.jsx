import "../assets/css/profileClient.css"
import profileImg from "../assets/img/profileIcon.png"
import iconLoading from "../assets/img/iconLoading.gif"
import Header from "../components/header";
import ProfileClientComp from "../components/profileClientComp";
import CardLastServiceHistory from "../components/cardLastServiceHistory";
import FrequentSearchCardDistance from "../components/frequentSearchCardDistance"

import { useEffect, useState } from "react"
import api from "../api"
import viaCep from "../api2"


function ProfileClient() {
    const [bairro, setBairro] = useState([])
    useEffect(() => {
        viaCep.get(JSON.parse(sessionStorage.getItem("user")).cep + `/json`
        ).then(({ status, data }) => {
            if (status === 200) {
                console.log(data)
                setBairro(data)
            }
        })
    }, [])

    function compare(a, b) {
        if (a.status < b.status) {
            return -1;
        }
        if (a.status > b.status) {
            return 1;
        }
        return 0;
    }
    
    const [services, setServices] = useState([])
    useEffect(() => {
        api.get(`/services/list/client/` + JSON.parse(sessionStorage.getItem("user")).id_user
        ).then(({ status, data }) => {
            if (status === 200) {
                setServices(data.sort(compare).reverse())
            }
        })
    }, [services])

    const [worker, setWorker] = useState([])
    useEffect(() => {
        api.get(
            `/users/recomendation/${JSON.parse(sessionStorage.getItem("user")).id_user
            }`
        ).then(({ status, data }) => {
            document.getElementById("loadingDivHome").style.display = "none"
            if (status === 200) {
                console.log(data)
                setWorker(data)
            }
        })
    }, [])


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
                image={img}
            />
            <div className="divDashboardClint">
                <h4 className="dashH4">Moradores de(a) Vila Madalena também contrataram</h4>
                <div id="loadingDivHome">
                    <img src={iconLoading} alt="Carregando a pagina" />
                </div>
                <div className="dashboard">
                    {worker.map(item => (
                        <FrequentSearchCardDistance
                            class={"cardCustumer3"}
                            img={item.user?.picture ?? profileImg}
                            name={item.user?.name}
                            category={item?.category}
                            rating={item.rating ?? 0.0}
                            ratingStr={item.rating ?? 0.0}
                            dist={item.distance ?? "N/A"}
                            id_user={item.user.id_user}
                        />
                    ))}
                </div>
            </div>
            <div className="historyOfServiceClientDiv">
                <h3>Historico de Serviços</h3>
                <div className="historyOfServiceClientDivOverflow">
                    {services.map(item => (
                        <CardLastServiceHistory
                            id={item.idScheduling}
                            service={item.nameService}
                            price={item.price}
                            date={item.date}
                            client={item.customerId}
                            local={item.address}
                            type={item.status}
                        />
                    ))}
                </div>
            </div>
            <div id="endServiceDivClient" className="endServiceDiv">
                <div className="endServiceDivIn">
                    <p>Tem certeza que deseja finalizar este serviço ?</p>
                    {/* <input id="pin" type="text" /> */}
                    <div className="endServiceDivInButton">
                        <button onClick={finishService}>Finalizar serviço</button>
                        <button onClick={closeendServiceDiv}>Sair</button>
                    </div>
                </div>
            </div>
            <div id="ratingForProviderDiv" className="ratingForProviderDiv">
                <div className="ratingForProvider">
                    <h4 id="comentarioAvaliacao">Nos deixe uma nota de avaliação sobre o serviço de XXXX</h4>
                    <div className="sumaryRating">
                        <p>1 - Pessimo</p>
                        <p>2 - Ruim</p>
                        <p>3 - Médio</p>
                        <p>4 - Bom</p>
                        <p>5 - Excelente</p>
                    </div>
                    <div className="inputNote">
                        Digite aqui:
                        <input id='noteService' required  type="number" maxLength={3} />
                    </div>
                    <div className="endServiceDivInButton">
                        <button onClick={saveNote}>Salvar nota</button>
                        <button onClick={closeRatingForProviderDiv}>Sair</button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ProfileClient

function saveNote() {
    var id_service = JSON.parse(sessionStorage.getItem("service")).idService
    var nota = document.getElementById("noteService").value
    let date = document.getElementById(id_service).innerText
    var id_user = JSON.parse(sessionStorage.getItem("user")).id_user
    console.log(nota)
    api.post(`/users/save-note/${id_user}/${nota}/${date.replace(': ','')}`)
    document.getElementById("ratingForProviderDiv").style.display = "none"
}

function closeRatingForProviderDiv() {
    document.getElementById("ratingForProviderDiv").style.display = "none"
}


function finishService() {
    // var pin = document.getElementById("pin").value
    var id = JSON.parse(sessionStorage.getItem("service")).idService

    api.patch(`/schedulings/status/${id}/done`
    ).then(({ status, data }) => {
        if (status === 201) {
            closeendServiceDiv()
        }
    })
    document.getElementById("ratingForProviderDiv").style.display = "flex"
    setNomeProvider()
}
function setNomeProvider(){
    var id = JSON.parse(sessionStorage.getItem("service")).idService
    api.get(`/users/get-name/${id}/`).then(({ status, data }) => {
        if (status === 200) {
            document.getElementById("comentarioAvaliacao").innerText = `Nos deixe uma nota de avaliação sobre o serviço de ${data}`
        }
    })
}


function closeendServiceDiv() {
    document.getElementById("endServiceDivClient").style.display = "none"
}

const nome = JSON.parse(sessionStorage.getItem("user"))?.name?.split(" ")?.[0] ?? ""
const email = JSON.parse(sessionStorage.getItem("user"))?.email
const img = JSON.parse(sessionStorage.getItem("user"))?.picture 

