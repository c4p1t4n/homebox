import "../assets/css/profileProvider.css"

import logoHomebox from "../assets/img/icon/logo-removebg-preview.png"
import logoffProfileProvider from "../assets/img/logoffProfileProvider.png"
import alterNameProvider from "../assets/img/alterNameProvider.png"
import deleteService from "../assets/img/deleteService.png"
import imgJose from "../assets/img/joseRicardoCustumer.png"


function profileProvider() {
    return (
        <>
            <div className="divBodyProfileProvider">
                <div className="divLeftProfileProvider">
                    <img src={logoHomebox} alt="logo homebox" className="logoProfileProvider" />
                    <p className="welcomeProvider">Seja bem vindo, XXXXXXX</p>
                    <a href="" className="tagAprofileProvider"><p>Meu Perfil</p></a>
                    <a href="" className="tagAprofileProvider"><p>Relatorios</p></a>
                    <a href="" className="tagAprofileProvider"><p>Meu Chat</p></a>
                    <div className="logoffProfileProvider">
                        <a href=""><img src={logoffProfileProvider} alt="logoff" /></a>
                    </div>
                </div>
                <div className="divRightProfileProvider">
                    <div className="divImgProfileProvider">
                        <div className="divNameProvider">
                            <p className="nameProvider">xxxxxxx</p>
                            <img src={alterNameProvider} alt="alterar o nome de exibição" className="alterNameProvider" />
                        </div>
                        <img className="imgPhotoProvider" src={imgJose} alt="imagem do prestador" />
                        <div className="ratingProvider">
                            <p>Div avaliação</p>
                        </div>
                    </div>
                    <div className="divAlterPhotoProvider">
                        <a href=""><p>Alterar foto</p></a>
                    </div>
                    <button>Adicionar um serviço</button>
                    <div className="divServicesProvider">
                        <details className="cardServiceProvider">
                            <summary className="infoService">
                                <div className="nameServiceProvider">
                                    <p>Serviço:</p>
                                    <p>xxxxxxx</p>
                                </div>
                                <div className="valueAvg">
                                    <p>Valor de Referência</p>
                                    <p>R$00,00</p>
                                </div>
                                <div className="divIconsService">
                                    <img src={alterNameProvider} alt="icone para editr serviço" className="editServiceProvider" />
                                    <img src={deleteService} alt="icone de deletar serviço" className="deleteService" />
                                </div>
                            </summary>
                            <div className="descriptionServiceProvider">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quam, eum. Adipisci, deserunt reprehenderit quaerat suscipit enim, delectus voluptatibus modi autem facilis dele</p>
                            </div>
                        </details>
                        <details className="cardServiceProvider">
                            <summary className="infoService">
                                <div className="nameServiceProvider">
                                    <p>Serviço:</p>
                                    <p>xxxxxxx</p>
                                </div>
                                <div className="valueAvg">
                                    <p>Valor de Referência</p>
                                    <p>R$00,00</p>
                                </div>
                                <div className="divIconsService">
                                    <img src={alterNameProvider} alt="icone para editr serviço" className="editServiceProvider" />
                                    <img src={deleteService} alt="icone de deletar serviço" className="deleteService" />
                                </div>
                            </summary>
                            <div className="descriptionServiceProvider">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quam, eum. Adipisci, deserunt reprehenderit quaerat suscipit enim, delectus voluptatibus modi autem facilis dele</p>
                            </div>
                        </details>
                        <details className="cardServiceProvider">
                            <summary className="infoService">
                                <div className="nameServiceProvider">
                                    <p>Serviço:</p>
                                    <p>xxxxxxx</p>
                                </div>
                                <div className="valueAvg">
                                    <p>Valor de Referência</p>
                                    <p>R$00,00</p>
                                </div>
                                <div className="divIconsService">
                                    <img src={alterNameProvider} alt="icone para editr serviço" className="editServiceProvider" />
                                    <img src={deleteService} alt="icone de deletar serviço" className="deleteService" />
                                </div>
                            </summary>
                            <div className="descriptionServiceProvider">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quam, eum. Adipisci, deserunt reprehenderit quaerat suscipit enim, delectus voluptatibus modi autem facilis dele</p>
                            </div>
                        </details>
                        <details className="cardServiceProvider">
                            <summary className="infoService">
                                <div className="nameServiceProvider">
                                    <p>Serviço:</p>
                                    <p>xxxxxxx</p>
                                </div>
                                <div className="valueAvg">
                                    <p>Valor de Referência</p>
                                    <p>R$00,00</p>
                                </div>
                                <div className="divIconsService">
                                    <img src={alterNameProvider} alt="icone para editr serviço" className="editServiceProvider" />
                                    <img src={deleteService} alt="icone de deletar serviço" className="deleteService" />
                                </div>
                            </summary>
                            <div className="descriptionServiceProvider">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quam, eum. Adipisci, deserunt reprehenderit quaerat suscipit enim, delectus voluptatibus modi autem facilis dele</p>
                            </div>
                        </details>
                    </div>
                </div>
            </div>
        </>
    )
}

export default profileProvider