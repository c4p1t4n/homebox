import "../assets/css/chat.css"

import CardChat from "../components/cardChat";
import SendMsg from "../components/sendMsg";
import CardChatMsgLeft from "../components/cardChatMsgLeft";
import CardChatMsgRight from "../components/cardChatMsgRight";
import CardAudioRight from "../components/cardAudioRight";
import CardAudioLeft from "../components/cardAudioLeft";
import CardImgRight from "../components/cardImgRight";
import CardImgLeft from "../components/cardImgLeft";

import VLibras from "@djpfs/react-vlibras"
import api from "../api"
import { useEffect, useState, useRef } from "react"

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
                setChats(data)
            }
        })
    }, [])

    const messagesEndRef = useRef(null)

    const scrollToBottom = () => { 
        if(sessionStorage.getItem("sendMsg")==='true'){
            messagesEndRef.current.scrollIntoView({ behavior: "auto" })
            setTimeout(function() {
                sessionStorage.setItem("sendMsg", false)
              }, 500);
              
        }
    }  
    
    api.get(`/chat/msgs/`+JSON.parse(sessionStorage.getItem("chat")).idChat
    ).then(({ status, data }) => {
        if (status === 200) {
            sessionStorage.setItem("chatInfo", JSON.stringify({...data}))
        }
            scrollToBottom()
    })

    var list = []
    const info = JSON.parse(sessionStorage.getItem("chatInfo"));
    for(var i in info){
        if(info[i].userId===JSON.parse(sessionStorage.getItem("user")).id_user){
            if(info[i].message !== ""){
                list.push(<CardChatMsgRight text={info[i].message}/>) 
            }
            else{
                if(info[i].type === "image/png" || info[i].type === "image/jpeg"){
                    list.push(<CardImgRight  url={info[i].path}/>)
                }
                else if(info[i].type === "audio/wav"){
                    list.push(<CardAudioRight url={info[i].path}/>)
                }
            }
        }
        else{
            if(info[i].message !== ""){
                list.push(<CardChatMsgLeft text={info[i].message}/>)
            }
            else{
                if(info[i].type === "image/png" || info[i].type === "image/jpeg"){
                    list.push(<CardImgLeft  url={info[i].path}/>)
                }
                else if(info[i].type === "audio/wav"){
                    list.push(<CardAudioLeft url={info[i].path}/>)
                }
            }
        }
    }



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
                                    lastMessage={item?.lastMessage.message}
                                    lastMessageHour={item.sendDate ?? "N/A"}
                                    index={item.chat.idChat}
                                />
                            ))}   
                        </div>
                    </div>
                    <div className="divRight">
                        <div className="topChat">
                            <div className="overflowDiv">
                            {list}
                            <div ref={messagesEndRef} />
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