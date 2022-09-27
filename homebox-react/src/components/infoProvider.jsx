import "../assets/css/profileProvider.css"
import DynamicStars from "../components/dynamicStart"
import alterNameProvider from "../assets/img/alterNameProvider.png"
import api from "../api"

function ProfileProvtderComp() {
    // api.get(`/users/avg-rating/`+JSON.parse(sessionStorage.getItem("user")).id_user
    api.get(`/users/avg-rating/1`
    ).then(({ status, data }) => {
        if (status === 200) {
            console.log(data)
        }
    })

    return (
        <>
        <div className="divImgProfileProvider">
            <div className="divNameProvider">
                <p className="nameProvider">{JSON.parse(sessionStorage.getItem("user")).name}</p>
                <img src={alterNameProvider} alt="alterar o nome de exibição" className="alterNameProvider" />
            </div>
            <img className="imgPhotoProvider" src={JSON.parse(sessionStorage.getItem("user")).picture} alt="imagem do prestador" />
            <div className="ratingProvider">
                <p>3/5</p>
                <p><DynamicStars rating={3}/></p>
            </div>
        </div>  
        </>
    )
}

export default ProfileProvtderComp