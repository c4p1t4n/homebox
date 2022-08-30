import MenuLeftProvider from "../components/menuLeftProvider"

import "../assets/css/providerDash.css"

import questionMark from "../assets/img/quesntion-mark.png"


function relatorioProvider() {
    return (
        <>
            <div className="divBodyProfileProvider">
                <MenuLeftProvider />
                <div className="divRightProfileProvider">
                    <div className="divTopDashProvider">
                        <div className="divLeftDashTopProvider">
                            <div className="divInsideDashTopLeft">
                                <div className="visitsYourProfile">
                                    <div className="divPTop">
                                        <p>Visitas ao seu perfil</p>
                                        <img src={questionMark} alt="informação sobre visitas ao seu perfil" />
                                    </div>
                                    <div className="divPbutt">
                                        <p id="indice">+13%</p>
                                        <p>24 <br />nessa semana</p>
                                    </div>
                                </div>
                                <div className="yourAvarage">
                                    <div className="divPTop">
                                        <p>Sua nota (média)</p>
                                        <img src={questionMark} alt="informação sobre sua média" />
                                    </div>
                                    <div className="divPbutt">
                                        <p id="indice">+0.10</p>
                                        <div className="avarageProviderDash">
                                            <p>4.2 <br />XXXXXX</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="divRightDashTopProvider">

                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default relatorioProvider