import "../assets/css/profileClientComp.css"

import profileIMG from "../assets/img/profileIcon.png"
import alterInfo from "../assets/img/img-alter-e-mail.png"

function ProfileClientComp() {
    return (
        <>
            <div className="profileClient">
                <div>
                    <h3>Seja bem-vindo(a), xxxxxx</h3>
                </div>
                <div className="divProfileClient">
                    <div className="divProfileImg">
                        <img className="imageClient" src={profileIMG} alt="foto cliente" />
                        <p>Alterar foto</p>
                    </div>
                    <div className="divUserInfo">
                        <div className="userInformations">
                            <h4>E-mail:</h4>
                            <p>xxxxxxx@xxxxxxxx.com</p>
                            <img src={alterInfo} alt="alterar e-mail" />
                        </div>
                        <div className="userInformations">
                            <h4>Contato:</h4>
                            <p>99999-9999</p>
                            <img src={alterInfo} alt="alterar telefone" />
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ProfileClientComp