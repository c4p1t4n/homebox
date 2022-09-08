/* eslint-disable react-hooks/rules-of-hooks */
import CardOpenSearchClick from "../components/cardOpenSearchClick"
import DynamicStars from "../components/dynamicStart"
import joseRicardo from "../assets/img/joseRicardoCustumer.png"
import iconCloseBlue from "../assets/img/iconCloseBlue.png"
import api from "../api"
import { useEffect, useState } from "react"

function cardSearchOpenClick(props) {




    const [listServices, setServices] = useState([])

    useEffect(() => {
        api.get(`services/getServicesOfWorker/1`)
            .then(({ status, data }) => {
                if (status === 200) {
                    console.log(data)
                    setServices(data)
                }
            })
    },[])








    const close = e => {
        console.log("close")
        document.getElementById(props.name + props.id_user).innerHTML = ""
    };



    return (
        <>

            <div id="cardSearchOpenID" className="cardSearchOpen">
                <div className="cardSearchOpenDiv">
                    <div className="cardSearchOpenDivIconClose">
                        <img onClick={close} src={iconCloseBlue} alt="Icone de fechar" />
                    </div>
                    <div className="cardSearchOpenDivTopInfo">
                        <div className="cardSearchOpenDivTopInfoInside">
                            <div className="cardSearchOpenDivTopInfoInsideP">
                                <p>{props.name}</p>
                                <p>{props.category}</p>
                            </div>
                            <img className="imgProvider" src={props.img} alt="Imagem do prestador" />
                            <div className="cardSearchOpenDivTopInfoInsideAvarage">
                                <p>{props.rating.toFixed(1)}/5</p>
                                <DynamicStars
                                    rating={props.rating} />
                            </div>
                        </div>
                    </div>
                    <div className="cardSearchOpenDivServices">
                        {listServices.map(item=>(
                            <CardOpenSearchClick
                            nameService = {item.name}
                            referencePrice = {item.referencePrice}
                            description = {item.description}
                            />
                        ))}
                        
                    </div>
                    <button className="buttonInitChat">Iniciar Chat</button>
                </div>
            </div>
        </>
    )
}


export default cardSearchOpenClick
