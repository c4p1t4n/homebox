package school.sptech.server.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.server.model.Chat;
import school.sptech.server.repository.ChatRepository;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatRepository repository;

    @PostMapping
    public ResponseEntity postChat(@RequestBody Chat novoChat){
        try {
            repository.save(novoChat);
            return ResponseEntity.status(201).build();
        }
        catch (NullPointerException npe){
            return ResponseEntity.status(401).build();
        }
    }
}
