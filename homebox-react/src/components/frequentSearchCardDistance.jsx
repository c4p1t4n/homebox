import { DynamicStar } from 'react-dynamic-star';

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
                <p style={styleRating}>Avaliação: {props.ratingStr.toFixed(1)}/5</p>
            </div>
            {props.rating ?
                <DynamicStar
                    width={35}
                    height={35}
                    rating={props.rating.toFixed(1)}
                    emptyStarColor={"grey"} /> : ""}
            <p>{props.dist.toFixed(2)} km de distância</p>
        </div>
    )
}

export default FrequentSearchCard
