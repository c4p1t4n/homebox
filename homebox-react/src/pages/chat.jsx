import "../assets/css/chat.css"

import CardChat from "../components/cardChat";
import CardChatMsgLeft from "../components/cardChatMsgLeft";
import CardChatMsgRight from "../components/cardChatMsgRight";


import VLibras from "@djpfs/react-vlibras"


import iconBack from "../assets/img/arrowLeft.png"
import videoIcon from "../assets/img/videoIcon.png"
import pictureIcon from "../assets/img/pictureIcon.png"
import iconSendMsg from "../assets/img/iconSendMsg.png"
import iconSendImg from "../assets/img/iconSendImg.png"
import iconSendMp3 from "../assets/img/iconSendMp3.png"
import iconHomebox from "../assets/img/icon/logo-removebg-preview.png"
import searchIcon from "../assets/img/searchIconBlack.png"


function Chat() {
    return (
        <>
            <VLibras />
            <div className="bodyChat">
                <div className="divLeft">
                    <div className="divLogoChat">
                        <button className="butIconBack"><img className="iconBack" src={iconBack} alt="voltar" /></button>
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
                            <button><img src={searchIcon} alt="Icone de pesquisar 'lupa'" /></button>
                        </div>
                    </div>
                    <div className="divCardsChat">
                        <CardChat />
                        <CardChat />
                        <CardChat />
                        <CardChat />
                        <CardChat />
                    </div>
                </div>
                <div className="divRight">
                    <div className="topChat">
                        <div className="overflowDiv">
                            <CardChatMsgLeft />
                            <CardChatMsgLeft />
                            <CardChatMsgLeft />
                            <CardChatMsgRight />
                            <CardChatMsgRight />
                            <CardChatMsgRight />
                        </div>
                    </div>
                    <div className="butChat">
                        <input placeholder="Digite aqui ..." type="text" className="msg" />
                        <button><img src={iconSendMsg} alt="Icone para enviar mensagem" className="sendMsg" /></button>
                        <button><img src={iconSendImg} alt="Icone para anexar foto ou video" className="anexImg" /></button>
                        <button><img src={iconSendMp3} alt="Icone para enviar audio" className="iconMic" /></button>

                        <div className="divOpenAnexo">
                            <img src={pictureIcon} alt="Enviar foto" />
                            <img src={videoIcon} alt="Enviar video" />
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Chat