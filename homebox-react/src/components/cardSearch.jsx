import { DynamicStar } from 'react-dynamic-star';

function CardSearch(props) {
    // const backgroundImg = {
    //     backgroundImage: props.img
    // }
    const styleRating = {
        display: "inline"
    }

    return (
        <div className="cardCustumer">
            <img
                className="imgCustumer"
                src={props.img}
                alt="Imagem do prestador"
            />
            <div className="infoCustumer">
                <div className="nameCustumer">
                    <p>{props.name}</p>
                    <p>{props.category}</p>
                </div>
                <div className="assessment">
                    {props.ranking ? <DynamicStar width={35} rating={props.ranking} emptyStarColor={"grey"} /> : ""}
                    <div className="assessment2">
                        <p style={styleRating}>
                            Avaliação: {props.ranking}/5
                        </p>
                    </div>
                </div>
            </div>
            <div className="distance">
                <p>{props.dist.toFixed(2)} km de distância</p>
            </div>
        </div>
    )
}

export default CardSearch
