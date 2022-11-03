import api from "../api"

function cardOpenSearchClick(props) {

    function interest(){
        api.post(`/interestAccess/service/${JSON.parse(sessionStorage.getItem("user")).id_user}/${props.nameService}`)
    }

    return (
        <>
            <details className="cardSearchOpenDivServicesOpen" onClick={interest}>
                <summary className="summaryCardSearchOpen">
                    <div className="nameServiceSearchCardOpen">
                        <p>Serviço</p>
                        <p>: {props.nameService}</p>
                    </div>
                    <div className="valueAvarageSearchCardOPen">
                        <p>Valor de Referencia: R$</p>
                        <p>{props.referencePrice}</p>
                    </div>
                </summary>
                <div className="descriptionServiceOpenCard">{props.description}</div>
            </details>
        </>
    )
}

export default cardOpenSearchClick
