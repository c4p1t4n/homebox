import logo from "../assets/img/icon/logo-removebg-preview.png"
import searchIcon from "../assets/img/searchIconBlack.png"
import profile from "../assets/img/profile.png"
import "../assets/css/header.css"
import "../assets/css/headerProfileOpen.css"
import lineProfileOpen from "../assets/img/lineBlackProfileOpen.png"

function Header() {
    return (
        <header>
            <div class="container">
                <div class="header">
                    <a href="http://localhost:3000/"><img className="logo" src={logo} alt="Homebox logo" /></a>
                    <div class="divSearch">
                        <div class="searchBar">
                            <input type="text" placeholder="Pesquise por serviço" />
                        </div>
                        <div className="searchIcon">
                            <img src={searchIcon} alt="Icone de pesquisar 'lupa'" />
                        </div>
                    </div>
                    <div class="profile">
                        <p>Wesley</p>
                        <img src={profile} alt="" />
                    </div>
                    <div  className="profileOpenDiv">
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