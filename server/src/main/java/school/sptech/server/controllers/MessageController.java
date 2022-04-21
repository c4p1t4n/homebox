package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.Message;
import school.sptech.server.model.UserChat;
import school.sptech.server.repository.ChatRepository;
import school.sptech.server.repository.MessageRepository;

@RestController
@RequestMapping("/msg")
public class MessageController {
    @Autowired
    private MessageRepository dbRepositorymessage;

    @PostMapping
    public ResponseEntity postMsg(@RequestBody Message msg){
        try {
            msg.setAutomatic('n');
            dbRepositorymessage.save(msg);
            return ResponseEntity.status(201).build();
        }
        catch (NullPointerException npe){
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/auto")
    public ResponseEntity postAutoMsg(@RequestBody Message msg){
        try {
            msg.setAutomatic('s');
            dbRepositorymessage.save(msg);
            return ResponseEntity.status(201).build();
        }
        catch (NullPointerException npe){
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping
    public ResponseEntity getMsg(){
        return ResponseEntity.status(200).body(dbRepositorymessage.msg());
    }
}
