package school.sptech.server.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.ChatHasMsg;
import school.sptech.server.model.Msg;
import school.sptech.server.repository.ChatHasMsgRepository;
import school.sptech.server.repository.ChatRepository;
import school.sptech.server.repository.MsgRepository;
import school.sptech.server.service.UserService;

@RestController
@RequestMapping("/msg")
public class MsgController {
    @Autowired
    private MsgRepository dbRepositoryMsg;
    @Autowired
    private ChatRepository dbRepositoryChat;
    @Autowired
    private ChatHasMsgRepository dbRepositoryChatHasMsg;
    @Autowired
    private UserService dbRepositoryUser;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/auto")
    public ResponseEntity<Object> postMsg(@RequestBody Msg msg,
            @RequestParam(value = "overwrite", required = false) Boolean overwrite) {
        try {

            msg.setAutomatic('y');

            if (Objects.nonNull(dbRepositoryMsg.findByAutomaticAndFkUser('y', msg.getFkUser()))
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

    @GetMapping
    public ResponseEntity<List<ChatHasMsg>> getMsgsFull() {
        List<ChatHasMsg> msgs = dbRepositoryChatHasMsg.findAllByChatIsNotNull();
        if (msgs.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(msgs);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{idMsg}")
    public ResponseEntity<Msg> getMsg(@PathVariable Integer idMsg) {
        if (!dbRepositoryMsg.existsById(idMsg)) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(dbRepositoryMsg.findById(idMsg).get());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Msg>> getMsgsPerUser(@PathVariable Integer idUser) {
        if (!dbRepositoryUser.existsById(idUser)) {
            return ResponseEntity.status(404).build();
        }
        List<Msg> msgs = dbRepositoryMsg.findByFkUser(idUser);

        if (msgs.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(msgs);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/chat/{idChat}")
    public ResponseEntity<List<ChatHasMsg>> getMsgsPerChat(@PathVariable Integer idChat) {
        if (!dbRepositoryChat.existsById(idChat)) {
            return ResponseEntity.status(404).build();
        }

        List<ChatHasMsg> msgs = dbRepositoryChatHasMsg.findAllByChat(dbRepositoryChat.findById(idChat));

        if (msgs.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(msgs);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/chat/{idChat}")
    public ResponseEntity<Void> postMsgInChat(@PathVariable Integer idChat, @RequestBody Msg newMsg) {
        if (!dbRepositoryChat.existsById(idChat)) {
            return ResponseEntity.status(404).build();
        }
        newMsg.setAutomatic('n');

        Msg msg = dbRepositoryMsg.save(newMsg);

        ChatHasMsg chatHasMsg = new ChatHasMsg(dbRepositoryChat.findById(idChat).get(), msg, LocalDate.now(), 'n');

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
