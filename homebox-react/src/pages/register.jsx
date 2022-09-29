import "../assets/css/style.css"
import VLibras from "@djpfs/react-vlibras"
import logo from "../assets/img/icon/logo-removebg-preview.png"
import api from "../api"

function Register() {
    return (
        <>
            <VLibras />
            <div className="register_body">
                <div className="container">
                    <a href="http://homebox.sytes.net">
                        <img src={logo} alt="logo Homebox" />
                    </a>
                </div>
                <div className="container">
                    <form className="register_div">
                        <h2 className="title_cadastro">Cadastro</h2>
                        <div className="div_input">
                            <p>Categoria</p>
                            <select name="category" id="category">
                                <option value="">Selecione</option>
                                <option value="worker">
                                    Prestador de serviço
                                </option>
                                <option value="customer">Cliente</option>
                            </select>
                        </div>
                        <div className="div_input">
                            <p>Nome Completo</p>
                            <input
                                type="text"
                                placeholder="xxxxxx xxxxxxx xxxxxx"
                                id="name"
                            />
                        </div>
                        <div className="div_input">
                            <p>CPF</p>
                            <input
                                id="cpf"
                                type="text"
                                placeholder="592493693-43"
                            />
                        </div>
                        <div className="div_input">
                            <p>Data Aniversario</p>
                            <input
                                id="birth_date"
                                type="date"
                            />
                        </div>
                        <div className="div_input">
                            <p>CEP</p>
                            <input
                                id="cep"
                                type="text"
                                placeholder="09385-220"
                            />
                        </div>
                        <div className="div_input">
                            <p>E-mail</p>
                            <input
                                id="email"
                                type="email"
                                placeholder="xxxxxx@gmail.com.br"
                            />
                        </div>
                        <div className="div_input">
                            <p>Senha</p>
                            <input
                                id="password"
                                type="password"
                                placeholder="xxxxxxxxxxxxx"
                            />
                        </div>
                        <div className="div_input">
                            <p>Digite novamente sua senha</p>
                            <input
                                id="passwordConfirm"
                                type="password"
                                placeholder="xxxxxxxxxxxxx"
                            />
                        </div>
                        <button className="button_register" onClick={register}>
                            Cadastrar
                        </button>
                    </form>
                </div>
            </div>
        </>
    )
}

// registerBtn.addEventListener("click", e => {
//     register(
//         inputName.value,
//         inputCPF.value,
//         inputCEP.value,
//         inputEmail.value,
//         inputType.selectedOptions[0].value,
//         inputPassword.value,
//         inputPasswordConfirm.value
//     )
// })
const register = e => {
    e.preventDefault()
    const inputName = document?.querySelector("#name")
    const inputCPF = document?.querySelector("#cpf")
    const inputBirthDate = document?.querySelector("#birth_date")
    const inputCEP = document?.querySelector("#cep")
    const inputEmail = document?.querySelector("#email")
    const inputType = document?.querySelector("#category")
    const inputPassword = document?.querySelector("#password")
    const inputPasswordConfirm = document?.querySelector("#passwordConfirm")

    const name = inputName?.value
    const cpf = inputCPF?.value
    const birthDate = inputBirthDate?.value
    const cep = inputCEP?.value
    const email = inputEmail?.value
    const type = inputType?.selectedOptions?.[0]?.value
    const password = inputPassword?.value
    const passwordConfirm = inputPasswordConfirm?.value

    if (
        !name ||
        !cpf ||
        !birthDate ||
        !cep ||
        !email ||
        !password ||
        !passwordConfirm ||
        !type
    ) {
        window.alert("Campos obrigatórios vazios!")
        return console.log("Campos obrigatórios vazios")
    }
    if (password !== passwordConfirm) {
        window.alert("Senhas diferentes!")

        return console.log("Senhas diferentes")
    }

    api.post(`users/${type}`, {
        name,
        email,
        password,
        cpf: cpf.trim().replace(".", "").replace(".", "").replace("-", ""),
        type,
        birthDate,
        cep: cep.trim().replace(".", "").replace(".", "").replace("-", "")
    })
        .then(({ status, data }) => {

            if (status === 201) {
                console.log("SUCESSO")
                window.alert("Cadastro realizado com sucesso!")
                sessionStorage.setItem(
                    "user",
                    JSON.stringify({ ...data })
                )
                if (data.type === "worker") window.location.href = "/profile/provider"
                else if (data.type === "customer") window.location.href = "/"
            } else if (status === 400) {
                console.log("DEU RUIM")
                window.alert("Não foi possivel realizar o cadastro!")
            }
        })
        .catch(error => {
            console.error(error)
            window.alert(error)
        })
}

export default Register
