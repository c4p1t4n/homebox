import '../assets/css/style.css';

import logo from "../assets/img/icon/logo-removebg-preview.png"
import google from "../assets/img/google.png"
import facebook from "../assets/img/facebook1.png"
import twitter from "../assets/img/twitter.png"

function Login() {
    return (
        <body className="login_body">
            <div className="homebox_logo">
                <a href="../index.html"><img className="logo_homebox" src={logo} alt="" /></a>
            </div>
            <div className='div_login_and_button'>
                <div className="div_login">
                    <h2>Login</h2>
                    <div className='div_emails'>
                        <p>E-mail</p>
                        <input type="e-mail" placeholder='xxxxxx.xxxxxx@gmail.com.br' />
                    </div>
                    <div className='div_password'>
                        <p>Senha</p>
                        <input type="password" placeholder='XXXXXXXXXX' />
                    </div>
                    <p>OU</p>
                    <div className="container_img">
                        <img src={google} alt="Google" />
                        <img src={facebook} alt="Facebook" />
                        <img src={twitter} alt="Twiter" />
                    </div>
                </div>
                <div className="div_button">
                    <button loginBtn>Entrar</button>
                    <br />
                    <a href="http://localhost:3000/register"><p>Ou <p id='register'>cadastre-se</p> aqui</p></a>
                    <br />
                    <a href=""><p id='password'>Esqueceu sua senha ?</p></a>
                </div>
            </div>
        </body>
    );
}

export default Login;