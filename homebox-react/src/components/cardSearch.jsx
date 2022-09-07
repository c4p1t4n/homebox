import { DynamicStar } from "react-dynamic-star"
import CardSearchOpenClick from "../components/cardSearchOpenClick"

function CardSearch(props) {
    // const backgroundImg = {
    //     backgroundImage: props.img
    // }
    const styleRating = {
        display: "inline"
    }

    return (
        <div onClick={openCardSearch} className="cardCustumer">
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
            <CardSearchOpenClick
              /*   img={item.user?.picture ?? profileImg}
                name={item.user?.name}
                category={item?.category}
                rating={item.rating ?? "N/A"} */
            />
        </div>
    )
}

export default CardSearch


function openCardSearch() {
    document.getElementById("cardSearchOpenID").style.display = "flex"
}