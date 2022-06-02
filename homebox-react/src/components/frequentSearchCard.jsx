function FrequentSearchCard(props) {
    return (
        <div className={props.class}>
            <p>{props.name}</p>
            <img className="imgCustumer" src={props.img} alt="Foto de perfil" />
            <p>{props.category}</p>
            <div className="avaliacao">
                <p>Avaliação: </p>
                <p>{props.ranking}/5</p>
            </div>
            <img
                className="stars"
                src={props.starImg}
                alt="Estrelas que indicam a avaliação do prestador"
            />
        </div>
    )
}

export default FrequentSearchCard