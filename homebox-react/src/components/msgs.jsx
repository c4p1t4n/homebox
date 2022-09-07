import CardChatMsgLeft from "../components/cardChatMsgLeft";
import CardChatMsgRight from "../components/cardChatMsgRight";
import CardAudioRight from "../components/cardAudioRight";
import CardAudioLeft from "../components/cardAudioLeft";
import CardImgRight from "../components/cardImgRight";
import CardImgLeft from "../components/cardImgLeft";


function Msgs() {
    
    var list = []
    const info = JSON.parse(sessionStorage.getItem("chatInfo"));
    for(var i in info){
        if(info[i].userId===JSON.parse(sessionStorage.getItem("user")).id_user){
            if(info[i].message !== ""){
                list.push(<CardChatMsgRight text={info[i].message}/>) 
            }
            else{
                if(info[i].type === "image/png" || info[i].type === "image/jpeg"){
                    list.push(<CardImgRight  url={info[i].path}/>)
                }
                else if(info[i].type === "audio/wav"){
                    list.push(<CardAudioRight url={info[i].path}/>)
                }
            }
        }
        else{
            if(info[i].message !== ""){
                list.push(<CardChatMsgLeft text={info[i].message}/>)
            }
            else{
                if(info[i].type === "image/png" || info[i].type === "image/jpeg"){
                    list.push(<CardImgLeft  url={info[i].path}/>)
                }
                else if(info[i].type === "audio/wav"){
                    list.push(<CardAudioLeft url={info[i].path}/>)
                }
            }
        }
    }
    
    return (
        <>
        {list}
        </>
    )
}

export default Msgs