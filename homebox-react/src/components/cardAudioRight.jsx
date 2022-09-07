function cardAudioRight() {
    var url = "file:///C:\\Users\\eduardo.oliveira\\OneDrive - Yandeh\\Área de Trabalho\\projects\\faculdade\\homebox\\homebox-react\\src\\pages\\f8df290c-4031-438c-818c-b442aa32e5f6.mp3"
    return (
        <>
            <div className="cardChatAudioRight">
            <audio src={url} controls />
            </div>
        </>
    )
}

export default cardAudioRight