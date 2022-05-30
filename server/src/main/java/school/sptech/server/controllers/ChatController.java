package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.Chat;
import school.sptech.server.model.ChatHasMsg;
import school.sptech.server.model.Msg;
import school.sptech.server.model.UserHasChat;
import school.sptech.server.repository.ChatHasMsgRepository;
import school.sptech.server.repository.ChatRepository;
import school.sptech.server.repository.MsgRepository;
import school.sptech.server.repository.UserHasChatRepository;

import school.sptech.server.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatRepository dbRepositoryChat;
    @Autowired
    private UserHasChatRepository dbRepositoryUserHasChat;
    @Autowired
    private ChatHasMsgRepository dbRepositoryChatHasMsg;
    @Autowired
    private MsgRepository dbRepositoryMsg;
    @Autowired
    private UserService dbUserService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/{idCustomer}/{idWorker}")
    public ResponseEntity<Void> postChat(@PathVariable Integer idCustomer, @PathVariable Integer idWorker) {
        if (!dbUserService.existsById(idCustomer)) {
            return ResponseEntity.status(404).build();
        }
        if (!dbUserService.existsById(idWorker)) {
            return ResponseEntity.status(404).build();
        }

        LocalDate now = LocalDate.now();
        Chat newChat = dbRepositoryChat.save(new Chat(now));

        UserHasChat customerChatAccess = new UserHasChat(idCustomer, newChat.getIdChat());
        UserHasChat WorkerChatAccess = new UserHasChat(idWorker, newChat.getIdChat());

        Msg autoMsg = dbRepositoryMsg.findByAutomaticAndFkUser('y', idWorker);

        if (Objects.nonNull(autoMsg)) {

            ChatHasMsg autoMsgChat = new ChatHasMsg(
                    autoMsg.getIdMsg(),
                    newChat.getIdChat(),
                    now,
                    'n');

            dbRepositoryChatHasMsg.save(autoMsgChat);
        }

        dbRepositoryUserHasChat.save(customerChatAccess);
        dbRepositoryUserHasChat.save(WorkerChatAccess);

        return ResponseEntity.status(201).build();

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{idChat}")
    public ResponseEntity<Chat> getChat(@PathVariable Integer idChat) {
        if (!dbRepositoryChat.existsById(idChat)) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(dbRepositoryChat.findById(idChat).get());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<Chat>> getChats() {
        List<Chat> chats = dbRepositoryChat.findAll();

        if (chats.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(chats);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/user/{fkUser}")
    public ResponseEntity<List<UserHasChat>> getChatsPerUser(@PathVariable Integer fkUser) {
        if (!dbUserService.existsById(fkUser)) {
            return ResponseEntity.status(404).build();
        }

        List<UserHasChat> chats = dbRepositoryUserHasChat.findChatByUser(dbUserService.findById(fkUser).get());

        if (chats.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(chats);
    }

}
