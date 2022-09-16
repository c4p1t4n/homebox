function cardServiceInProgressProvider(props) {
    return (
        <>
            <details open className="cardServiceInProgressProvider">
                <summary>
                    <div className="nameServiceInProgressProvider">
                        <p>Serviço</p>
                        <p>: xxxxxxx</p>
                    </div>
                    <div className="nameServiceInProgressProvider">
                        <p>Preço</p>
                        <p>: R$00,00</p>
                    </div>
                    <div className="endService">
                        <button>Finalizar Serviço</button>
                    </div>
                </summary>
                <div className="descriptionServiceInProgress">
                    <div className="clientServiceInProgress">
                        <p>Cliente</p>
                        <p>: xxxxxxx</p>
                    </div>
                    <div className="local">
                        <p>Local</p>
                        <p>: xxxxxxxxxx</p>
                    </div>
                </div>
            </details>
        </>
    )
}

export default cardServiceInProgressProvider