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



function staff() {


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

    const labels = ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sabado'];

    const data2 = {
        labels,
        datasets: [
            {
                label: 'Agendamentos',
                data: [100, 200, 300, 400, 500, 600],
                borderColor: 'rgb(255, 99, 132)',
                backgroundColor: 'rgba(255, 99, 132, 0.5)',
            },
            {
                label: 'Serviços finalizados',
                data: [150, 250, 350, 450, 550, 650],
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
                data: [12, 19, 3],
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
                                            <h1>x:x</h1>
                                        </div>
                                    </div>
                                    <div className="proporcaoStaffDiv">
                                        <p>Avaliação média:</p>
                                        <p>Prestador</p>
                                        <div className="indiceProporcaoStaff">
                                            <p className="pIndiceVerdeStaff">+x%</p>
                                            <h1>x.x</h1>
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
                                            <h2>x:x</h2>
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

export default staff