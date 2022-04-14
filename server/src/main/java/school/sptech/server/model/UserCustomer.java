package school.sptech.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserCustomer extends User {

    public UserCustomer() {

    }

    @Override
    public Boolean login(String user, String password) {
        Boolean autenticacao = getPassword().equals(password) & getEmail().equals(user);
        if (autenticacao) {
            setAuthenticated(Boolean.TRUE);
        }
        return autenticacao;
    }

    public UserCustomer(Integer id, String name, String email, String password, String cpf, String token, String type,
            String picture, String cep) {
        super(id, name, email, password, cpf, token, type, picture, cep);
    }
}