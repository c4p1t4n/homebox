import jose from "../assets/img/joseRicardoCustumer.png"


function cardChat() {
    return (
        <>
            <div className="cardsChat">
                <div className="cardChat">
                    <img src={jose} alt="Foto do Jose" />
                    <p className="nameP">Jóse</p>
                    <p>00h00</p>
                </div>
            </div>
        </>
    )
}

export default cardChat