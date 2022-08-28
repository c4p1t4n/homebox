import "../assets/css/profileProvider.css"

import alterNameProvider from "../assets/img/alterNameProvider.png"
import deleteService from "../assets/img/deleteService.png"
import imgJose from "../assets/img/joseRicardoCustumer.png"

import MenuLeftProvider from "../components/menuLeftProvider"

function profileProvider() {
    return (
        <>
            <div className="divBodyProfileProvider">
                <MenuLeftProvider />
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