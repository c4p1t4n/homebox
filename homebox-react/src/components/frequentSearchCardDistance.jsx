function FrequentSearchCard(props) {
    const styleRating = {
        display: "inline"
    }

    return (
        <div className={props.class}>
            <p>{props.name}</p>
            <img className="imgCustumer" src={props.img} alt="Foto de perfil" />
            <p>{props.category}</p>
            <div className="avaliacao">
                <p style={styleRating}>Avaliação: {props.ranking}/5</p>
            </div>
            <img
                className="stars"
                src={props.starImg}
                alt="Estrelas que indicam a avaliação do prestador"
            />
            <p>{props.dist.toFixed(2)} km de distância</p>
        </div>
    )
}

export default FrequentSearchCard
