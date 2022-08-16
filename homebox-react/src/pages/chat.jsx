import "../assets/css/chat.css"

import CardChat from "../components/cardChat";


import iconBack from "../assets/img/arrowLeft.png"
import iconHomebox from "../assets/img/icon/logo-removebg-preview.png"
import searchIcon from "../assets/img/searchIconBlack.png"


function Chat() {
    return (
        <>
            <div className="body">
                <div className="divLeft">
                    <div className="divLogoChat">
                        <img className="iconBack" src={iconBack} alt="voltar" />
                        <img className="iconHomebox" src={iconHomebox} alt="logo homebox" />
                    </div>
                    <div className="convs">
                        <h5>
                            Conversas
                        </h5>
                    </div>
                    <div className="divSearchBar">
                        <div className="barSearch">
                            <input placeholder="Pesquisar..." type="text" />
                        </div>
                        <div className="iconSearch">
                            <img src={searchIcon} alt="Icone de pesquisar 'lupa'" />
                        </div>
                    </div>
                    <div className="divCardsChat">
                        <CardChat />                    
                    </div>
                </div>
                <div className="divRight"></div>
            </div>
        </>
    )
}

export default Chat