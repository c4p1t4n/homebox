function cardAudioLeft() {
    var url = ""
    return (
        <>
            <div className="cardChatMsgLeft">
            <audio src={url} controls />
            </div>
        </>
    )
}

export default cardAudioLeft