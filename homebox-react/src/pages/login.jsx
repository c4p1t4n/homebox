/* eslint-disable jsx-a11y/anchor-is-valid */
import "../assets/css/style.css"

import logo from "../assets/img/icon/logo-removebg-preview.png"
import google from "../assets/img/google.png"
import facebook from "../assets/img/facebook1.png"
import iconClose from "../assets/img/iconCloseBlue.png"
import twitter from "../assets/img/twitter.png"
import api from "../api"
import VLibras from "@djpfs/react-vlibras"

function Login() {
    return (
        <>
            <VLibras />
            <body className="login_body">
                <div className="homebox_logo">
                    <a href="http://homebox.sytes.net">
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
                        <button id="buttonLogin" onClick={login}>
                            Entrar
                        </button>
                        <br />
                        <a href="/register">
                            <p>
                                Ou <p id="register">cadastre-se</p> aqui
                            </p>
                        </a>
                        <br />
                        <p onClick={openDivForgotPassword} id="password">
                            Esqueceu sua senha ?
                        </p>
                    </div>
                </div>
            </body>
            <div id="forgotPasswordDiv" className="forgotPasswordDiv">
                <img
                    onClick={openDivForgotPassword}
                    src={iconClose}
                    alt="Icone de fechar"
                />
                <form>
                    <h2>Recuperar Senha</h2>
                    <div className="inputFormForgotPassword">
                        <label htmlFor="email">
                            Informe o e-mail cadastrado:
                        </label>
                        <input type="text" placeholder="Digite aqui" />
                    </div>
                    <label htmlFor="email">
                        Enviaremos um link para redefinição de senha no email
                        informado
                    </label>
                    <input
                        onClick={resetPassword}
                        id="submitForgotPassword"
                        type="submit"
                    />
                </form>
            </div>
        </>
    )
}

function openDivForgotPassword() {
    const logoffOpenDiv = document.getElementById("forgotPasswordDiv")

    logoffOpenDiv.style.display =
        logoffOpenDiv.style.display === "flex" ? "none" : "flex"
}

function resetPassword() {}

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
        .catch(err => err.response)
        .then(({status, data}) => {
            if (status === 200) {
                sessionStorage.setItem("user", JSON.stringify({...data}))
                if (data.type === "worker")
                    window.location.href = "/profile/provider"
                else if (data.type === "customer") window.location.href = "/"
                else throw new TypeError("TIPO DE USUARIO INVALIDO")
            } else if (status === 400) {
                window.alert("Senha errada!")
            } else if (status === 404) {
                api.post("/staff/login/", {
                    email,
                    password
                })
                    .catch(err => err.response)
                    .then(({status, data}) => {
                        console.log(data)
                        if (status === 200) {
                            sessionStorage.setItem(
                                "staff",
                                JSON.stringify({...data})
                            )
                            window.location.href = "/staff"
                        } else window.alert("Email não encontrado!")
                    })
            }
        })
        .catch(err => {
            console.error(err)
        })
}

export default Login
