import alterNameProvider from "../assets/img/alterNameProvider.png"
import deleteService from "../assets/img/deleteService.png"


function cardServiceProviderServices(props) {
    return (
        <details open className="cardServiceProvider">
            <summary className="infoService">
                <div className="nameServiceProvider">
                    <p>Serviço</p>
                    <p>: {props.nameService}</p>
                </div>
                <div className="valueAvg">
                    <p>Valor de Referência</p>
                    <p>: R${props.referencePrice}</p>
                </div>
                <div className="divIconsService">

                    <img src={alterNameProvider} alt="icone para editr serviço" className="editServiceProvider" />


                    <img src={deleteService} alt="icone de deletar serviço" className="deleteService" />

                </div>
            </summary>
            <div className="descriptionServiceProvider">
                <p>{props.description}</p>
            </div>
        </details>
    )
}


export default cardServiceProviderServices