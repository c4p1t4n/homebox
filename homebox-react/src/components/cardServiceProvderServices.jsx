import alterNameProvider from "../assets/img/alterNameProvider.png"
import deleteService from "../assets/img/deleteService.png"


function cardServiceProviderServices() {
    return (
        <details className="cardServiceProvider">
            <summary className="infoService">
                <div className="nameServiceProvider">
                    <p>Serviço:</p>
                    <p>xxxxxxx</p>
                </div>
                <div className="valueAvg">
                    <p>Valor de Referência</p>
                    <p>R$00,00</p>
                </div>
                <div className="divIconsService">
                    <img src={alterNameProvider} alt="icone para editr serviço" className="editServiceProvider" />
                    <img src={deleteService} alt="icone de deletar serviço" className="deleteService" />
                </div>
            </summary>
            <div className="descriptionServiceProvider">
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quam, eum. Adipisci, deserunt reprehenderit quaerat suscipit enim, delectus voluptatibus modi autem facilis dele</p>
            </div>
        </details>
    )
}


export default cardServiceProviderServices