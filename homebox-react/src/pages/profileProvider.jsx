/* eslint-disable react-hooks/rules-of-hooks */
import "../assets/css/profileProvider.css"
import iconCloseBlue from "../assets/img/iconCloseBlue.png"
import CardServiceProviderServices from "../components/cardServiceProvderServices"
import api from "../api"
import MenuLeftProvider from "../components/menuLeftProvider"
import ProfileProvtderComp from "../components/infoProvider"
import { useEffect, useState } from "react"
import { Upload } from "@aws-sdk/lib-storage";
import { S3Client} from "@aws-sdk/client-s3";
import { v4 as uuidv4 } from 'uuid';

function profileProvider() {
    function openCreateService() {
        document.getElementById("addServiceDiv").style.display = "flex"
    }

    function createService() {
        api.post(`/services`, {
            fkUser: JSON.parse(sessionStorage.getItem("user")).id_user,
            fkCategory: document.getElementById("select_categories").value,
            name: document.getElementById("nameServiceId").value,
            description: document.getElementById("txtid").value,
            referencePrice: document.getElementById("refValueId").value
        }).then((response) => {
            console.log(response.status)
        })

        document.getElementById("addServiceDiv").style.display = "none"
        alert("Serviço adicionado !!!")
    }

    function updateService() {
        api.patch(`/services/update/`+JSON.parse(sessionStorage.getItem("service")).idService, {
            fkUser: JSON.parse(sessionStorage.getItem("user")).id_user,
            fkCategory: document.getElementById("editServiceDivSelectCategories").value,
            name: document.getElementById("editServiceDivNameServiceId").value,
            description: document.getElementById("editServiceDivTxtid").value,
            referencePrice: document.getElementById("editServiceDivRefValueId").value
        }).then((response) => {
            console.log(response.status)
        })

      
        document.getElementById("editServiceDiv").style.display = "none"
        alert("Serviço Atualizado !!!")
    }

    function closeDivCreateService() {
        document.getElementById("addServiceDiv").style.display = "none"
    }


    const [listServices, setServices] = useState([])
    useEffect(() => {
        api.get(`services/getServicesOfWorker/${JSON.parse(sessionStorage.getItem("user")).id_user}`)
            .then(({ status, data }) => {
                if (status === 200) {
                    sessionStorage.setItem("servicesInfo", JSON.stringify({...data}))
                    setServices(data)
                }
            })
    }, [listServices])




    return (
        <>
            <div className="divBodyProfileProvider">
                <MenuLeftProvider />
                <div className="divRightProfileProvider">
                    <ProfileProvtderComp />
                    <div className="divAlterPhotoProvider">
                        <p className="alterPhoto" onClick={changePhoto}>Alterar foto</p >
                    </div>
                    <button id="botao1" onClick={openCreateService}>Adicionar um serviço</button>
                    <div className="divServicesProvider">
                        {listServices.map(item => (
                            <CardServiceProviderServices
                                id={item.idService}
                                nameService={item.name}
                                referencePrice={item.referencePrice}
                                description={item.description}
                            />
                        ))}
                    </div>

                </div>
            </div>
            <div id="addServiceDiv" className="addServiceDiv">

                <form className="modalCreateService">
                    <img onClick={closeDivCreateService} className="iconCloseService" src={iconCloseBlue} alt="icone de fechar" />
                    <div className="divInputForm">
                        <label htmlFor="nameService">Nome do serviço:</label>
                        <input required id="nameServiceId" type="text" placeholder="Exemplo: Troca de cano" />
                    </div>
                    <div className="divInputForm">
                        <label htmlFor="refValue">Valor de Referência:</label>
                        <input required id="refValueId" type="text" placeholder="Exemplo: 120" />
                    </div>
                    <div className="divInputForm">
                        <label htmlFor="categoryService">Categoria do serviço:</label>
                        <select name="categories" id="select_categories">
                            <option value="0">Selecione</option>
                            <option value="1">Encanamento</option>
                            <option value="2">Elétrico</option>
                            <option value="3">Montagem de imovéis</option>
                            <option value="4">Pintura</option>
                        </select>
                    </div>
                    <div className="divInputForm">
                        <label htmlFor="descriptionService">Descrição do serviço:</label>
                        <textarea required id="txtid" name="txtname" rows="5" cols="50" ></textarea>
                    </div>
                    <input id="submitForm" value="Salvar Serviço" onClick={createService} />
                </form>

            </div>
            <div id="openDivAlterNameProvider" className="openDivAlterNameProvider">
                <img onClick={closeChangeName} className="imgCloseAlterName" src={iconCloseBlue} alt="Icone de fechar" />
                <div className="divAlterName">
                    <h3>Alterar nome social</h3>
                    <input type="text" placeholder="Digite aqui" id="changeName"
                        onKeyPress={(e) => {
                            if(e.key === "Enter"){
                                updateName()
                        }}}/>
                    <button onClick={updateName}>Trocar</button>
                </div>
            </div>
            <div id="editServiceDiv" className="addServiceDiv">
                <form className="modalCreateService">
                    <img onClick={closeDivEditService} className="iconCloseService" src={iconCloseBlue} alt="icone de fechar" />
                    <div className="divInputForm">
                        <label htmlFor="nameService">Nome do serviço:</label>
                        <input required id="editServiceDivNameServiceId" type="text" placeholder="Exemplo: Troca de cano" />
                    </div>
                    <div className="divInputForm">
                        <label htmlFor="refValue">Valor de Referência:</label>
                        <input required id="editServiceDivRefValueId" type="text" placeholder="Exemplo: 120" />
                    </div>
                    <div className="divInputForm">
                        <label htmlFor="categoryService">Categoria do serviço:</label>
                        <select name="categories" id="editServiceDivSelectCategories">
                            <option value="0">Selecione</option>
                            <option value="1">Encanamento</option>
                            <option value="2">Elétrico</option>
                            <option value="3">Montagem de imovéis</option>
                            <option value="4">Pintura</option>
                        </select>
                    </div>
                    <div className="divInputForm">
                        <label htmlFor="descriptionService">Descrição do serviço:</label>
                        <textarea required id="editServiceDivTxtid" name="txtname" rows="5" cols="50" ></textarea>
                    </div>
                    <input id="submitForm" value="Editar Serviço" onClick={updateService} />
                </form>

            </div>
            <div className="recorder">
                <input type="file" onChange={onChange} id="inputFile"/>
            </div>
        </>
    )
}

function closeDivEditService() {
    document.getElementById("editServiceDiv").style.display = "none"
}

export default profileProvider

var state = {
    file: null,
}

const closeChangeName = e => {
    document.getElementById("openDivAlterNameProvider").style.display = "none"
}

const changePhoto = e => {
    document.getElementById("inputFile").click();
}

const onChange = e => {
    let user = JSON.parse(sessionStorage.getItem("user")).id_user

    state = ({ file: e.target.files[0] })
    console.log(state.file)

    let fileName = uuidv4()+".png";    
    const url = '/users/att/img/'+user+'/'+fileName;
    upload(state.file, fileName)
   
    setTimeout(() => { 
    api.patch(url)
        .then((response) => {
            console.log(response.status)
    })
    }, 690);
    
}

const updateName = () => {
    let user = JSON.parse(sessionStorage.getItem("user")).id_user

    var nameArray = document.getElementById("changeName").value.toLowerCase().split("")
    nameArray[0] = nameArray[0].toUpperCase()
    for (let i = 0; i < nameArray.length; i++) {
        if(nameArray[i] === ' '){
            nameArray[i+1] = nameArray[i+1].toUpperCase()
        }
    } 
    var newName = nameArray.toString().replace(/,/g,"")

    document.getElementById("changeName").value = ""
    const url = '/users/att/name/'+user+'/'+newName;

    api.patch(url)
    .then((response) => {
        console.log(response.status)
    })

    alert("Nome Alterado!!!!\n"+newName)
    document.getElementById("openDivAlterNameProvider").style.display = "none"
}

const upload = (file, name) => {
    const target = { Bucket:"homebox-files", Key:name, Body:file, ACL:'public-read'}
    const cred = { accessKeyId:'ASIA3XAZXQC6JOR4WL2T',  
                   secretAccessKey:'kY4qoCTb+5ou8hVPCbrCTpGY/6okZwNkzWm9IINy', 
                   sessionToken:'FwoGZXIvYXdzEKj//////////wEaDEm0mXBz1ogJbuawhyLPASjqh6v+vuIqADhqjfb1lTLKGzKDWKcRzmClL5sSGPLHQiVYHxYpxYTtBLtZTkgHDm9oz39MqAL1hj9ThIDZQDTGZ4Ii0CalpB3yB5RIGBxsRCqkVdBjIyktnGxtQ8iAyMx2NJ4mFGZaB1DoBGdcWDfszC/fOzuTeVeqvaW66udf6A25qVqU/5/URlU2FFOnE+w3ve1bxQRFG38/O/gztWSyoNDLbsMnKx6kOtmqfSlFp/gLMDtd71mzixO9yGBQae1t0iaw/3dDJbpBFzjFSijcw9iZBjItm/M4B6wDbkCZXnIMn2iq43NXLUK7xaKDYUR+CPcSlwkNfzTd3yePE0xEruxe'}

    try {
        const parallelUploads3 = new Upload({
          client: new S3Client({region:'us-east-1', credentials:cred}),
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