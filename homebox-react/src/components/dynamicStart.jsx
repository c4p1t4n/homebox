import { DynamicStar } from "react-dynamic-star";

function dynamicStars(props) {
    return (
        <>
            {props.rating ?
                <DynamicStar
                    width={30}
                    height={30}
                    rating={props.rating.toFixed(1) ?? 0}
                    emptyStarColor={"grey"} /> : ""}
        </>
    )
}


export default dynamicStars