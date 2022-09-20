import "../assets/css/staff.css"
import logoHomebox from "../assets/img/icon/logo-removebg-preview.png"
import iconProfile from "../assets/img/profile.png"


function staff() {

    function openLogoffStaffDiv(){
        
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
                            <div className="logoffStaffDiv">
                                <p>Logoff</p>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        </>
    )
}

export default staff