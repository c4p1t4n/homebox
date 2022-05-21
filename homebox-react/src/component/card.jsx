function Card(props) {
  return (
    <div className={props.class}>
        <img src={props.icon} alt="Icone de categoria"/>
        <p className={props.color}>{props.categoria}</p>
        <div onclick="" className="access">
            <p className={props.color}>ACESSAR</p>
            <img src={props.iconAccesses} alt="Icone de acesso"/>
        </div>
    </div>
  );
}
export default Card;