import { useState } from "react"
import { DynamicStar } from "react-dynamic-star"
import CardSearchOpenClick from "../components/cardSearchOpenClick"

function CardSearch(props) {
    // const backgroundImg = {
    //     backgroundImage: props.img
    // }
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

            <div onClick={open} className="cardCustumer">
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
                        <div className="assessment2">
                            <p style={styleRating}>
                                Avaliação: {props.rating.toFixed(1)}/5
                            </p>
                        </div>
                        {props.rating ? (
                            <DynamicStar
                                width={35}
                                height={35}
                                rating={props.rating.toFixed(1)}
                                emptyStarColor={"grey"}
                            />
                        ) : (
                            ""
                        )}

                    </div>
                </div>
                <div className="distance">
                    <p>{props.dist.toFixed(2)} km de distância</p>
                </div>
            </div>
            <div id={props.name + props.id_user}>
                {cardList}
            </div>
        </>
    )
}


export default CardSearch


// const openDivProviderCard = e => {

//     e.preventDefault()
//     const openDivProviderCardOpen = document.getElementById("cardSearchOpenID")

//     openDivProviderCardOpen.style.display = "flex"
// }