function cardAudioRight(props) {
    return (
        <>
            <div className="cardChatAudioRight">
            <audio src={props.url} controls />
            </div>
        </>
    )
}

export default cardAudioRight