package school.sptech.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class UserPrestador extends User {
    public UserPrestador() {
    }

    @Override
    public Boolean login(String user, String password) {
        Boolean autenticacao = getSenha().equals(password) & getEmail().equals(user);
        if (autenticacao) {
            setAutenticado(Boolean.TRUE);
        }
        return autenticacao;
    }

    public UserPrestador(Integer id, String nome, String email, String senha, String cpf, String token,
            String tipo, String foto, String cep) {
        super(id, nome, email, senha, cpf, token, tipo, foto, cep);
    }
}
