package school.sptech.server.model;


import javax.persistence.*;

@MappedSuperclass
public abstract   class User {

    @Column(name="nome")
    private String nome;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Integer id;

    @Column(name="email")
    private String email;
    @Column(name="senha")
    private String senha;

    @Column(name="cpf")
    private String cpf;

    @Column(name="token")
    private String token;

    @Column(name="tipo")
    private String tipoCliente;

    @Column(name="foto")
    private  String foto;

    @Transient
    private Boolean autenticado;

    public User(){


    }


    public abstract String login(String user,String password);

    public User(Integer id, String nome, String email, String senha, String cpf, String token, String tipoCliente, String foto) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }

    public Integer getId_usuario() {
        return id;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }



}
