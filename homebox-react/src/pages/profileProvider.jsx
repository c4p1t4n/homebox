import "../assets/css/profileProvider.css"

import logoHomebox from "../assets/img/icon/logo-removebg-preview.png"
import logoffProfileProvider from "../assets/img/logoffProfileProvider.png"


function profileProvider() {
    return (
        <>
            <div className="divBodyProfileProvider">
                <div className="divLeftProfileProvider">
                    <img src={logoHomebox} alt="logo homebox" className="logoProfileProvider" />
                    <p className="welcomeProvider">Seja bem vindo, XXXXXXX</p>
                    <a href="" className="tagAprofileProvider"><p>Meu Perfil</p></a>
                    <a href="" className="tagAprofileProvider"><p>Relatorios</p></a>
                    <a href="" className="tagAprofileProvider"><p>Meu Chat</p></a>
                    <div className="logoffProfileProvider">
                        <a href=""><img src={logoffProfileProvider} alt="logoff" /></a>
                    </div>
                </div>
                <div className="divRightProfileProvider"></div>
            </div>
        </>
    )
}

export default profileProvider