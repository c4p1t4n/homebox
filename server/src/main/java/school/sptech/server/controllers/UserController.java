package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.User;
import school.sptech.server.model.UserCliente;
import school.sptech.server.model.UserPrestador;
import school.sptech.server.repository.UserClientRepository;
import school.sptech.server.repository.UserPrestadorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserClientRepository bancoCliente;
    List<User> users = new ArrayList<>();
    @Autowired
    private UserPrestadorRepository bancoPrestador;

    @PostMapping("/cliente")
    public ResponseEntity cadastrarUsuarioCliente(@RequestBody UserCliente novoUsuario) {

        if (novoUsuario.getTipo().equals("cliente")) {
            bancoCliente.save((UserCliente) novoUsuario);
        } else {
            return ResponseEntity.status(403).build();
        }
        novoUsuario.setAutenticado(Boolean.FALSE);
        users.add(novoUsuario);
        return ResponseEntity.status(201).build();

    }

    @GetMapping("/cliente")
    public ResponseEntity<List<UserCliente>> getUsuarioCliente() {
        List<UserCliente> users = bancoCliente.findAll().stream().filter(user -> user.getTipo().equals("cliente"))
                .collect(Collectors.toList());
        return !users.isEmpty() ? ResponseEntity.status(200).body(users) : ResponseEntity.status(204).build();
    }

    @PostMapping("/prestador")
    public ResponseEntity cadastrarUsuarioPrestador(@RequestBody UserPrestador novoUsuario) {
        try {

            if (novoUsuario.getTipo().equals("prestador")) {
                bancoPrestador.save(novoUsuario);
            } else {
                return ResponseEntity.status(403).build();
            }
            novoUsuario.setAutenticado(Boolean.FALSE);
            users.add(novoUsuario);
            return ResponseEntity.status(201).build();
        } catch (NullPointerException npe) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/prestador")
    public ResponseEntity<List<UserPrestador>> getUsuarioPrestador() {
        List<UserPrestador> users = bancoPrestador.findAll().stream().filter(user -> user.getTipo().equals("prestador"))
                .collect(Collectors.toList());
        return !users.isEmpty() ? ResponseEntity.status(200).body(users) : ResponseEntity.status(204).build();
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsuario() {
        return !users.isEmpty() ? ResponseEntity.status(200).body(users) : ResponseEntity.status(204).build();
    }

    @GetMapping("/login/{userLogin}/{userPassword}")
    public ResponseEntity getLoginUser(@PathVariable String userLogin, @PathVariable String userPassword) {
        for (User user : users) {
            user.login(userLogin, userPassword);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping("/logoff/{userLogin}")
    public ResponseEntity logoffUser(@PathVariable String userLogin) {
        for (User user : users) {
            if (user.getEmail().equals(userLogin) & user.getAutenticado().equals(Boolean.TRUE)) {
                user.setAutenticado(Boolean.FALSE);
                return ResponseEntity.status(200).build();
            }
            if (user.getEmail().equals(userLogin) & user.getAutenticado().equals(Boolean.FALSE)) {
                return ResponseEntity.status(403).build();
            }
        }
        return ResponseEntity.status(400).build();
    }

}
