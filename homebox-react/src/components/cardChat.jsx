function cardChat(props) {
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

export default cardChat

function setChat(value){
    console.log(value)
    const data = {
        idChat: value
    }
    console.log(data)
    sessionStorage.setItem("chat", JSON.stringify({...data}))
}