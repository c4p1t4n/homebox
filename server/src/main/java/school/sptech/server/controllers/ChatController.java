package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.*;
import school.sptech.server.repository.*;
import school.sptech.server.response.ChatJoinMsgJoinMedia;
import school.sptech.server.response.ChatsPerUser;
import school.sptech.server.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    @Autowired
    private UserService dbRepositoryUser;
    @Autowired
    private UserRepository dbUserRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/{idCustomer}/{idWorker}")
    public ResponseEntity<Void> postChat(@PathVariable Integer idCustomer, @PathVariable Integer idWorker) {
        if (!dbUserService.existsById(idCustomer)) {
            return ResponseEntity.status(404).build();
        }
        if (!dbUserService.existsById(idWorker)) {
            return ResponseEntity.status(404).build();
        }

        LocalDate today = LocalDate.now();
        Chat newChat = dbRepositoryChat.save(new Chat(today));

        UserHasChat customerChatAccess = new UserHasChat(dbRepositoryUser.findById(idCustomer).get(), newChat);
        UserHasChat WorkerChatAccess = new UserHasChat(dbRepositoryUser.findById(idWorker).get(), newChat);

        Msg autoMsg = dbRepositoryMsg.findByAutomaticAndUserId('y', idWorker);

        if (Objects.nonNull(autoMsg)) {
            LocalDateTime now = LocalDateTime.now();

            ChatHasMsg autoMsgChat = new ChatHasMsg(newChat, autoMsg, now, 'n');

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
    public ResponseEntity<List<ChatsPerUser>> getChatsPerUser(@PathVariable Integer fkUser) {
        if (!dbUserService.existsById(fkUser)) {
            return ResponseEntity.status(404).build();
        }

        List<UserHasChat> chats = dbRepositoryUserHasChat.findByUserWithPartner(fkUser);

        if (chats.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<ChatsPerUser> chatsPerUser = new ArrayList<ChatsPerUser>();

        for (UserHasChat chat : chats) {
            ChatHasMsg chm = dbRepositoryChatHasMsg.findTop1ByChatIdChatOrderBySendDateDesc(chat.getChat().getIdChat());
            chatsPerUser.add(new ChatsPerUser(chat.getId(), chat.getUser(), chat.getChat(), chm.getMsg(), chm.getSendDate(), chm.getSeen()));
        }

        return ResponseEntity.status(200).body(chatsPerUser);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/auto")
    public ResponseEntity<Object> postMsg(@RequestBody Msg msg,
            @RequestParam(value = "overwrite", required = false) Boolean overwrite) {
        try {

            msg.setAutomatic('y');

            if (Objects.nonNull(dbRepositoryMsg.findByAutomaticAndUserId('y', msg.getUser().getId()))
                    && ((Objects.nonNull(overwrite) && !overwrite) || Objects.isNull(overwrite))) {
                return ResponseEntity.status(405).body("Usuário ja possui mensagem automatica");

            }
            dbRepositoryMsg.save(msg);
            return ResponseEntity.status(201).build();
        } catch (NullPointerException npe) {
            return ResponseEntity.status(401).build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/short")
    public ResponseEntity<List<Msg>> getMsgs() {
        List<Msg> msgs = dbRepositoryMsg.findAll();

        if (msgs.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(msgs);
    }

    @GetMapping("/msg")
    public ResponseEntity<List<ChatHasMsg>> getMsgsFull() {
        List<ChatHasMsg> msgs = dbRepositoryChatHasMsg.findAllByChatIsNotNull();
        if (msgs.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(msgs);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/msg/{idMsg}")
    public ResponseEntity<Msg> getMsg(@PathVariable Integer idMsg) {
        if (!dbRepositoryMsg.existsById(idMsg)) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(dbRepositoryMsg.findById(idMsg).get());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/msg/user/{idUser}")
    public ResponseEntity<List<Msg>> getMsgsPerUser(@PathVariable Integer idUser) {
        if (!dbRepositoryUser.existsById(idUser)) {
            return ResponseEntity.status(404).build();
        }
        List<Msg> msgs = dbRepositoryMsg.findByUserId(idUser);

        if (msgs.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(msgs);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/msgs/{idChat}")
    public ResponseEntity<List<ChatJoinMsgJoinMedia>> getMsgsPerChat(@PathVariable Integer idChat) {
        if (!dbRepositoryChat.existsById(idChat)) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(dbRepositoryChatHasMsg.findAllByChat(idChat));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/msg/{idChat}/{idUsuario}")
    public ResponseEntity<Void> postMsgInChat(@PathVariable Integer idChat,
                                              @RequestBody Msg newMsg,
                                              @PathVariable Integer idUsuario) {

        if (!dbRepositoryChat.existsById(idChat)) {
            return ResponseEntity.status(404).build();
        }

        System.out.println(newMsg);
        newMsg.setAutomatic('n');

        User user = dbUserRepository.getById(idUsuario);
        newMsg.setUser(user);

        Msg msg = dbRepositoryMsg.save(newMsg);

        ChatHasMsg chatHasMsg = new ChatHasMsg(dbRepositoryChat.findById(idChat).get(), msg, LocalDateTime.now(), 'n');

        dbRepositoryChatHasMsg.save(chatHasMsg);

        return ResponseEntity.status(201).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping(value = "/read/{fkMsg}/{fkChat}")
    public ResponseEntity<Void> readMsg(@PathVariable Integer fkMsg, @PathVariable Integer fkChat) {
        if (dbRepositoryChatHasMsg.existsByMsgIdMsgAndChatIdChat(fkMsg, fkChat)) {
            dbRepositoryChatHasMsg.readNotification(fkMsg, fkChat);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

}
