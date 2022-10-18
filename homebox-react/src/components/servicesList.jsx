import "../assets/css/providerDash.css"
import api from "../api"
import CardServiceInProgressProvider from "../components/cardServiceInProgressProvider"
import { useEffect, useState } from "react"

function ServicesList() {
    const [services, setServices] = useState([])
    useEffect(() => {
        api.get(`/services/list/` + JSON.parse(sessionStorage.getItem("user")).id_user
        ).then(({ status, data }) => {
            if (status === 200) {
                
                setServices(data)
            }
        })
    }, [services])

    return (
        <>
            {services.map(item => (
                <CardServiceInProgressProvider
                    id={item.idScheduling}
                    service={item.nameService}
                    price={item.price}
                    client={item.customerId}
                    local={item.address}
                />
            ))}
        </>
    )
}

export default ServicesList