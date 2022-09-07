import api from "../api"
import AudioReactRecorder, { RecordState } from 'audio-react-recorder'
import { v4 as uuidv4 } from 'uuid';
import React, { Component } from 'react'

import iconSendMsg from "../assets/img/iconSendMsg.png"
import iconSendMp3 from "../assets/img/iconSendMp3.png"
import iconStopMp3 from "../assets/img/iconSendMp3Red.png"
import paperclip from "../assets/img/paperclip.png"

class SendMsg extends Component {
    constructor(props) {
        super(props)

        this.state = {
            recordState: null
        };

        this.condition = true
    }

    start = () => {
        this.condition = false
        document.getElementById("imgMic").src = iconStopMp3
        document.getElementById("imgMic").style.height = "35px"
        this.setState({
            recordState: RecordState.START
        });
    };
    
    stop = () => {
        this.condition = true
        document.getElementById("imgMic").src = iconSendMp3
        document.getElementById("imgMic").style.height = "40px"
        this.setState({
            recordState: RecordState.STOP
        });
    };

    onStop = (audioData) => {
        let user = JSON.parse(sessionStorage.getItem("user")).id_user
        let chat = JSON.parse(sessionStorage.getItem("chat")).idChat;

        const url = '/upload/uploadFile/'+chat+"/"+user;

        const formData = new FormData();
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
                <input id="inputMsg" placeholder="Digite aqui ..." type="text" className="msg" />
                <button onClick={sendMsg}><img src={iconSendMsg} alt="Icone para enviar mensagem" className="sendMsg" /></button>
                <button onClick={getFile}>
                    <img src={paperclip} alt="Icone para anexar foto ou video"/>
                </button>
                <button onClick={this.condition ? this.start : this.stop}>
                    <img id="imgMic" src={iconSendMp3} alt="Icone para enviar audio" className="iconMic" />
                </button>
                <div className="recorder">
                    <AudioReactRecorder state={recordState} onStop={this.onStop} />
                    <input type="file" onChange={onChange} id="inputFile"/>
                </div>
            </>
        )
    }
}

export default SendMsg 

var state = {
    file: null,
}

const onChange = e => {
    let user = JSON.parse(sessionStorage.getItem("user")).id_user
    let chat = JSON.parse(sessionStorage.getItem("chat")).idChat

    state = ({ file: e.target.files[0] })
    console.log(state.file)

    const url = '/upload/uploadFile/'+chat+"/"+user;
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

const sendMsg = e =>{
    let chat = JSON.parse(sessionStorage.getItem("chat")).idChat;
    let user = JSON.parse(sessionStorage.getItem("user")).id_user;

    let msg = {
        message: document.getElementById("inputMsg").value,
        id: user
    };
    console.log(msg);
    document.getElementById("inputMsg").value = "";
    
    api.post('chat/msg/'+chat+'/'+user, msg)
        .then((response) => {
            console.log(response.status)
        });
}