import logo from "../assets/img/icon/logo-removebg-preview.png"
import searchIcon from "../assets/img/searchIconBlack.png"
import profile from "../assets/img/profile.png"

function Header() {
    return (
        <header>
        <div className="container">
            <div className="header">
                <img className="logo" src={logo} alt="Logo Homebox"/>
                <div className="divSearch">
                    <div className="searchBar">
                        <input type="text" placeholder="Pesquise por serviço"/>
                    </div>
                    <div className="searchIcon">
                        <img src={searchIcon} alt="Icone de pesquisar 'lupa'"/>
                    </div>
                </div>
                <div className="profile">
                    <p>Wesley</p>
                    <img src={profile} alt="Imagem de perfil"/>
                </div>
            </div>
        </div>
    </header>
    );
  }
  
  export default Header;