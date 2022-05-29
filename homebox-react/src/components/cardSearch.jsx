import fourStars from "../assets/img/fourStars.png"

function CardSearch(props) {
    // const backgroundImg = {
    //     backgroundImage: props.img
    // }

    return (
        <div className="cardCustumer">
            <img
                className="imgCustumer"
                src={props.img}
                alt="Imagem do José Ricardo"
            />
            <div className="infoCustumer">
                <div className="nameCustumer">
                    <p>{props.name}</p>
                    <p>Pintor</p>
                </div>
                <div className="assessment">
                    <img className="imgStars" src={fourStars} alt="" />
                    <div className="assessment2">
                        <p>Avaliação:</p>
                        <p>4/5</p>
                    </div>
                </div>
            </div>
            <div className="distance">
                <p>{props.dist} km de distância</p>
            </div>
        </div>
    )
}

export default CardSearch
