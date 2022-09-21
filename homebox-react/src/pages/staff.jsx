import "../assets/css/staff.css"
import logoHomebox from "../assets/img/icon/logo-removebg-preview.png"
import iconProfile from "../assets/img/profile.png"
import {
    Chart as ChartJS,
    RadialLinearScale,
    ArcElement,
    Tooltip,
    Legend,
} from 'chart.js';
import { PolarArea } from 'react-chartjs-2';



function staff() {


    ChartJS.register(RadialLinearScale, ArcElement, Tooltip, Legend);

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
                                <p>Seja bem vindo(a) , {JSON.parse(sessionStorage.getItem("user")).name}</p>
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
                            <div></div>
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
                        </div>
                    </div>
                </div>
            </main>
        </>
    )
}

export default staff