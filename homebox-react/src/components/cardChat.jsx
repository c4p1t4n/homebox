function cardChat(props) {
    return (
        <>
            <div className="cardsChat" key={props.index}>
                <div className="cardChat">
                    <img src={props.img} alt="Foto do Jose" />
                    <div className="nameP">
                    <p>{props.name}</p>
                    <p className="lastMsg">{props.lastMessage}</p>
                    </div>
                    <p>{props.lastMessageHour}</p>
                </div>
            </div>
        </>
    )
}

export default cardChat