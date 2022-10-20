import "../assets/css/profileClientComp.css"

import iconCloseBlue from "../assets/img/iconCloseBlue.png"
import alterInfo from "../assets/img/img-alter-e-mail.png"
import api from "../api"
import { v4 as uuidv4 } from 'uuid';
import { Upload } from "@aws-sdk/lib-storage";
import { S3Client } from "@aws-sdk/client-s3";

function ProfileClientComp() {
    api.get(`/users/` + JSON.parse(sessionStorage.getItem("user")).id_user
    ).then(({ status, data }) => {
        if (status === 200) {
            sessionStorage.setItem(
                "user",
                JSON.stringify({ ...data })
            )
        }
    })

    return (
        <>
            <div className="profileClient">
                <div>
                    <h3>Seja bem-vindo(a), {JSON.parse(sessionStorage.getItem("user")).name}</h3>
                </div>
                <div className="divProfileClient">
                    <div className="divProfileImg">
                        <img className="imageClient" src={JSON.parse(sessionStorage.getItem("user")).picture} alt="foto cliente" />
                        <p className="alterPhoto" onClick={changePhoto}>Alterar foto</p>
                    </div>
                    <div className="divUserInfo">
                        <div className="userInformations">
                            <h4>E-mail</h4>
                            <p>: {JSON.parse(sessionStorage.getItem("user")).email}</p>
                            <img onClick={changeEmail} src={alterInfo} alt="alterar e-mail" />
                        </div>
                    </div>
                </div>
            </div>
            <div className="recorder">
                <input type="file" onChange={onChange} id="inputFile" />
            </div>
            <div id="openDivAlterNameProvider" className="openDivAlterNameProvider">
                <img onClick={closeChangeName} className="imgCloseAlterName" src={iconCloseBlue} alt="Icone de fechar" />
                <div className="divAlterName">
                    <h3>Alterar o email</h3>
                    <input type="text" placeholder="Digite aqui" id="changeEmail"
                        onKeyPress={(e) => {
                            if (e.key === "Enter") {
                                updateEmail()
                            }
                        }} />
                    <button onClick={updateEmail}>Trocar</button>
                </div>
            </div>
        </>
    )
}

export default ProfileClientComp

const changeEmail = e => {
    document.getElementById("openDivAlterNameProvider").style.display = "flex"
}

const changePhoto = e => {
    document.getElementById("inputFile").click();
}

const closeChangeName = e => {
    document.getElementById("openDivAlterNameProvider").style.display = "none"
}

var state = {
    file: null,
}

const onChange = e => {
    let user = JSON.parse(sessionStorage.getItem("user")).id_user

    state = ({ file: e.target.files[0] })
    console.log(state.file)

    let fileName = uuidv4() + ".png";
    const url = '/users/att/img/' + user + '/' + fileName;

    setTimeout(() => {
        api.patch(url)
            .then((response) => {
                console.log(response.status)
            })
    }, 690);

    upload(state.file, fileName)
}

const upload = (file, name) => {
    const target = { Bucket: "homebox-files", Key: name, Body: file, ACL: 'public-read' }
    const cred = {
        accessKeyId: 'ASIA3XAZXQC6KN2WGQC2',
        secretAccessKey: 'M7YBBXQHDwYiSUu9E0PIwbI1Px+YMNuPEFj+XwCY',
        sessionToken: 'FwoGZXIvYXdzEJ7//////////wEaDAESbkwcAgBSabH+0yLPAblKf5Pka8Ny0oDUBqeulnhv8r20W8GOFK1bglUFN+HeQRDb2q876YOK7b6fEhhI7cleeJ8aewPTSe/BL5xSpCKCIuQdvQpkmazWsJiqkba5xMdo7heT5cOHoKbXWq96mpn7Lmx5EABrmOlpdoHgW01yMXXliYY6o3Rmz3MfUktxNOnI4b6NlBYyTKMepFeO+0IgLEuT+BQjDr4Bpfinpg5Bu8/93lbWKRAKDvEJsOD3VgeJRS/vTTqwG871x3POTj5jPs6sEvRpzBcdBSr0tijQ38aaBjItoF8wioZ47qmGSHYXFXLJmY2wC22iF/qoNGbqQhHAxxKAInGxoa8h18gK/eH1'
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
}

const updateEmail = () => {
    let user = JSON.parse(sessionStorage.getItem("user")).id_user

    var newEmail = document.getElementById("changeEmail").value
    if (newEmail.indexOf('@') > -1 && newEmail.indexOf('.com') > -1) {
        document.getElementById("changeEmail").value = ""
        const url = '/users/att/email/' + user + '/' + newEmail;

        api.patch(url)
            .then((response) => {
                console.log(response.status)
            })

        alert("Email Alterado!!!!\n" + newEmail)
        document.getElementById("openDivAlterNameProvider").style.display = "none"
    }


}