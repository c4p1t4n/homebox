import logo from "../assets/img/icon/logo-removebg-preview.png"
import searchIcon from "../assets/img/searchIconBlack.png"
import profile from "../assets/img/profile.png"
import "../assets/css/header.css"
import {search} from "../assets/js/search"
import "../assets/css/headerProfileOpen.css"
import lineProfileOpen from "../assets/img/lineBlackProfileOpen.png"

function Header(props) {
    return (
        <header>
            <div className="container">
                <div className="header">
                    <a href="/">
                        <img className="logo" src={logo} alt="Homebox logo" />
                    </a>
                    <div className="divSearch">
                        <div className="searchBar">
                            <input
                                // value={props?.search ?? ""}
                                id="search-input"
                                type="text"
                                onKeyDown={keyStroke}
                                placeholder="Pesquise por serviço"
                            />
                        </div>
                        <div className="searchIcon">
                            <img
                                src={searchIcon}
                                alt="Icone de pesquisar 'lupa'"
                            />
                        </div>
                    </div>
                    <div className="profile" onClick={profileSwitch}>
                        <p>
                            {JSON.parse(sessionStorage.getItem("user"))?.name ??
                                ""}
                        </p>
                        <img src={profile} alt="" />
                    </div>
                    <div className="profileOpenDiv" id="profileMenu">
                        <a href="#">
                            <p>Perfil</p>
                        </a>
                        <a href="#">
                            <p>Chats</p>
                        </a>
                        <img src={lineProfileOpen} alt="" />
                        <p id="logoff" onClick={logOff}>
                            Logoff
                        </p>
                    </div>
                </div>
            </div>
        </header>
    )
}

const logOff = e => {
    sessionStorage.clear()
    window.location.href = "http://127.0.0.1:5500/website/index.html"
}

const profileSwitch = e => {
    e.preventDefault()
    const profile = document.querySelector(".profile")
    const profileOpenDiv = document.querySelector(".profileOpenDiv")

    profileOpenDiv.style.display =
        profileOpenDiv.style.display === "flex" ? "none" : "flex"
}

const keyStroke = e => {
    const input = document.querySelector("#search-input")
    const searchValue = input?.value
    if (!searchValue) {
        return
    }
    if (e.key === "Enter") {
        window.location.href = `/search?search=${searchValue}`
    }
    search(searchValue)
        .then(value => {
            console.log(value)
        })
        .catch(err => console.warn(err))
}
export default Header
