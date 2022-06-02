import "../assets/css/notFound.css"
import img404 from "../assets/img/404.gif"

function NotFound(){
    return(
        <>
        <h1>Ops, não encontramos essa página!</h1>
        <img src={img404} alt="" />
        </>       
    );
}

export default NotFound;