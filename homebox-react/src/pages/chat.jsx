import "../assets/css/chat.css"

import CardChat from "../components/cardChat";
import CardChatMsgLeft from "../components/cardChatMsgLeft";
import CardChatMsgRight from "../components/cardChatMsgRight";
import CardAudioRight from "../components/cardAudioRight";
import CardAudioLeft from "../components/cardAudioLeft";
import SendMsg from "../components/sendMsg";

import VLibras from "@djpfs/react-vlibras"
import api from "../api"
import { useEffect, useState } from "react"

import iconBack from "../assets/img/arrowLeft.png"
import iconHomebox from "../assets/img/icon/logo-removebg-preview.png"
import searchIcon from "../assets/img/searchIconBlack.png"
import profileImg from "../assets/img/profileIcon.png"

function Chat(){
    const [chats, setChats] = useState([])

    useEffect(() => {
        api.get(`/chat/user/`+JSON.parse(sessionStorage.getItem("user")).id_user
        ).then(({ status, data }) => {
            if (status === 200) {
                console.log(data)
                setChats(data)
            }
        })
    }, [])

        return (
            <>
                <VLibras />
                <div className="bodyChat">
                    <div className="divLeft">
                        <div className="divLogoChat">
                            <button onClick={back} className="butIconBack"><img className="iconBack" src={iconBack} alt="voltar" /></button>
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
                        {chats.map((item) => (
                                <CardChat
                                    img={item.user?.picture ?? profileImg}
                                    name={item.user?.name}
                                    lastMessage={item?.lastMessage}
                                    lastMessageHour={item.lastMessageHour ?? "N/A"}
                                    key={item.id}
                                />
                            ))}   
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
                                <CardAudioLeft />
                                <CardAudioRight />
                            </div>
                        </div>
                        <div className="butChat">
                            <SendMsg />
                        </div>
                    </div>
                </div>
            </>
        )
}

export default Chat

const back = e => {
    window.history.back()
}