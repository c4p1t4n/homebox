/* eslint-disable react-hooks/rules-of-hooks */
import "../assets/css/profileProvider.css"
import iconCloseBlue from "../assets/img/iconCloseBlue.png"
import CardServiceProviderServices from "../components/cardServiceProvderServices"
import api from "../api"
import MenuLeftProvider from "../components/menuLeftProvider"
import ProfileProvtderComp from "../components/infoProvider"
import { useEffect, useState } from "react"

function profileProvider() {
    function openCreateService() {
        document.getElementById("addServiceDiv").style.display = "flex"
    }

    function createService() {
        // api.post(`/services`, {
        //     fkUser: JSON.parse(sessionStorage.getItem("user").id_user),
        // })
        //     .then(({ status, data }) => {
        //         if (status === 200) {
        //             setServices(data)
        //         }
        //     })

        console.log(document.getElementsByName("categories"))
        var body = {
            fkUser: JSON.parse(sessionStorage.getItem("user").id_user),
            fkCategory: document.getElementsByName("categories").value,
            name: document.getElementsByName("valuenameServiceId").value,
            description: document.getElementsByName("txtid").value,
            referencePrice: document.getElementsByName("refValueId").value
        }

        console.log(body)
    }


    function closeDivCreateService() {
        document.getElementById("addServiceDiv").style.display = "none"
    }


    const [listServices, setServices] = useState([])
    useEffect(() => {
        api.get(`services/getServicesOfWorker/${JSON.parse(sessionStorage.getItem("user")).id_user}`)
            .then(({ status, data }) => {
                if (status === 200) {
                    setServices(data)
                }
            })
    }, [])




    return (
        <>
            <div className="divBodyProfileProvider">
                <MenuLeftProvider />
                <div className="divRightProfileProvider">
                    <ProfileProvtderComp />
                    <div className="divAlterPhotoProvider">
                        <button onClick={changePhoto}>
                        <p>Alterar foto</p >
                        </button>
                    </div>
                    <button id="botao1" onClick={openCreateService}>Adicionar um serviço</button>
                    <div className="divServicesProvider">
                        {listServices.map(item => (
                            <CardServiceProviderServices
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
                    <input id="submitForm" value="Salvar Serviço" onClick={createService}/>
                </form>

            </div>
        </>
    )
}

export default profileProvider


const changePhoto = e => {
    console.log("Mudando o nome")
 }