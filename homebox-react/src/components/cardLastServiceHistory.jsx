function cardLastServiceHistory(props) {

    function openEndServiceDivClient() {
        const data = {
            idService: props.id
        }
        sessionStorage.setItem("service", JSON.stringify({...data}))

        document.getElementById("endServiceDivClient").style.display = "flex"
    }

    return (
        <>
            <div className="cardLastService">
                <div className="nameServiceHistory">
                    <p>Nome do Serviço</p>
                    <p>: {props.service}</p>
                </div>
                <div className="priceServiceHistory">
                    <p>Preço pago</p>
                    <p>: R$ {props.price}</p>
                </div>
                <div className="dateServiceHistory">
                    <p>Data do serviço</p>
                    <p>:{props.date}</p>
                </div>
                <div className="cardLastServiceButton">
                    {
                     props.type==='scheduled' ? <button onClick={openEndServiceDivClient}>Finalizar Serviço</button> : 'Finalizado'
                    }
                </div>
            </div>
        </>
    )
}

export default cardLastServiceHistory
