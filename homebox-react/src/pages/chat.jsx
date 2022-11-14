import "../assets/css/chat.css"

import CardChat from "../components/cardChat";
import SendMsg from "../components/sendMsg";
import CardChatMsgLeft from "../components/cardChatMsgLeft";
import CardChatMsgRight from "../components/cardChatMsgRight";
import CardAudioRight from "../components/cardAudioRight";
import CardAudioLeft from "../components/cardAudioLeft";
import CardImgRight from "../components/cardImgRight";
import CardImgLeft from "../components/cardImgLeft";
import CardChatService from "../components/cardChatService";

import VLibras from "@djpfs/react-vlibras"
import api from "../api"
import { useEffect, useState, useRef } from "react"

import iconBack from "../assets/img/arrowLeft.png"
import closeBusiness from "../assets/img/closeBusiness.png"
import iconHomebox from "../assets/img/icon/logo-removebg-preview.png"
import searchIcon from "../assets/img/searchIconBlack.png"
import profileImg from "../assets/img/profileIcon.png"



function Chat() {
    const [chats, setChats] = useState([])
    useEffect(() => {
        api.get(`/chat/user/` + JSON.parse(sessionStorage.getItem("user")).id_user
        ).then(({ status, data }) => {
            if (status === 200) {
                setChats(data)
            }
        })
    }, [chats])

    const searchChat = () => {
        var name = document.getElementById("searchChat").value
        api.get(`/chat/user/` + JSON.parse(sessionStorage.getItem("user")).id_user + `/` + name
        ).then(({ status, data }) => {
            if (status === 200) {
                setChats(data)
            }
            else {
                setChats([])
            }
        }).catch(
            err => {
                setChats([])
            }
        )
    }

    const messagesEndRef = useRef(null)

    const scrollToBottom = () => {
        if (sessionStorage.getItem("sendMsg") === 'true') {
            messagesEndRef.current.scrollIntoView({ behavior: "auto" })
            setTimeout(function () {
                sessionStorage.setItem("sendMsg", false)
            }, 500);
        }
    }

    api.get(`/chat/msgs/` + JSON.parse(sessionStorage.getItem("chat")).idChat
    ).then(({ status, data }) => {
        if (status === 200) {
            sessionStorage.setItem("chatInfo", JSON.stringify({ ...data }))
        }
        scrollToBottom()
    })

    var list = []
    const info = JSON.parse(sessionStorage.getItem("chatInfo"));
    for (var i in info) {
        if (info[i].userId === JSON.parse(sessionStorage.getItem("user")).id_user) {
            if (info[i].message !== "") {
                if(info[i].message.includes("/!!/")){
                    var message_array = info[i].message.split('/!!/')
                    var message = ""
                    for(let i = 2; i <  message_array.length; i++){
                        message+= message_array[i]
                    }      
                    list.push(<CardChatMsgRight text={message} />)
                }
                else{
                    list.push(<CardChatMsgRight text={info[i].message} />)
                }
            }
            else {
                if (info[i].type === "image/png" || info[i].type === "image/jpeg") {
                    list.push(<CardImgRight url={info[i].path} />)
                }
                else if (info[i].type === "audio/wav") {
                    list.push(<CardAudioRight url={info[i].path} />)
                }
            }
        }
        else {
            if (info[i].message !== "") {
                if(info[i].message.includes("/!!/")){
                    var message_array = info[i].message.split('/!!/')
             
                    var message = ""
                    for(let i = 2; i < message_array.length; i++){
                        message+=message_array[i]
                    }      
                    list.push(<CardChatService text={message}
                                               id ={ message_array[1]}
                                               service ={ message_array[3]}
                                               adress ={ message_array[5]}
                                               date ={ message_array[7]}/>)
            
                }
                else{
                    list.push(<CardChatMsgLeft text={info[i].message} />)
                }
            }
            else {
                if (info[i].type === "image/png" || info[i].type === "image/jpeg") {
                    list.push(<CardImgLeft url={info[i].path} />)
                }
                else if (info[i].type === "audio/wav") {
                    list.push(<CardAudioLeft url={info[i].path} />)
                }
            }
        }
    }

    let services = []
    services.push(<option value="0">Selecione</option>)
    var serviceInfoObj = JSON.parse(sessionStorage.getItem("servicesInfo"));
    var serviceInfo = Object.keys(serviceInfoObj).map(key => [String(key), serviceInfoObj[key]]);
    for (let i = 0; i < serviceInfo.length; i++) {
        services.push(<option value={serviceInfo[i][1].idService+":"+serviceInfo[i][1].name}>
            {serviceInfo[i][1].name}
        </option>)
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
                            <input id="searchChat" placeholder="Pesquisar..." type="text"
                                onKeyPress={(e) => {
                                    if (e.key === "Enter") {
                                        searchChat()
                                    }
                                }} />
                        </div>
                        <div className="iconSearch">
                            <button><img src={searchIcon} alt="Icone de pesquisar 'lupa'" onClick={searchChat} /></button>
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
            <div className="closeBusinessDiv" id="closeBusinessDiv">
                <div id="closeBusinessDivImg" className="closeBusinessDivImg">
                    <img onClick={openCloseBusinessDivImgWithText} src={closeBusiness} alt="Fechar Negocio" />
                </div>
                <div id="closeBusinessDivImgWithText" className="closeBusinessDivImgWithText">
                    <p onClick={getListService}>Fechar negocio ?</p>
                    <img onClick={openCloseBusinessDivImgWithText} src={closeBusiness} alt="Fechar Negocio" />
                </div>
            </div>
            <div id="modalCloseBusinessDiv" className="modalCloseBusinessDiv">
                <div className="openModalCloseBusiness" action="">
                    <label htmlFor="Service">
                        <label htmlFor="categoryService">Serviço:</label><br></br>
                        <select required name="categories" id="select_categories">
                            {services}
                        </select>
                    </label>
                    <label htmlFor="Address">
                        <p>Endereço</p>
                        <input required id="adress" type="text" placeholder="Endereço" />
                    </label>
                    {/* <label htmlFor="ValueOfService">
                        <p>Valor do serviço</p>
                        <input required id="serviceValue" type="number" placeholder="Valor R$" />
                    </label> */}
                    <label htmlFor="dateOfService">
                        <p>Data do serviço</p>
                        <input required id="serviceDate" type="date" placeholder="Data do serviço" />
                    </label>
                    <label htmlFor="" className="buttonsCloseBusiness">
                        <button onClick={closeBusinessFunction}>Fechar negócio?</button>
                        <button onClick={openModalCloseBusinessDiv}>Cancelar</button>
                    </label>
                </div>
            </div>
        </>
    )
}

export default Chat

function closeBusinessFunction() {
    let user = JSON.parse(sessionStorage.getItem("user")).id_user;
    let chat = JSON.parse(sessionStorage.getItem("chat")).idChat;

    let drt = document.getElementById("select_categories").value
    
    let service = drt.substring(2)
    let desc = document.getElementById("adress").value
    let sd = document.getElementById("serviceDate").value

    api.post("/schedulings", {
        fkUser: user,
        fkService:  Array.from(drt)[0]
    }
    ).then(({ status, data }) => {
        if (status === 201) {
            api.post("/schedulings/accomplish/" + data.idScheduling, {
                description: desc,
                price: '0.0',
                serviceDate: sd
            }
            ).then(({ status, data }) => {
                if (status === 201) {                  
                    let msg = {
                        message: `/!!/${data.id}/!!/Olá, eu gotaria de fechar o serviço /!!/${service}/!!/, em /!!/${desc}/!!/ no dia /!!/${sd}/!!/`,
                        id: user
                    };
                    console.log(msg);
                
                    api.post('chat/msg/' + chat + '/' + user, msg)
                        .then((response) => {
                            console.log(response.status)
                        });

                    alert("Pedido de serviço enviado!!!")
                }
            })
        }

    })

    openModalCloseBusinessDiv()
}

function openModalCloseBusinessDiv() {
    document.getElementById("modalCloseBusinessDiv").style.display = document.getElementById("modalCloseBusinessDiv").style.display === "flex" ? "none" : "flex"
}
function openCloseBusinessDivImgWithText() {
    document.getElementById("closeBusinessDivImgWithText").style.display = document.getElementById("closeBusinessDivImgWithText").style.display === "flex" ? "none" : "flex"

    document.getElementById("closeBusinessDivImg").style.display = document.getElementById("closeBusinessDivImg").style.display === "none" ? "flex" : "none"
}

function getListService() {
    var idWorker = JSON.parse(sessionStorage.getItem("chatInfo"))
    api.get(`services/getServicesOfWorker/` + idWorker[0].userId)
        .then(({ status, data }) => {
            if (status === 200) {
                sessionStorage.setItem("servicesInfo", JSON.stringify({ ...data }))
                openModalCloseBusinessDiv()
            }
            else {
                const info = {

                }
                sessionStorage.setItem("servicesInfo", JSON.stringify({ ...info }))
            }
        })
}

const back = e => {
    window.history.back()
}
