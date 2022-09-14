/* eslint-disable react-hooks/rules-of-hooks */
import "../assets/css/profileProvider.css"
import logo from "../assets/img/icon/logo-removebg-preview.png"
import DynamicStars from "../components/dynamicStart"
import iconCloseBlue from "../assets/img/iconCloseBlue.png"
import CardServiceProviderServices from "../components/cardServiceProvderServices"
import alterNameProvider from "../assets/img/alterNameProvider.png"
import deleteService from "../assets/img/deleteService.png"
import imgJose from "../assets/img/joseRicardoCustumer.png"
import api from "../api"
import MenuLeftProvider from "../components/menuLeftProvider"
import { useEffect, useState } from "react"

function profileProvider() {


    function openCreateService() {
        document.getElementById("addServiceDiv").style.display = "flex"
    }

    function createService() {
        api.post(`/services`, {
            fkUser: JSON.parse(sessionStorage.getItem("user").id_user),

        })
            .then(({ status, data }) => {
                if (status === 200) {
                    setServices(data)
                }
            })
    }


    function closeDivCreateService() {
        document.getElementById("addServiceDiv").style.display = "none"
    }


    const [listServices2, setServices] = useState([])

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
                    <div className="divImgProfileProvider">
                        <div className="divNameProvider">
                            <p className="nameProvider">{JSON.parse(sessionStorage.getItem("user")).name}</p>
                            <img src={alterNameProvider} alt="alterar o nome de exibição" className="alterNameProvider" />
                        </div>
                        <img className="imgPhotoProvider" src={imgJose} alt="imagem do prestador" />
                        <div className="ratingProvider">
                            <p>3/5</p>
                            <p><DynamicStars
                                rating={3}
                            /></p>
                        </div>
                    </div>
                    <div className="divAlterPhotoProvider">
                        <a href=""><p>Alterar foto</p></a>
                    </div>
                    <button onClick={openCreateService}>Adicionar um serviço</button>
                    <div className="divServicesProvider">
                        {listServices2.map(item => (
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

                <form className="modalCreateService" action={createService}>
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
                        <select name="" id="">
                            <option value="">Selecione</option>
                            <option value="">Pintura</option>
                            <option value="">Encanamento</option>
                            <option value="">Elétrico</option>
                            <option value="">Montagem de imovéis</option>
                        </select>
                    </div>
                    <div className="divInputForm">
                        <label htmlFor="descriptionService">Descrição do serviço:</label>
                        <textarea required id="txtid" name="txtname" rows="5" cols="50" ></textarea>
                    </div>
                    <input id="submitForm" type="submit" value="Salvar Serviço" />
                </form>

            </div>
        </>
    )
}

export default profileProvider
