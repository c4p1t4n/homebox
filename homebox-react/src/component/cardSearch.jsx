

function CardSearch(props) {
  return (
    <div className="cardCustumer">
        <img className="imgCustumer" src={props.img} alt="Imagem do José Ricardo"/>
        <div className="infoCustumer">
            <div className="nameCustumer">
                <p>{props.name}</p>
                <p>{props.category}</p>
            </div>
            <div className="assessment">
                <img className="imgStars" src="./assets/img/fourStars.png" alt=""/>
                <div className="assessment2">
                    <p>Avaliação:</p>
                    <p>{props.aval}/5</p>
                </div>
            </div>
        </div>
        <div className="distance">
            <p>{props.dist} km de distância</p>
        </div>
    </div>
  );
}

export default CardSearch;
