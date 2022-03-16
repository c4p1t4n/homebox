package school.sptech.server.model;

import javax.persistence.*;

import school.sptech.server.service.ILogin;

@Entity
@Table(name = "staff")
public class Staff implements ILogin {

    @Column(name = "nome")
    private String nome;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_staff")
    private Integer id;

    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Boolean login(String username, String password) {
        return getSenha().equals(password) && getEmail().equals(username);
    }

}
