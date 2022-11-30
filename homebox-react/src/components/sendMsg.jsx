import api from "../api"
import ec2MetaData from "../ec2MetaData"
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

    render() {
        return (
            <>
                <input id="inputMsg" placeholder="Digite aqui ..." type="text" className="msg"
                    onKeyPress={(e) => {
                        if (e.key === "Enter") {
                            sendMsg()
                        }
                    }} />
                <button onClick={sendMsg}><img src={iconSendMsg} alt="Icone para enviar mensagem" className="sendMsg" /></button>
                <button onClick={getFile}>
                    <img src={paperclip} alt="Icone para anexar foto ou video" />
                </button>
                <div className="recorder">
                    <input type="file" onChange={onChange} id="inputFile" />
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

    let fileName = uuidv4() + ".png";

    const url = '/upload/uploadFile/' + chat + "/" + user;
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
    }, 3000);

}

const getFile = e => {
    document.getElementById("inputFile").click();
}

const sendMsg = e => {
    let chat = JSON.parse(sessionStorage.getItem("chat")).idChat;
    let user = JSON.parse(sessionStorage.getItem("user")).id_user;

    let msg = {
        message: document.getElementById("inputMsg").value,
        id: user
    };
    document.getElementById("inputMsg").value = "";

    api.post('chat/msg/' + chat + '/' + user, msg)
        .then((response) => {
            console.log(response.status)
        });
    document.getElementById(JSON.parse(sessionStorage.getItem("chat")).idChat).innerHTML = msg.message;
    sessionStorage.setItem("sendMsg", true)
}

const upload = (file, name) => {
    ec2MetaData
        .get('/')
        .then(({status, data: {AccessKeyId: accessKeyId, SecretAccessKey: secretAccessKey, Token: sessionToken}}) => {

            const target = { Bucket:"imagens-homebox", Key:name, Body:file, ACL:'public-read'}
            const cred = {
                accessKeyId,
                secretAccessKey,
                sessionToken
            }
            try {
                const parallelUploads3 = new Upload({
                    client: new S3Client({ region: 'us-east-1', credentials: cred }),
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
        })
}
