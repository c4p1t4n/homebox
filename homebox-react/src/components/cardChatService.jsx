import api from "../api"

function cardChatService(props) {
    let service = props.service
    let adress = props.adress
    let date = props.date

    api.get(`/schedulings/accomplish/${props.id}`
    ).then(({data}) =>{
        if (data>0) {
            document.getElementById(`button${props.id}`).style.display = 'none' 
        }
    })

    function closeBusinessFunction() {
        api.patch(`/schedulings/accomplish/${props.id}/${document.getElementById(`serviceValue${props.id}`).value}`
        ).then(({status}) => {
            console.log(status)
            if (status === 201) {                  
                alert("Negocio Fechado!!!")
                openModalCloseBusinessDiv()
            }
        })
    }
    
    function openModalCloseBusinessDiv() {
        document.getElementById(`modalCloseBusinessDiv${props.id}`).style.display = document.getElementById(`modalCloseBusinessDiv${props.id}`).style.display === "flex" ? "none" : "flex"
    }    

    return (
        <>
            <div className="cardChatMsgLeft" key={props.index}>
                <p>{props.text}</p>
                <button id={`button${props.id}`} onClick={openModalCloseBusinessDiv}>Fechar Negócio ?</button>
            </div>

            <div id={`modalCloseBusinessDiv${props.id}`} className="modalCloseBusinessDiv">
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
                        <input required id={`serviceValue${props.id}`} type="number" placeholder="Valor R$" />
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