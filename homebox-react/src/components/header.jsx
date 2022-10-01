import logo from "../assets/img/icon/logo-removebg-preview.png"
import searchIcon from "../assets/img/searchIconBlack.png"
import profile from "../assets/img/profile.png"
import "../assets/css/header.css"
import { search } from "../assets/js/search"
import "../assets/css/headerProfileOpen.css"
import lineProfileOpen from "../assets/img/lineBlackProfileOpen.png"
import api from "../api"
import { useEffect, useState } from "react"

function Header(props) {
    if (!sessionStorage.getItem("user")) {
        window.location.href = "/login"
    }

    const [searchResult, setSearchResult] = useState([])

    useEffect(() => {
        api.get("/search/last/search")
            .then(response => {
                console.log(response)
                if (response.status === 200) {
                    setSearchResult(response.data)
                }
            })
            .catch(err => console.warn(err))
    }, [])

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
                                list="searchs"
                                id="search-input"
                                type="text"
                                onKeyDown={keyStroke}
                                placeholder="Pesquise"
                            />
                            <datalist id="searchs">
                                {searchResult.map((item, key) => (
                                    <option value={item}>{item}</option>
                                ))}
                            </datalist>
                        </div>

                        <div onClick={ClickSearch} className="searchIcon">
                            <img
                                src={searchIcon}
                                alt="Icone de pesquisar 'lupa'"
                            />
                        </div>
                    </div>
                    <div className="profile" onClick={profileSwitch}>
                        <p>
                            {JSON.parse(
                                sessionStorage.getItem("user")
                            )?.name?.split(" ")?.[0] ?? ""}
                        </p>
                        <img src={profile} alt="" />
                    </div>
                    <div className="profileOpenDiv" id="profileMenu">
                        <a href="/profile/client">
                            <p>Perfil</p>
                        </a>
                        <a href="/chat">
                            <p>Chats</p>
                        </a>
                        <img src={lineProfileOpen} alt="" />
                        <p id="logoff" onClick={logOffSwitch}>
                            Logoff
                        </p>
                    </div>
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
        </header>
    )
}

const logOffSwitch = e => {
    e.preventDefault()
    const logoffOpenDiv = document.getElementById("logOffDivAbsolut")

    logoffOpenDiv.style.display =
        logoffOpenDiv.style.display === "flex" ? "none" : "flex"
}


const logOff = e => {
    sessionStorage.clear()
    window.location.href = "http://127.0.0.1:5500/website/index.html"
}

const profileSwitch = e => {
    e.preventDefault()
    const profileOpenDiv = document.querySelector(".profileOpenDiv")

    profileOpenDiv.style.display =
        profileOpenDiv.style.display === "flex" ? "none" : "flex"
}

function ClickSearch() {
    const input = document.querySelector("#search-input")
    const searchValue = input?.value
    const user = JSON.parse(sessionStorage.getItem("user"))
    if (user) {
        let { id_user: idUser } = user
        api.post("/search", { idUser, value: searchValue }).then(response => {
            if (response.status === 201) {
                console.log("SUCESSO")
            } else {
                console.log(response)
            }
        })
    }
    window.location.href = `/search?search=${searchValue}`
}

const keyStroke = e => {
    const input = document.querySelector("#search-input")
    const searchValue = input?.value
    if (!searchValue) {
        return
    }
    if (e.key === "Enter") {
        ClickSearch()
    }
    search(searchValue, JSON.parse(sessionStorage.getItem("user")).id_user)
        .then(value => {
            console.log(value)
        })
        .catch(err => { })
}
export default Header
