package school.sptech.server.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.User;
import school.sptech.server.model.UserCliente;
import school.sptech.server.model.UserPrestador;
import school.sptech.server.repository.UserClientRepository;
import school.sptech.server.repository.UserPrestadorRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UserController {


    @Autowired
    private UserClientRepository bancoCliente;
    List<User> users =new ArrayList<>();
    @Autowired
    private UserPrestadorRepository bancoPrestador;


    @PostMapping("/Cliente")
    public String cadastrarUsuarioCliente(@RequestBody UserCliente novoUsuario){

        try {

            if(novoUsuario.getTipoCliente().equals("cliente")){
                bancoCliente.save((UserCliente) novoUsuario);
            }else{
                return "Usuario invalido";
            }
            novoUsuario.setAutenticado(Boolean.FALSE);
            users.add(novoUsuario);
            return "Usuario cadastrado com sucesso";
        } catch (NullPointerException npe) {
            return "erro 405";
        }
    }

    @GetMapping()
    public List<UserCliente> getUsuarioCliente(){
       return bancoCliente.findAll();
    }


    @PostMapping("/Prestador")
    public String cadastrarUsuarioPrestador(@RequestBody UserPrestador novoUsuario){
        try {

            if(novoUsuario.getTipoCliente().equals("prestador")){
                bancoPrestador.save(novoUsuario);
            }else{
                return "Usuario invalido";
            }
            novoUsuario.setAutenticado(Boolean.FALSE);
            users.add(novoUsuario);
            return "Usuario cadastrado com sucesso";
        } catch (NullPointerException npe) {
            return "erro 405";
        }
    }


    @GetMapping("/Prestador")
    public List<UserPrestador> getUsuarioPrestador(){
        return bancoPrestador.findAll();
    }

    @GetMapping("/login/{userLogin}/{userPassword}")
    public String getLoginUser(@PathVariable String userLogin,@PathVariable String userPassword){
        for(User user:users){
           return  user.login(userLogin,userPassword);
        }
        return "Usuario não encontrado";
    }
    @GetMapping("/logoff/{userLogin}")
    public  String logoffUser(@PathVariable String userLogin){
        String stringReturn = "usuario nao existe";
        for(User user:users){
            if(user.getEmail().equals(userLogin) & user.getAutenticado().equals(Boolean.TRUE)){
                stringReturn = String.format("Usuario %s deslogado",user.getNome());
                return stringReturn;
            }
            if(user.getEmail().equals(userLogin) & user.getAutenticado().equals(Boolean.FALSE)){
                stringReturn = String.format("Usuario %s não esta logado",user.getNome());
                return stringReturn;
            }
        }
        return  stringReturn;
    }










}
