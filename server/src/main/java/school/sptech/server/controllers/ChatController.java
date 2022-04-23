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
import school.sptech.server.repository.UserCustomerRepository;
import school.sptech.server.response.ChatJoinUserHasChat;

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
    private UserCustomerRepository dbRepositoryUser;

    @PostMapping("/{idCustomer}/{idWorker}")
    public ResponseEntity postChat(@PathVariable Integer idCustomer, @PathVariable Integer idWorker) {
        try {
            LocalDate now = LocalDate.now();
            Chat newChat = dbRepositoryChat.save(new Chat(now));

            UserHasChat customerChatAccess = new UserHasChat(idCustomer, newChat.getIdChat());
            UserHasChat WorkerChatAccess = new UserHasChat(idWorker, newChat.getIdChat());
            System.out.println('1');
            Msg autoMsg = dbRepositoryMsg.findByAutomaticAndFkUser('y', idWorker);
            System.out.println(autoMsg.getMessage());

            if (Objects.nonNull(autoMsg)) {
                System.out.println('2');
                ChatHasMsg autoMsgChat = new ChatHasMsg(
                        autoMsg.getIdMsg(),
                        newChat.getIdChat(),
                        now,
                        'n');

                dbRepositoryChatHasMsg.save(autoMsgChat);
            }
            System.out.println('1');

            dbRepositoryUserHasChat.save(customerChatAccess);
            dbRepositoryUserHasChat.save(WorkerChatAccess);

            return ResponseEntity.status(201).build();
        } catch (NullPointerException npe) {
            return ResponseEntity.status(405).build();
        }
    }

    @GetMapping("/{idChat}")
    public ResponseEntity<Chat> getChat(@PathVariable Integer idChat) {
        if (!dbRepositoryChat.existsById(idChat)) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(dbRepositoryChat.findById(idChat).get());
    }

    @GetMapping
    public ResponseEntity<List<Chat>> getChats() {
        List<Chat> chats = dbRepositoryChat.findAll();

        if (chats.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(chats);
    }

    @GetMapping("/user/{fkUser}")
    public ResponseEntity<List<ChatJoinUserHasChat>> getChatsPerUser(@PathVariable Integer fkUser) {
        if (!dbRepositoryUser.existsById(fkUser)) {
            return ResponseEntity.status(404).build();
        }

        List<ChatJoinUserHasChat> chats = dbRepositoryChat.findChatsPerUser(fkUser);

        if (chats.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(chats);
    }

}
