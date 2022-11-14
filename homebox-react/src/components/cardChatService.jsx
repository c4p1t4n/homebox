import api from "../api"

function cardChatService(props) {
    let id = props.id
    let service = props.service
    let adress = props.adress
    let date = props.date

    function closeBusinessFunction() {
        api.patch(`/schedulings/accomplish/${id}/${document.getElementById("serviceValue").value}`
        ).then((status) => {
            if (status === 201) {                  
                alert("Negocio Fechado!!!")
            }
        })
    }
    
    function openModalCloseBusinessDiv() {
        document.getElementById("modalCloseBusinessDiv").style.display = document.getElementById("modalCloseBusinessDiv").style.display === "flex" ? "none" : "flex"
    }    

    return (
        <>
            <div className="cardChatMsgLeft" key={props.index}>
                <p>{props.text}</p>
                <button onClick={openModalCloseBusinessDiv}>Fechar Negócio ?</button>
            </div>

            <div id="modalCloseBusinessDiv" className="modalCloseBusinessDiv">
                <div className="openModalCloseBusiness" action="">
                    <label htmlFor="Service">
                        <label htmlFor="categoryService">Serviço:</label><br></br>
                        <p>{service}</p>
                    </label>
                    <label htmlFor="Address">
                        <p>Endereço</p>
                        <p>{adress}</p>
                    </label>
                    <label htmlFor="ValueOfService">
                        <p>Valor do serviço</p>
                        <input required id="serviceValue" type="number" placeholder="Valor R$" />
                    </label>
                    <label htmlFor="dateOfService">
                        <p>Data do serviço</p>
                        <p>{date}</p>
                    </label>
                    <label htmlFor="" className="buttonsCloseBusiness">
                        <button onClick={closeBusinessFunction}>Fechar negócio?</button>
                        <button onClick={openModalCloseBusinessDiv}>Cancelar</button>
                    </label>
                </div>
            </div>

        </>
    )
}

export default cardChatService