import '../assets/css/style.css';

import logo from "../assets/img/icon/logo-removebg-preview.png"
import google from "../assets/img/google.png"
import facebook from "../assets/img/facebook1.png"
import twitter from "../assets/img/twitter.png"

function Login() {
    return (
        <body className="login_body">
            <div className="homebox_logo">
                <a href="./index.html"><img className="logo_homebox" src={logo} alt="" /></a>
            </div>
            <div className="div_login">
                <form className="login" id="form_login">
                    <p className="login_title">Login</p>
                    <div className="email_password">
                        <label>E-mail:</label>
                        <input email name="email" type="text" required />
                        <label>Senha:</label>
                        <input password name="password" type="password" required />
                        <p>OU</p>
                        <div className="img_container">
                            <img className="logo" src={google} alt="" />
                            <img className="logo" src={facebook} alt="" />
                            <img className="logo" src={twitter} alt="" />
                        </div>
                    </div>
                </form>
            </div>
            <div className="div_button">
                <button loginBtn>Entrar</button><br />
                <p><a href="register.html">Ou cadastre-se aqui</a></p>
            </div>
        </body>
    );
}

export default Login;