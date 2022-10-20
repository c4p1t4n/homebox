function cardOpenSearchClick(props) {

    return (
        <>
            <details open className="cardSearchOpenDivServicesOpen">
                <summary className="summaryCardSearchOpen">
                    <div className="nameServiceSearchCardOpen">
                        <p>Serviço</p>
                        <p>: {props.nameService}</p>
                    </div>
                    <div className="valueAvarageSearchCardOPen">
                        <p>Valor de Referencia: R$</p>
                        <p>{props.referencePrice}</p>
                    </div>
                    <div></div>
                </summary>
                <div className="descriptionServiceOpenCard">{props.description}</div>
            </details>
        </>
    )
}

export default cardOpenSearchClick