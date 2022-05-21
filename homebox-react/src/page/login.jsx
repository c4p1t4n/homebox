import '../assets/css/style.css';

import logo from "../assets/img/icon/logo-removebg-preview.png"
import google from "../assets/img/google.png"
import facebook from "../assets/img/facebook1.png"
import twitter from "../assets/img/twitter.png"

function Login() {
    return (
    <div className="login_body">
        <div className="homebox_logo">
            <a href=""><img className="logo_homebox" src={logo} alt="Logo Homebox"/></a>
        </div>
        <div className="div_login">
            <form className="login">
                <p className="login_title">Login</p>
                <div className="email_password">
                    <label>E-mail:</label>
                    <input name="email" type="text"/>
                    <label>Senha:</label>
                    <input name="password" type="password"/>
                    <p>OU</p>
                    <div className="img_container">
                        <img className="logo" src={google} alt="Ícone do google"/>
                        <img className="logo" src={facebook} alt="Ícone do facebook"/>
                        <img className="logo" src={twitter} alt="Ícone do twitter"/>
                    </div>
                </div>
            </form>
        </div>
        <div className="div_button">
            <button>Entrar</button><br/>
            <p><a href="">Ou cadastre-se aqui</a></p>
        </div>
    </div>
    );
  }
  
  export default Login;