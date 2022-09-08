import MenuLeftProvider from "../components/menuLeftProvider"

import "../assets/css/providerDash.css"

import questionMark from "../assets/img/quesntion-mark.png"

import Chart from "react-apexcharts"

import React, { Component } from "react"

import api from "../api"





function getLastSevendays(){
    var date = new Date();
    var list = [];
    var count = 0;   
    var list_date = []
    for (var i = 0; i < 7; i++) {
        
        list.push(date.getDate()-count)
        count++
        console.log(count)
    }
    for(var x=0;x<=list.length;x++) {
        date = new Date();
        if(list[x]<= list[0]){
            list_date.push(String(list[x])+'/'+ String(date.getMonth()+1))
        }else{
            list_date.push(String(list[x])+'/'+ String(date.getMonth()))
        }
    }
    console.log(list)
    console.log(list_date)
    return list_date
}


class relatorioProvider extends Component {
    constructor(props) {
        
        super(props)
        this.state = {
            options: {
                title:{
                    text:"Sua nota ao longo do tempo",
                    align:"center",
                    style:{
                        fontSize:"28px",
                        color:'#174574'
                    }
                },
                chart: {
                    id: "basic-bar"
                },
                xaxis: {
                    categories: [1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998]
                }
            },
            series: [
                {
                    name: "series-1",
                    data: [5, 4, 4.3, 3.7, 4, 5, 5, 4.7]
                }
            ],
            avg: 0,
            visitas_semana:0
        };
    }
     




    



    async componentDidMount() {
        let id_user = JSON.parse(sessionStorage.getItem('user')).id_user

        let temp_value = await api.post(`/users/avg-rating/1`,).catch(err => err.response)
        this.setState({avg: (temp_value.data == 0  ? "0" : temp_value.data.toFixed(2))})
        console.log(this.state.avg)


        let visitas_semana = await api.get(`/interestAcess/avg_last_seven_days/1`,).catch(err => err.response)
        this.setState({visitas_semana: (visitas_semana.data == 0  ? "0" : visitas_semana.data)})
        console.log(this.state.visitas_semana)

        let list_medias_ultima_semana = await api.get(`/interestAcess/getListAvgLastSevenDays/1`,).catch(err => err.response)

        this.setState({series:[
                {
                    data: list_medias_ultima_semana.data,
                }
            ]     
        
        });

        this.setState({options:{
            title:{
                text:"Sua nota ao longo do tempo",
                align:"center",
                style:{
                    fontSize:"28px",
                    color:'#174574'
                }
            },
            chart: {
                id: "basic-bar"
            },
            xaxis: {
                categories: getLastSevendays()
            }
        }
        })
        
        

        
    
    }
    render() {
        
        // const getAvgRating = async(idUser) => {
        //    let valor = await api.post(`/users/avg-rating/${idUser}`,).catch(err => err.response)
        //     console.log(valor)
        //     console.log(valor.data)
        //     return valor.data
        //         // console.log(`data:${data}`)
        // }
                return (
                    <>
                
                <div className="divBodyProfileProvider">
                    <MenuLeftProvider />
                    <div className="divRightProfileProvider">
                        <div className="divRightProfileProviderRelatorio">
                            <div className="divRightProfileProviderTop">
                                <div className="divRightProfileProviderTopLeft">
                                    <div className="divRightProfileProviderTopLeftTOP">
                                        <div className="visitsYourProfile">
                                            <div className="visitsYourProfileTOP">
                                                <p>Visitas ao seu perfil</p>
                                                <img src={questionMark} alt="Informações sobre visitas ao seu perfil" />
                                            </div>
                                            <div className="visitsYourProfileBut">
                                                <p id="indice">13%</p>
                                                <div className="indiceDiv2">
                                                    <p id="indice2">{this.state.visitas_semana}</p>
                                                    <p>nessa semana</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="visitsYourProfile">
                                            <div className="visitsYourProfileTOP">
                                                <p>Sua nota média</p>
                                                <img src={questionMark} alt="Informações sobre visitas ao seu perfil" />
                                            </div>
                                            <div className="visitsYourProfileBut">
                                                <p id="indice">+10</p>
                                                <div className="indiceDiv2">
                                                    <p id="indice2">{this.state.avg}</p>
                                                    <p>**estrelas**</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <br />
                                    <div className="divRightProfileProviderTopLeftBut">
                                        <p>Sua nota média é maior que a dos outros Pintores da nossa plataforma (4.0 X 3.8)</p>
                                        <p className="indiceDiv3">+0.20</p>
                                    </div>
                                </div>
                                <div className="divRightProfileProviderTopRight">

                                </div>
                            </div>
                            <br />
                            <div className="divRightProfileProviderMed">
                                <Chart
                                    options={this.state.options}
                                    series={this.state.series}
                                    type="line"
                                    height={310}
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </>
        )
    }

}



export default relatorioProvider

