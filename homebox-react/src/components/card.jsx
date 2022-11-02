import api from "../api"

function Card(props) {
  function sendSearch() {
    api.post(`/interestAccess/category/${JSON.parse(sessionStorage.getItem("user")).id_user}/${props.categoria}`)

    const searchValue = props.categoria
    const user = JSON.parse(sessionStorage.getItem("user"))
    if (user) {
        let { id_user: idUser } = user
        api.post("/search", { idUser, value: searchValue }).then(response => {
            if (response.status === 201) {
                console.log("SUCESSO")
            } else {
                console.log(response)
            }
        })
    }
    window.location.href = `/search?search=${searchValue}`
  }


  return (
    <div className={props.class} onClick={sendSearch}>
        <img src={props.icon} alt="Icone de categoria"/>
        <p className={props.color}>{props.categoria}</p>
        <div  className="access">
            <p className={props.color}>ACESSAR</p>
            <img src={props.iconAccesses} alt="Icone de acesso"/>
        </div>
    </div>
  );
}
export default Card;
