import "../assets/css/style.css"

import logo from "../assets/img/icon/logo-removebg-preview.png"
import google from "../assets/img/google.png"
import facebook from "../assets/img/facebook1.png"
import twitter from "../assets/img/twitter.png"
import api from "../api"
import VLibras from "@djpfs/react-vlibras"

function Login() {
    return (
        <>
        <VLibras/>
        <body className="login_body">
            <div className="homebox_logo">
                <a href="http://127.0.0.1:5500/website/index.html">
                    <img className="logo_homebox" src={logo} alt="" />
                </a>
            </div>
            <div className="div_login_and_button">
                <div className="div_login">
                    <h2>Login</h2>
                    <div className="div_emails">
                        <p>E-mail</p>
                        <input
                            onKeyDown={keyStroke}
                            id="email"
                            type="e-mail"
                            placeholder="xxxxxx.xxxxxx@gmail.com.br"
                        />
                    </div>
                    <div className="div_password">
                        <p>Senha</p>
                        <input
                            onKeyDown={keyStroke}
                            id="password"
                            type="password"
                            placeholder="XXXXXXXXXX"
                        />
                    </div>
                    <p>OU</p>
                    <div className="container_img">
                        <img src={google} alt="Google" />
                        <img src={facebook} alt="Facebook" />
                        <img src={twitter} alt="Twiter" />
                    </div>
                </div>
                <div className="div_button">
                    <button id="buttonLogin" onClick={login}>Entrar</button>
                    <br />
                    <a href="/register">
                        <p>
                            Ou <p id="register">cadastre-se</p> aqui
                        </p>
                    </a>
                    <br />
                    <a href="/">
                        <p id="password">Esqueceu sua senha ?</p>
                    </a>
                </div>
            </div>
        </body>
        </>
    )
}

const keyStroke = e => {
    const buttonLogin = document.querySelector("#buttonLogin")
   
   
    if (e.key === "Enter") {
        buttonLogin.click()
    }
}


const login = e => {
    e.preventDefault()
    const passwordInput = document.querySelector("#password")
    const emailInput = document.querySelector("#email")

    const email = emailInput?.value
    const password = passwordInput?.value

    if (!email || !password) {
        return window.alert("Preencha os campos para entrar")
    }

    api.post("/users/login/", {
        email,
        password
    })
        .then(response => {
            console.log(response)
            if (response.status === 200) {
                console.log("SUCESSO")
                sessionStorage.setItem(
                    "user",
                    JSON.stringify({...response.data})
                )
                window.location.href = "/"
            } else if (response.status === 400) {
                window.alert("Usuario ou Senha errado(s)!")
            } else if (response.status === 404) {
                console.log("DEU MUITO RUIM")
                window.alert("Usuario ou Senha errado(s)!")

            }
        })
        .catch(err => {
            console.error(err)
        })
}

export default Login
