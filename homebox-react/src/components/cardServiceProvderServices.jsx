import alterNameProvider from "../assets/img/alterNameProvider.png"
import deleteService from "../assets/img/deleteService.png"


function cardServiceProviderServices(props) {

    function openEditService() {
        var id = props.id;
        var serviceInfoObj = JSON.parse(sessionStorage.getItem("servicesInfo"));
        var serviceInfo = Object.keys(serviceInfoObj).map(key => [String(key), serviceInfoObj[key]]);

        for (let i = 0; i < serviceInfo.length; i++) {
            if(serviceInfo[i][1].idService === id){
                document.getElementById("editServiceDivTxtid").value = serviceInfo[i][1].description
                document.getElementById("editServiceDivNameServiceId").value = serviceInfo[i][1].name
                document.getElementById("editServiceDivRefValueId").value = serviceInfo[i][1].referencePrice
                document.getElementById("editServiceDivSelectCategories").value = serviceInfo[i][1].category.idCategory
            }
        }

        const data = {
            idService: id
        }
        sessionStorage.setItem("service",JSON.stringify({ ...data }))
        
        document.getElementById("editServiceDiv").style.display = "flex"
    }

    return (
        <details open className="cardServiceProvider">
            <summary className="infoService">
                <div className="nameServiceProvider">
                    <p>Serviço</p>
                    <p>: {props.nameService}</p>
                </div>
                <div className="valueAvg">
                    <p>Valor de Referência</p>
                    <p>: R${props.referencePrice}</p>
                </div>
                <div className="divIconsService">
                    <img onClick={openEditService} src={alterNameProvider} alt="icone para editr serviço" className="editServiceProvider" />
                    <img src={deleteService} alt="icone de deletar serviço" className="deleteService" />

                </div>
            </summary>
            <div className="descriptionServiceProvider">
                <p>{props.description}</p>
            </div>
        </details>
    )
}

export default cardServiceProviderServices