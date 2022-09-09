function cardAudioLeft(props) {
    return (
        <>
            <div className="cardChatAudioLeft">
            <audio src={props.url} controls />
            </div>
        </>
    )
}

export default cardAudioLeft