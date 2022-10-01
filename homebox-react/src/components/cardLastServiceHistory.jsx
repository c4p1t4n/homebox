function cardLastServiceHistory(props) {
    return (
        <>
            <div className="cardLastService">
                <div className="nameServiceHistory">
                    <p>Nome do Serviço</p>
                    <p>: xxxxxxxx</p>
                </div>
                <div className="priceServiceHistory">
                    <p>Preço pago</p>
                    <p>: R$00,00</p>
                </div>
                <div className="dateServiceHistory">
                    <p>Data do serviço</p>
                    <p>: 00/00/0000</p>
                </div>
                <div className="cardLastServiceButton">
                    <button onClick={openEndServiceDivClient}>Finalizar Serviço</button>
                </div>
            </div>
        </>
    )
}

export default cardLastServiceHistory

function openEndServiceDivClient() {
    document.getElementById("endServiceDivClient").style.display = "flex"
}