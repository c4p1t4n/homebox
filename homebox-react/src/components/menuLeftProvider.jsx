import logoHomebox from "../assets/img/icon/logo-removebg-preview.png"
import logoffProfileProvider from "../assets/img/logoffProfileProvider.png"
import logo from "../assets/img/icon/logo-removebg-preview.png"


function menuLeftProvider() {
    if (!sessionStorage.getItem("user")) {
        window.location.href = "/login"
    }
    return (
        <>

            <div className="divLeftProfileProvider">
                <img src={logoHomebox} alt="logo homebox" className="logoProfileProvider" />
                <p id="nameProviderMenu" className="welcomeProvider">Seja bem vindo(a), {nome}</p>
                <a href="/profile/provider" className="tagAprofileProvider"><p>Meu Perfil</p></a>
                <a href="/profile/provider/relatorio" className="tagAprofileProvider"><p>Relatorios</p></a>
                <a href="/chat" className="tagAprofileProvider"><p>Meu Chat</p></a>
                <div className="logoffProfileProvider">
                    <a onClick={logOffSwitch}><img src={logoffProfileProvider} alt="logoff" /></a>
                </div>
            </div>
            <div id="logOffDivAbsolut">
                <img src={logo} alt="Logo homebox" />
                <p>Tem certeza que deseja sair ?</p>
                <div className="buttonsLogOff">
                    <button onClick={logOff} id="buttonYes">SIM</button>
                    <button onClick={logOffSwitch} id="buttonNo">NÃO</button>
                </div>
            </div>
        </>
    )
}

export default menuLeftProvider

const nome = JSON.parse(sessionStorage.getItem("user"))?.name?.split(" ")?.[0] ?? ""

const logOffSwitch = e => {
    e.preventDefault()
    const logoffOpenDiv = document.getElementById("logOffDivAbsolut")
    logoffOpenDiv.style.display =
        logoffOpenDiv.style.display === "flex" ? "none" : "flex"

}

const logOff = e => {
    sessionStorage.clear()
    window.location.href = "/login"
}