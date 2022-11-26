import "../assets/css/staff.css"
import logoHomebox from "../assets/img/icon/logo-removebg-preview.png"
import iconProfile from "../assets/img/profile.png"
function HeaderStaff() {
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
        </>
    )
}

function openLogoffStaffDiv() {

    const logoffOpenDiv = document.getElementById("logoffStaffDiv")

    logoffOpenDiv.style.display =
        logoffOpenDiv.style.display === "flex" ? "none" : "flex"
}


function logoffStaff() {
    sessionStorage.clear()
    window.location.href = "/login"
}
export default HeaderStaff