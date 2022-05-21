import '../assets/css/style.css';

import logo from "../assets/img/icon/logo-removebg-preview.png"
import google from "../assets/img/google.png"
import facebook from "../assets/img/facebook1.png"
import twitter from "../assets/img/twitter.png"

function Register() {
    return (
        <div class="register_body">
            <div class="container">
                <a href=""><img src={logo} alt="logo Homebox"/></a>
            </div>
            <div class="container">
                <form class="register_div">
                    <h2 class="title_cadastro">Cadastro</h2>
                    <div class="div_input">
                        <p>Nome Completo</p>
                        <input type="text" placeholder="xxxxxx xxxxxxx xxxxxx"/>
                    </div>
                    <div class="div_input">
                        <p>CPF</p>
                        <input type="text" placeholder="592493693-43"/>
                    </div>
                    <div class="div_input">
                        <p>CEP</p>
                        <input type="number" placeholder="09385-220"/>
                    </div>
                    <div class="div_input">
                        <p>E-mail</p>
                        <input type="email" placeholder="xxxxxx@gmail.com.br"/>
                    </div>
                    <div class="div_input">
                        <p>Categoria</p>
                        <select name="category" id="category">
                            <option value="">Selecione</option>
                            <option value="provider">Prestador de serviço</option>
                            <option value="client">Cliente</option>
                        </select>
                    </div>
                    <div class="div_input">
                        <p>Senha</p>
                        <input type="password" placeholder="xxxxxxxxxxxxx"/>
                    </div>
                    <div class="div_input">
                        <p>Digite novamente sua senha</p>
                        <input type="password" placeholder="xxxxxxxxxxxxx"/>
                    </div>
                    <a register href=""><button class="button_register">Cadastrar</button></a>
                </form>
            </div>
        </div>
    );
  }
  
  export default Register;