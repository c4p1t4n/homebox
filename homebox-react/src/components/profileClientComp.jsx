import "../assets/css/profileClientComp.css"

import profileIMG from "../assets/img/profileIcon.png"
import alterInfo from "../assets/img/img-alter-e-mail.png"

function ProfileClientComp(props) {
    return (
        <>
            <div className="profileClient">
                <div>
                    <h3>Seja bem-vindo(a), {props.nome}</h3>
                </div>
                <div className="divProfileClient">
                    <div className="divProfileImg">
                        <img className="imageClient" src={profileIMG} alt="foto cliente" />
                        <p>Alterar foto</p>
                    </div>
                    <div className="divUserInfo">
                        <div className="userInformations">
                            <h4>E-mail</h4>
                            <p>: {props.email}</p>
                            <img src={alterInfo} alt="alterar e-mail" />
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ProfileClientComp