import "../assets/css/staff.css"
import logoHomebox from "../assets/img/icon/logo-removebg-preview.png"
import iconProfile from "../assets/img/profile.png"
import {
    Chart as ChartJS,
    RadialLinearScale,
    ArcElement,
    Tooltip,
    Legend,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
} from 'chart.js';
import { PolarArea } from 'react-chartjs-2';
import { Line } from 'react-chartjs-2';
import React, { useState, useEffect } from 'react';
import api from "../api"


function Staff() {

    const [worker, setWorker] = useState(0)

    const [costumer, setCostumer] = useState(0)
    
    const [avaliacaoMedia,setAvaliacaoMedia] = useState(0)

    const [days,setDays] = useState([])

    const [services,setServices] = useState([])
    const [servicesDone,setServicesDone] = useState([])

    const [scheduling,setScheduling] = useState(0)
    const [chats,setChats] = useState(0)

    const [ceps,setCep] = useState([])
    const [countCeps,setCountCeps] = useState([])



    useEffect(() => {
        api.get(
            `/staff/count-ceps`
        ).then(({ status, data }) => {
            
            if (status === 200) {
                console.log(countCeps)
                setCountCeps(data)
            }
        })
    }, [])

    useEffect(() => {
        api.get(
            `/staff/ceps`
        ).then(({ status, data }) => {
            
            if (status === 200) {
                console.log(`data${data}`)
                setCep(data)
            }
        })
    }, [])









    useEffect(() => {
        api.get(
            `/staff/scheduling-chat`
        ).then(({ status, data }) => {
            
            if (status === 200) {
                console.log(data[0])
                setScheduling(data[0])
                setChats(data[1])
            }
        })
    }, [])


    useEffect(() => {
        api.get(
            `/staff/get-scheduling-last-seven-days`
        ).then(({ status, data }) => {
            
            if (status === 200) {
                setDays(data[0])
            }
        })
    }, [])


    useEffect(() => {
        api.get(
            `/staff/ratio-worker-customer`
        ).then(({ status, data }) => {
            
            if (status === 200) {
                setWorker(data[0])
                setCostumer(data[1])
            }
        })
    }, [])
    

    useEffect(() => {
        api.get(
            `/staff/get-count-last-seven-days`
        ).then(({ status, data }) => {
            
            if (status === 200) {
                setServicesDone(data[0])
                setServices(data[1])
            }
        })
    }, [])

    useEffect(() => {
        api.get(
            `staff/avg-rating-worker`
        ).then(({ status, data }) => {
            
            if (status === 200) {
                setAvaliacaoMedia(data)
            }
        })
    }, [])
    

    



    const options2 = {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
            // title: {
            //     display: true,
            //     text: 'Agendamentos e serviços finalizados',
            // },
        },
    };

    const labels = days;

    const data2 = {
        labels,
        datasets: [
            {
                label: 'Agendamentos',
                data: services,
                borderColor: 'rgb(255, 99, 132)',
                backgroundColor: 'rgba(255, 99, 132, 0.5)',
            },
            {
                label: 'Serviços finalizados',
                data: servicesDone,
                borderColor: 'rgb(53, 162, 235)',
                backgroundColor: 'rgba(53, 162, 235, 0.5)',
            },
        ],
    };






    ChartJS.register(RadialLinearScale, ArcElement, Tooltip, Legend, CategoryScale,
        LinearScale,
        PointElement,
        LineElement,
        Title);

    const data = {
        labels: ['Zona Central', 'Zona Sul', 'Zona Norte'],
        datasets: [
            {
                label: 'Serviços finalizados por região',
                data: countCeps,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.5)',
                    'rgba(54, 162, 235, 0.5)',
                    'rgba(255, 206, 86, 0.5)',
                ],
                borderWidth: 3,
            },
        ],
    };





    function openLogoffStaffDiv() {

        const logoffOpenDiv = document.getElementById("logoffStaffDiv")

        logoffOpenDiv.style.display =
            logoffOpenDiv.style.display === "flex" ? "none" : "flex"
    }


    function logoffStaff() {
        sessionStorage.clear()
        window.location.href = "/login"
    }

    return (
        <>
            <header>
                <div className="divHeader">
                    <div className="containerStaff">
                        <div className="headerIn">
                            <div className="logoHomeboxDiv">
                                <img src={logoHomebox} alt="Icone Homebox" />
                            </div>
                            <h4>Administração</h4>
                            <div onClick={openLogoffStaffDiv} className="nameStaffDiv">
                                <p>Seja bem vindo(a), {JSON.parse(sessionStorage.getItem("staff")).name.split(' ')[0]}</p>
                                <img src={iconProfile} alt="Icone de perfil" />
                            </div>
                            <div id="logoffStaffDiv" className="logoffStaffDiv">
                                <p onClick={logoffStaff} >Logoff</p>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <main>
                <div className="containerStaff">
                    <div className="mainDiv">
                        <div className="leftDivStaff">
                            <div className="chartLineStaff">
                                <p>Agendamentos e serviços finalizados</p>
                                <Line options={options2} data={data2} />
                            </div>
                            <div className="visitsDivStaff">
                                <p>Visitantes</p>
                                <div className="timeAvgDiv">
                                    <div className="visitsPropsStaff">
                                        <p>xxxxxxxx</p>
                                        <p>xxxxxxxxxxxxxxxxxx</p>
                                        <div className="visitsPropsStaffIndice">
                                            <p>-xs</p>
                                            <h2>x.x min</h2>
                                        </div>
                                    </div>
                                    <div className="visitsPropsStaff">
                                        <p>xxxxxx</p>
                                        <p>xxxxxxxxxxxx</p>
                                        <div className="visitsPropsStaffIndice">
                                            <p>-X%</p>
                                            <h2>x:x</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="rigthDivStaff">
                            <div className="divToprigthDivStaff">
                                <div className="serviceOfRegion">
                                    <p>Serviços finalizados por região</p>
                                    <PolarArea data={data} />
                                </div>
                                <div className="proporcaoStaff">
                                    <div className="proporcaoStaffDiv">
                                        <p>Proporção</p>
                                        <p>Prestador : Cliente</p>
                                        <div className="indiceProporcaoStaff">
                                            <p className="pIndiceVerdeStaff">+x%</p>
                                            <h1>{worker}:{costumer}</h1>
                                        </div>
                                    </div>
                                    <div className="proporcaoStaffDiv">
                                        <p>Avaliação média:</p>
                                        <p>Prestador</p>
                                        <div className="indiceProporcaoStaff">
                                            <p className="pIndiceVerdeStaff">+x%</p>
                                            <h1>{avaliacaoMedia.toFixed(2)}</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="divButrigthDivStaff">
                                <p>Leads</p>
                                <div className="timeAvgDiv">
                                    <div className="timeAvg">
                                        <p>Tempo médio</p>
                                        <p>Entre acesso e cadastro</p>
                                        <div className="indiceAvgTime">
                                            <p>-Xs</p>
                                            <h2>x.x min</h2>
                                        </div>
                                    </div>
                                    <div className="timeAvg">
                                        <p>Proporção</p>
                                        <p>Chats : Agendamentos</p>
                                        <div className="indiceAvgTime">
                                            <p>-X%</p>
                                            <h2>{chats}:{scheduling}</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </>
    )
}

export default Staff