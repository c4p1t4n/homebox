import "../assets/css/providerDash.css"
import MenuLeftProvider from "../components/menuLeftProvider"
import ServicesList from "../components/servicesList"
import DynamicStars from "../components/dynamicStart"
import questionMark from "../assets/img/quesntion-mark.png"

import Chart from "react-apexcharts"
import React, { Component } from "react"
import api from "../api"


function getLastSevendays() {
    var date = new Date();
    var list = [];
    var count = 0;
    var list_date = []
    for (var i = 0; i < 7; i++) {

        list.push(date.getDate() - count)
        count++
    }
    for (var x = 0; x <= 6; x++) {
        date = new Date();
        if (list[x] <= list[0]) {
            list_date.push(String(list[x]) + '/' + String(date.getMonth() + 1))
        } else {
            list_date.push(String(list[x]) + '/' + String(date.getMonth()))
        }
    }
    return list_date.reverse()
}


class relatorioProvider extends Component {
    constructor(props) {

        super(props)
        this.state = {
            options: {
                title: {
                    text: "Sua nota ao longo do tempo",
                    align: "center",
                    style: {
                        fontSize: "28px",
                        color: '#174574'
                    }
                },
                chart: {
                    id: "basic-bar"
                },
                xaxis: {
                    categories: [1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998]
                },
                yaxis: {
                    forceNiceScale: true,
                    min: 0,
                    max: 5
                }
            },
            series: [
                {
                    name: "series-1",
                    data: [5, 4, 4.3, 3.7, 4, 5, 5, 4.7]
                }
            ],
            avg: 0,
            visitas_semana: 7
        };
    }

    async componentDidMount() {
        let id_user = JSON.parse(sessionStorage.getItem('user')).id_user

        let temp_value = await api.get(`/users/avg-rating/${id_user}`).catch(err => err.response)
        this.setState({ avg: (temp_value.data === 0 ? "0" : temp_value.data.toFixed(2)) })

        let visitas_semana = await api.get(`interestAccess/avg_last_seven_days/${id_user}`,).catch(err => err.response)
        this.setState({ visitas_semana: (visitas_semana.data === 0 ? "0" : visitas_semana.data) })

        let list_medias_ultima_semana = await api.get(`interestAccess/getListAvgLastSevenDays/${id_user}`,).catch(err => err.response)
        this.setState({
            series: [
                {
                    data: list_medias_ultima_semana.data,
                }
            ]

        });

        this.setState({
            options: {
                title: {
                    text: "Sua nota ao longo do tempo",
                    align: "center",
                    style: {
                        fontSize: "28px",
                        color: '#174574'
                    }
                },
                chart: {
                    id: "basic-bar"
                },
                xaxis: {
                    categories: getLastSevendays()
                }
            }
        })

    }
    render() {
        return (
            <>

                <div className="divBodyProfileProvider">
                    <MenuLeftProvider />
                    <div className="divRightProfileProvider">
                        <div className="divRightProfileProviderRelatorio">
                            <div className="servicesInProgress">
                                <p>Serviços ativos</p>
                                <br />
                                <div className="servicesInProgressOverflow">
                                    <ServicesList />
                                </div>
                            </div>
                            <div className="divRightProfileProviderTop">
                                <div className="divRightProfileProviderTopLeft">
                                    <div className="divRightProfileProviderTopLeftTOP">
                                        <div className="visitsYourProfile">
                                            <div className="visitsYourProfileTOP">
                                                <p>Visitas ao seu perfil</p>
                                                <img src={questionMark} alt="Informações sobre visitas ao seu perfil" />
                                            </div>
                                            <div className="visitsYourProfileBut">
                                                {/* <p id="indice">13%</p> */}
                                                <div className="indiceDiv2">
                                                    <p id="indice2">{geraVisitas()}</p>
                                                    <p>nessa semana</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="visitsYourProfile">
                                            <div className="visitsYourProfileTOP">
                                                <p>Sua nota média</p>
                                                <img src={questionMark} alt="Informações sobre visitas ao seu perfil" />
                                            </div>
                                            <div className="visitsYourProfileBut">
                                                {/* <p id="indice">+10</p> */}
                                                <div className="indiceDiv2">
                                                    <p id="indice2">{this.state.avg}</p>
                                                    <DynamicStars
                                                        rating={3}
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <br />
                                    {/* <div className="divRightProfileProviderTopLeftBut">
                                        <p>Sua nota média é maior que a dos outros Pintores da nossa plataforma (4.0 X 3.8)</p>
                                        <p className="indiceDiv3">+0.20</p>
                                    </div> */}
                                </div>
                                <div className="divRightProfileProviderTopRight">

                                </div>
                            </div>
                            <br />
                            <div className="divRightProfileProviderMed">
                                <Chart
                                    options={this.state.options}
                                    series={this.state.series}
                                    type="line"
                                    height={320}
                                />
                            </div>
                        </div>
                    </div>
                </div>
                <div id="endServiceDiv" className="endServiceDiv">
                    <div className="endServiceDivIn">
                        <p>Deseja finalizar este serviço ?</p>
                        {/* <input id="pin" type="text" /> */}
                        <div className="endServiceDivInButton">
                            <button onClick={finishService}>Finalizar serviço</button>
                            <button onClick={closeendServiceDiv}>Sair</button>
                        </div>
                    </div>
                </div>
            </>
        )
    }

}

const geraVisitas = () => {
    const { id_user } = JSON.parse(sessionStorage.getItem('user'))
    if (!sessionStorage.getItem(`visitas-${id_user}`)) {
        let visitas = Math.round((Math.random() * 20) + 5)
        sessionStorage.setItem(`visitas-${id_user}`, visitas)
        return visitas
    }
    return sessionStorage.getItem(`visitas-${id_user}`)

}


function finishService() {
    // var pin = document.getElementById("pin").value
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
    document.getElementById("endServiceDiv").style.display = "none"
}

export default relatorioProvider

