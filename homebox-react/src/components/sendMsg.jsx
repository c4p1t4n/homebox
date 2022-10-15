import api from "../api"
import AudioReactRecorder, { RecordState } from 'audio-react-recorder'
import { v4 as uuidv4 } from 'uuid';
import React, { Component } from 'react'

import { Upload } from "@aws-sdk/lib-storage";
import { S3Client, S3 } from "@aws-sdk/client-s3";

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
        let fileName = uuidv4() + ".mp3"

        const formData = new FormData();
        formData.append("file", audioData.blob, fileName);
        const config = {
            headers: {
                'content-type': 'multipart/form-data'
            }
        }
        upload(audioData.blob, fileName)

        document.getElementById(JSON.parse(sessionStorage.getItem("chat")).idChat).innerHTML = "Áudio";

        setTimeout(() => {
            api.post(url, formData, config)
            .then((response) => {
                console.log(response.status)
            })
            sessionStorage.setItem("sendMsg", true)
        }, 620);
    };

    render() {
        const { recordState } = this.state;

        return (
            <>
                <input id="inputMsg" placeholder="Digite aqui ..." type="text" className="msg"
                onKeyPress={(e) => {
                    if(e.key === "Enter"){
                        sendMsg()
                    }}}/>
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

    let fileName = uuidv4()+".png";

    const url = '/upload/uploadFile/'+chat+"/"+user;
    const formData = new FormData();
    formData.append("file", state.file, fileName);
    const config = {
        headers: {
            'content-type': 'multipart/form-data'
        }
    }

    upload(state.file, fileName)


    document.getElementById(JSON.parse(sessionStorage.getItem("chat")).idChat).innerHTML = "Imagem";
    setTimeout(() => {
        api.post(url, formData, config)
            .then((response) => {
                console.log(response.status)
            })
            sessionStorage.setItem("sendMsg", true)
    }, 620);

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
    document.getElementById(JSON.parse(sessionStorage.getItem("chat")).idChat).innerHTML = msg.message;
    sessionStorage.setItem("sendMsg", true)
}

const upload = (file, name) => {
    const target = { Bucket:"homebox-files", Key:name, Body:file, ACL:'public-read'}
    // const cred = { accessKeyId:'ASIA3XAZXQC6FNJ7AGZ5',
    //                secretAccessKey:'+FbT1bbcbTcK/1N+H61ck7Wz6C67JtZ0aCiIDRu+',
    //                sessionToken:'FwoGZXIvYXdzEOz//////////wEaDJxTp31VUR/AwCDh4iLPAQ0UeDeV/7tc3HyIGoOwrMyv9ETganyJiZvizepdUjgqwuWgbVafHqgrzJDf9AoHrgyCgD+xYrNU8tQmvzKKZlXorCw8KPZgbH5eTCgTvlq6ht9Fw3aZFneTMUYGm2d+Ku1Uh+XqaUf8tNemKfGJIyovmXxtOcnrLDAIEkFSjO11/5WxIOrJOiqR105PDb8JvZZ0toaM7OWEXuCNOfVqQpfeT7e/q+xq0UWFlkMQDwiSbKlmXSat7gmUuA0p6ieSshpU8vMrIYtoM53ivxF5WCijla+ZBjIt38x9f1BweKL2IlRxzsKMGXL8ENio2jYlK6kkm3qwG53IC/yb+kVvOj9fFlDd'}

    try {
        const parallelUploads3 = new Upload({
          client: new S3Client({region:'us-east-1'}),
          params: target,
          leavePartsOnError: false,
        });

        parallelUploads3.on("httpUploadProgress", (progress) => {
          console.log(progress);
        });

        parallelUploads3.done();
      } catch (e) {
        console.log(e);
      }
}
