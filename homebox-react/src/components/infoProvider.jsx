import "../assets/css/profileProvider.css"
import DynamicStars from "../components/dynamicStart"
import alterNameProvider from "../assets/img/alterNameProvider.png"
import api from "../api"
import { useEffect, useState} from "react"

function ProfileProvtderComp() {
    const [star, setStar] = useState(0)
    useEffect(() => {
        api.get(`/users/avg-rating/`+JSON.parse(sessionStorage.getItem("user")).id_user
        ).then(({ status, data }) => {
            if (status === 200) {                
                setStar(data)   
                console.log(star)
            }
        })
    },[star])

    // var num   = star

    return (
        <>
        <div className="divImgProfileProvider">
            <div className="divNameProvider">
                <p className="nameProvider">{JSON.parse(sessionStorage.getItem("user")).name}</p>
                <button onClick={changeName}>
                    <img src={alterNameProvider} alt="alterar o nome de exibição" className="alterNameProvider" />
                </button>
            </div>
            <img className="imgPhotoProvider" src={JSON.parse(sessionStorage.getItem("user")).picture} alt="imagem do prestador" />
            <div className="ratingProvider">
                <p>{star.toFixed(1)}/5</p>
                <p><DynamicStars rating={star}/></p>
            </div>
        </div>  
        </>
    )
}

export default ProfileProvtderComp

const changeName = e => {
   console.log("Mudando o nome")
}
