package school.sptech.server.model;

public abstract class  Usuario {
    private String nome;
    private String email;
    private String senha;



    public abstract void  insertUser();
    public abstract void updateUser();

}
