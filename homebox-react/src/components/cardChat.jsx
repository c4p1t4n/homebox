import api from "../api"

function CardChat(props) {
    return (
        <>
            <div className="cardsChat" key={props.index} onClick={() => setChat(props.index)}>
                <div className="cardChat">
                    <img src={props.img} alt="Foto do Jose" />
                    <div className="nameP">
                    <p>{props.name}</p>
                    <p className="lastMsg">{props.lastMessage}</p>
                    </div>
                    <p className="timestamp">{`${props.lastMessageHour.split('T')[0]}\n${props.lastMessageHour.split('T')[1]}`}</p>
                </div>
            </div>
        </>
    )
}

export default CardChat

function setChat(value){
    const data = {
        idChat: value
    }
    sessionStorage.setItem("chat", JSON.stringify({...data}))

    api.get(`/chat/msgs/`+JSON.parse(sessionStorage.getItem("chat")).idChat
    ).then(({ status, data }) => {
        if (status === 200) {
            sessionStorage.setItem("chatInfo", JSON.stringify({...data}))
        }
    })
}