import "../assets/css/footer.css"

import blackLine from "../assets/img/lineBlackFooter.png"
import logo from "../assets/img/icon/logo-removebg-preview.png"
import linkedin from "../assets/img/linkedin.png"
import instagram from "../assets/img/instagram.png"
import facebook from "../assets/img/facebook.png"
import twiter from "../assets/img/twiter.png"

function Footer() {
    return (
        <footer>
        <div className="container">
            <img className="lineFooter" src={blackLine} alt="Linha preta"/>
            <div className="contact-div">
                <div className="social-media">
                    <img className="logoHomebox" src={logo} alt="Logo Homebox"/>
                    <h2>Nossas redes sociais:</h2>
                    <div className="img-social-media">
                        <a href=""><img src={linkedin} alt=""/></a>
                        <a href=""><img src={instagram} alt=""/></a>
                        <a href=""><img src={facebook} alt=""/></a>
                        <a href=""><img src={twiter} alt=""/></a>
                    </div>
                </div>
                <div className="address">
                    <h1>Endereço</h1>
                    <h2>Rua Haddock Lobo, 595 Cerqueira César, São Paulo SP, 01414-001</h2>
                    <h1>Telefone</h1>
                    <h2>+55 3099-8855</h2>
                </div>
            </div>
        </div>
        </footer>
    );
  }
  
  export default Footer;