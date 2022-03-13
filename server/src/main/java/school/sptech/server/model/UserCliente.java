package school.sptech.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class UserCliente extends User {

    public UserCliente() {

    }

    @Override
    public String login(String user, String password) {
        String stringReturn = "Email e/ou senha incorreta";
        if (getSenha().equals(password) & getEmail().equals(user)) {
            stringReturn = String.format("Usuario Prestador logado com sucesso");
            setAutenticado(Boolean.TRUE);
            return stringReturn;
        }
        return stringReturn;
    }

    public UserCliente(Integer id, String nome, String email, String senha, String cpf, String token, String tipo,
            String foto, String cep) {
        super(id, nome, email, senha, cpf, token, tipo, foto, cep);
    }
}
