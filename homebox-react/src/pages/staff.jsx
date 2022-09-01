import "../assets/css/staff.css"
import logoHomebox from "../assets/img/icon/logo-removebg-preview.png"
import iconProfile from "../assets/img/profile.png"


function staff() {
    return (
        <header className="headerStaff">
            <img className="logoHomeboxStaff" src={logoHomebox} alt="Logo homebox" />
            <h2>Administração</h2>
            <div className="profileStaff">
                <p>Seja bem vindo</p>
                <p>, XXXXXX</p>
                <img src={iconProfile} alt="Perfil Staff" />
            </div>
        </header>
    )
}

export default staff