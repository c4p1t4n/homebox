import "../assets/css/staff.css"
import logoHomebox from "../assets/img/icon/logo-removebg-preview.png"
import iconProfile from "../assets/img/profile.png"


function staff() {

    function openLogoffStaffDiv() {

        const logoffOpenDiv = document.getElementById("logoffStaffDiv")

        logoffOpenDiv.style.display =
            logoffOpenDiv.style.display === "flex" ? "none" : "flex"
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
                                <p>Logoff</p>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <main>
                <div className="containerStaff">
                    <div className="mainDiv">
                        <div className="leftDivStaff"></div>
                        <div className="rigthDivStaff">
                            <div className="divToprigthDivStaff">
                                <div className="serviceOfRegion">

                                </div>
                                <div className="proporcaoStaff">
                                    <div className="proporcaoStaffDiv"></div>
                                    <div className="proporcaoStaffDiv"></div>
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