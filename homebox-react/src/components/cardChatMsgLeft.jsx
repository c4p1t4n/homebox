function cardChatMsgLeft(props) {
    return (
        <>
            <div className="cardChatMsgLeft" key={props.index}>
                <p>{props.text}</p>
            </div>
        </>
    )
}

export default cardChatMsgLeft