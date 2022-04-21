package school.sptech.server.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.Chat;
import school.sptech.server.model.ChatHasMsg;
import school.sptech.server.model.UserChat;
import school.sptech.server.repository.ChatHasMsgRepository;
import school.sptech.server.repository.ChatRepository;
import school.sptech.server.repository.MessageRepository;
import school.sptech.server.repository.UserChatRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatRepository dbRepositoryChat;
    @Autowired
    private UserChatRepository dbRepositoryUserChat;
    @Autowired
    private ChatHasMsgRepository dbRepositoryChatHasMsg;
    @Autowired
    private MessageRepository dbRepositoryMsg;

    @PostMapping("/{idCustomer}/{idWorker}")
    public ResponseEntity postChat(@RequestBody Chat novoChat,
                                   @RequestParam int idCustomer,
                                   @RequestParam int idWorker){
        try {
            UserChat register1 = new UserChat(idCustomer, novoChat.getIdChat());
            UserChat register2 = new UserChat(idWorker, novoChat.getIdChat());

            ChatHasMsg autoMsg = new ChatHasMsg(
                    dbRepositoryMsg.findByAutomaticAndfkUser('s',idWorker).getIdMsg(),
                    novoChat.getIdChat(),
                    LocalDate.now(),
                    'n');

            dbRepositoryChat.save(novoChat);
            dbRepositoryUserChat.save(register1);
            dbRepositoryUserChat.save(register2);
            dbRepositoryChatHasMsg.save(autoMsg);

            return ResponseEntity.status(201).build();
        }
        catch (NullPointerException npe){
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getChat(@RequestParam int id){
        List<Chat> list = dbRepositoryChat.chat(id);
                
        return ResponseEntity.status(200).body(list);
    }


}
