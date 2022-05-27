import logo from "../assets/img/icon/logo-removebg-preview.png"
import searchIcon from "../assets/img/searchIconBlack.png"
import profile from "../assets/img/profile.png"
import lineProfileOpen from "../assets/img/lineBlackProfileOpen.png"
import "../assets/css/headerProfileOpen.css"

function Header() {
    return (
        <header>
            <div className="container">
                <div className="header">
                <a href="http://localhost:3000/"><img  className="logo" src={logo} alt="Homebox logo"/></a>
                    <div className="divSearch">
                        <div className="searchBar">
                            <input type="text" placeholder="Pesquise por serviço" />
                        </div>
                        <div className="searchIcon">
                            <img src={searchIcon} alt="Icone de pesquisar 'lupa'" />
                        </div>
                    </div>
                    <div className="profile">
                        <p>Wesley</p>
                        <img src={profile} alt="Imagem de perfil" />
                    </div>
                    <div className="profileOpenDiv">
                        <a href=""><p>Perfil</p></a>
                        <a href=""><p>Chats</p></a>
                        <img src={lineProfileOpen} alt="" />
                        <a href=""><p id="logoff">Logoff</p></a>
                    </div>
                </div>
            </div>
        </header>
    );
}

export default Header;