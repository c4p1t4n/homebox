import logoHomebox from "../assets/img/icon/logo-removebg-preview.png"
import logoffProfileProvider from "../assets/img/logoffProfileProvider.png"


function menuLeftProvider() {
    return (
        <>

            <div className="divLeftProfileProvider">
                <img src={logoHomebox} alt="logo homebox" className="logoProfileProvider" />
                <p className="welcomeProvider">Seja bem vindo, XXXXXXX</p>
                <a href="" className="tagAprofileProvider"><p>Meu Perfil</p></a>
                <a href="" className="tagAprofileProvider"><p>Relatorios</p></a>
                <a href="" className="tagAprofileProvider"><p>Meu Chat</p></a>
                <div className="logoffProfileProvider">
                    <a onClick={logOffSwitch} href=""><img src={logoffProfileProvider} alt="logoff" /></a>
                </div>
            </div>
        </>
    )
}

export default menuLeftProvider



const logOffSwitch = e => {
    e.preventDefault()
    const logoffOpenDiv = document.getElementById("logOffDivAbsolut")

    logoffOpenDiv.style.display =
        logoffOpenDiv.style.display === "flex" ? "none" : "flex"
}

