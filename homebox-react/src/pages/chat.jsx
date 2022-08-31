import "../assets/css/chat.css"

import CardChat from "../components/cardChat";
import CardChatMsgLeft from "../components/cardChatMsgLeft";
import CardChatMsgRight from "../components/cardChatMsgRight";
import CardAudioRight from "../components/cardAudioRight";
import CardAudioLeft from "../components/cardAudioLeft";

import VLibras from "@djpfs/react-vlibras"
import api from "../api"
import AudioReactRecorder, { RecordState } from 'audio-react-recorder'
import { v4 as uuidv4 } from 'uuid';
import React, { Component } from 'react'

import iconBack from "../assets/img/arrowLeft.png"
import iconSendMsg from "../assets/img/iconSendMsg.png"
import paperclip from "../assets/img/paperclip.png"
import iconSendMp3 from "../assets/img/iconSendMp3.png"
import iconHomebox from "../assets/img/icon/logo-removebg-preview.png"
import searchIcon from "../assets/img/searchIconBlack.png"

class Chat extends Component {
    constructor(props) {
        super(props)

        this.state = {
            recordState: null
        };

        this.condition = true
    }

    start = () => {
        this.condition = false
        this.setState({
            recordState: RecordState.START
        });
    };

    stop = () => {
        this.condition = true
        this.setState({
            recordState: RecordState.STOP
        });
    };

    onStop = (audioData) => {
        const url = '/upload/uploadFile';
        const formData = new FormData();
        // formData.append("file", file, uuidv4()+".mp3");
        formData.append("file", audioData.blob, uuidv4() + ".mp3");
        const config = {
            headers: {
                'content-type': 'multipart/form-data'
            }
        }

        api.post(url, formData, config)
            .then((response) => {
                console.log(response.status)
            })
    };
    render() {
        const { recordState } = this.state;
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
                        {/* {searchResult
                            ? searchResult.map(item => (
                                <CardChat
                                    img={item.user?.picture ?? profileImg}
                                    name={item.user?.name}
                                    lastMessage={item?.lastMessage}
                                    lastMessageHour={item.lastMessageHour ?? "N/A"}
                                />
                            ))
                            : ""} */}
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
                            <input placeholder="Digite aqui ..." type="text" className="msg" />
                            <button><img src={iconSendMsg} alt="Icone para enviar mensagem" className="sendMsg" /></button>
                            <button onClick={getFile}>
                                <img src={paperclip} alt="Icone para anexar foto ou video"/>
                            </button>
                            <button onClick={this.condition ? this.start : this.stop}>
                                <img src={iconSendMp3} alt="Icone para enviar audio" className="iconMic" />
                            </button>
                        </div>
                    </div>
                </div>
                <div className="recorder">
                    <AudioReactRecorder state={recordState} onStop={this.onStop} />
                    <input type="file" onChange={onChange} id="inputFile"/>
                </div>
            </>
        )
    }
}

export default Chat

const back = e => {
    window.history.back()
}

var state = {
    file: null
}

const onChange = e => {
    state = ({ file: e.target.files[0] })
    console.log(state.file)
    const url = '/upload/uploadFile';
    const formData = new FormData();
    formData.append("file", state.file, uuidv4()+".png");
    const config = {
        headers: {
            'content-type': 'multipart/form-data'
        }
    }
    api.post(url, formData, config)
        .then((response) => {
            console.log(response.status)
        })

}

const getFile = e =>{
    document.getElementById("inputFile").click();
}