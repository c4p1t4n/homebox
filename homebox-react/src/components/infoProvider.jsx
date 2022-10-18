import "../assets/css/profileProvider.css"
import DynamicStars from "../components/dynamicStart"
import alterNameProvider from "../assets/img/alterNameProvider.png"
import api from "../api"
import { useEffect, useState } from "react"

function ProfileProvtderComp() {
    const [star, setStar] = useState(0)
    useEffect(() => {
        api.get(`/users/avg-rating/` + JSON.parse(sessionStorage.getItem("user")).id_user
        ).then(({ status, data }) => {
            if (status === 200) {
                setStar(data)
            }
        })
    }, [star])

    useEffect(() => {
        api.get(`/users/` + JSON.parse(sessionStorage.getItem("user")).id_user
        ).then(({ status, data }) => {
            if (status === 200) {
                sessionStorage.setItem(
                    "user",
                    JSON.stringify({ ...data })
                )
            }
        })
    })

    return (
        <>
            <div className="divImgProfileProvider">
                <div className="divNameProvider">
                    <p className="nameProvider">{JSON.parse(sessionStorage.getItem("user")).name}</p>

                    <img onClick={changeName} src={alterNameProvider} alt="alterar o nome de exibição" className="alterNameProvider" />

                </div>
                <img className="imgPhotoProvider" src={JSON.parse(sessionStorage.getItem("user")).picture} alt="imagem do prestador" />
                <div className="ratingProvider">
                    <p>{star.toFixed(1)}/5</p>
                    <p><DynamicStars rating={star || 0.1} /></p>
                </div>
            </div>
        </>
    )
}

export default ProfileProvtderComp

const changeName = e => {
    document.getElementById("openDivAlterNameProvider").style.display = "flex"
}
