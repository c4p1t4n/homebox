import jose from "../assets/img/joseRicardoCustumer.png"


function cardChat() {
    return (
        <>
            <div className="cardsChat">
                <div className="cardChat">
                    <img src={jose} alt="Foto do Jose" />
                    <div className="nameP">
                    <p>José Roberto</p>
                    <p className="lastMsg">Lorem ipsum dolor sit amet consectetur adipisicing elit. Tenetur officia minima vel deserunt minus inventore illo aut, dolore beatae molestiae sapiente expedita veritatis, totam repellat maiores voluptas distinctio consectetur non.</p>
                    </div>
                    <p>00h00</p>
                </div>
            </div>
        </>
    )
}

export default cardChat