package school.sptech.server.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;
import school.sptech.server.service.ILogin;

@Entity
@Table(name = "user")
@MappedSuperclass
public  class User implements ILogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    @JsonIgnore
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    @Email
    private String email;
    @Column(name = "password")
    private String password;

    @Column(name = "cpf")
    @CPF
    private String cpf;

    @Column(name = "token")
    private String token;

    @Column(name = "type")
    private String type;

    @Column(name = "picture")
    private String picture;

    @Column(name = "cep")
    private String cep;

    @Column(name="authenticated")
    @JsonIgnore
    private Character authenticated;
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }




    public User() {

    }

    public User(Integer id, String name, String email, String password, String cpf, String token, String type,
            String picture, String cep) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.token = token;
        this.type = type;
        this.picture = picture;
        this.cep = cep;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Character authenticated) {
        this.authenticated = authenticated;
    }

    public Integer getId_user() {
        return id;
    }

    public void setId_user(Integer id_user) {
        this.id = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Character login(String user, String password) {
        Boolean autenticacao = getPassword().equals(password) & getEmail().equals(user);
        if (autenticacao) {
            setAuthenticated('s');
        }else{
            setAuthenticated('n');
        }
        return getAuthenticated();
    }


    @Override
    public String toString() {
        return String.format("%05d %-14s %-20s %-14s %-14s %-14s %-14s %-14s %-14s",
                             id, name, email, password, cpf, token, type, picture, cep);
    }
}
