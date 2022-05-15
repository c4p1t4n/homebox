package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.User;
import school.sptech.server.model.UserCustomer;
import school.sptech.server.model.UserWorker;
import school.sptech.server.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private UserService dbUserService;

    @GetMapping("worker/{name}")
    public ResponseEntity getWorkerByName(@PathVariable String name) {

        List<User> list = dbUserService.getWorkersByName("%"+name+"%");
        if (list.isEmpty()) {
            return ResponseEntity.status(201).build();
        }
        return  ResponseEntity.status(200).body(list);
    }

    /*FAZER DEPOIS DE ARRUMAR AS FK */
    @GetMapping("worker/{category}")
    public ResponseEntity getWorkerByCategory(@PathVariable String category) {
        List<User> list = dbUserService.getWorkersByName("%"+category+"%");
        if (list.isEmpty()) {
            return ResponseEntity.status(201).build();
        }
        return  ResponseEntity.status(200).body(list);
    }

    @GetMapping("worker/{service}")
    public ResponseEntity getWorkerByService(@PathVariable String service) {

        List<User> list = dbUserService.getWorkersByName("%"+service+"%");
        if (list.isEmpty()) {
            return ResponseEntity.status(201).build();
        }
        return  ResponseEntity.status(200).body(list);
    }

    @GetMapping("chat/{name}")
    public ResponseEntity getChatByName(@PathVariable String name) {

        List<User> list = dbUserService.getWorkersByName("%"+name+"%");
        if (list.isEmpty()) {
            return ResponseEntity.status(201).build();
        }
        return  ResponseEntity.status(200).body(list);
    }

    @GetMapping("chat/{mensage}")
    public ResponseEntity getChatByMensage(@PathVariable String mensage) {

        List<User> list = dbUserService.getWorkersByName("%"+mensage+"%");
        if (list.isEmpty()) {
            return ResponseEntity.status(201).build();
        }
        return  ResponseEntity.status(200).body(list);
    }
    /* ******************************************* */
}
