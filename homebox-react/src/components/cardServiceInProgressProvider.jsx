import api from "../api"

function cardServiceInProgressProvider(props) {
    
    function openEndServiceDiv() {
    const data = {
        idService: props.id
    }
    sessionStorage.setItem("service", JSON.stringify({...data}))

        document.getElementById("endServiceDiv").style.display = "flex"
    }

    return (
        <>
            <details open className="cardServiceInProgressProvider">
                <summary>
                    <div className="nameServiceInProgressProvider">
                        <p>Serviço</p>
                        <p>: {props.service}</p>
                    </div>
                    <div className="nameServiceInProgressProvider">
                        <p>Preço</p>
                        <p>: R${props.price}</p>
                    </div>
                    <div className="endService">
                        <button onClick={openEndServiceDiv}>Finalizar Serviço</button>
                    </div>
                </summary>
                <div className="descriptionServiceInProgress">
                    <div className="clientServiceInProgress">
                        <p>Cliente</p>
                        <p>:{props.client}</p>
                    </div>
                    <div className="local">
                        <p>Local</p>
                        <p>: {props.local}</p>
                    </div>
                </div>
            </details>
        </>
    )
}

export default cardServiceInProgressProvider