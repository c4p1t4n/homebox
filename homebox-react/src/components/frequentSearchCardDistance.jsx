import { DynamicStar } from 'react-dynamic-star';
import { useState } from "react"
import CardSearchOpenClick from "../components/cardSearchOpenClick"


function FrequentSearchCard(props) {
    const styleRating = {
        display: "inline"
    }

    const [cardList, setCardList] = useState([])
    const open = e => {
        document.getElementById(props.name + props.id_user).innerHTML = cardList
        setCardList([])
        console.log("open")
        setCardList(cardList.concat(<CardSearchOpenClick
            id_user={props.id_user}
            img={props.img}
            name={props.name}
            category={props.category}
            rating={props.rating ?? 0.0}
        />))
    }


    return (
        <>
            <div className={props.class} onClick={open}>
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
            <div id={props.name + props.id_user}>
                {cardList}
            </div>
        </>
    )
}

export default FrequentSearchCard
