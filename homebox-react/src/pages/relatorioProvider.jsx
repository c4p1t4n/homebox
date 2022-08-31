import MenuLeftProvider from "../components/menuLeftProvider"

import "../assets/css/providerDash.css"

import questionMark from "../assets/img/quesntion-mark.png"





function relatorioProvider() {
    return (
        <>
            <div className="divBodyProfileProvider">
                <MenuLeftProvider />
                <div className="divRightProfileProvider">
                    <div className="divRightProfileProviderRelatorio">
                        <div className="divRightProfileProviderTop">
                            <div className="divRightProfileProviderTopLeft">
                                <div className="divRightProfileProviderTopLeftTOP">
                                    <div className="visitsYourProfile">
                                        <div className="visitsYourProfileTOP">
                                            <p>Visitas ao seu perfil</p>
                                            <img src={questionMark} alt="Informações sobre visitas ao seu perfil" />
                                        </div>
                                        <div className="visitsYourProfileBut">
                                            <p id="indice">13%</p>
                                            <div className="indiceDiv2">
                                                <p id="indice2">24</p>
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
                                            <p id="indice">+10</p>
                                            <div className="indiceDiv2">
                                                <p id="indice2">4.3</p>
                                                <p>**estrelas**</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br />
                                <div className="divRightProfileProviderTopLeftBut">
                                    <p>Sua nota média é maior que a dos outros Pintoresda nossa plataforma (4.0 X 3.8)</p>
                                    <p className="indiceDiv3">+0.20</p>
                                </div>
                            </div>
                            <div className="divRightProfileProviderTopRight">

                            </div>
                        </div>
                        <br />
                        <div className="divRightProfileProviderMed">
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default relatorioProvider

