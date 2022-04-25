package school.sptech.server.model;

import javax.persistence.*;

import school.sptech.server.service.ILogin;

@Entity
@Table(name = "staff")
public class Staff implements ILogin {

    @Column(name = "name")
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_staff")
    private Integer id;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @Column(name="authenticated")
    private Character authenticated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Character getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Character authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public Character login(String username, String password) {
        Boolean autenticacao = getPassword().equals(password) & getEmail().equals(username);
        if (autenticacao) {
            setAuthenticated('s');
        }else{
            setAuthenticated('n');
        }
        return getAuthenticated();
    }

}
