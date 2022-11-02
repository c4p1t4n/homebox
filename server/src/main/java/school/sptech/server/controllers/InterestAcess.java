package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.*;
import school.sptech.server.repository.*;
import school.sptech.server.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/interestAccess")
public class InterestAcess {
    @Autowired
    TagRepository tagRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService dbServiceUser;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    UserHasTagRepository userHasTag;

    @Autowired
    ServiceHasTagRepository serviceHasTagRepository;

    @Autowired
    CategoryHasTagRepository categoryHasTagRepository;


    @Autowired
    InterestAccessRepository interestAccessRepository;

    @PostMapping("category/{userId}/{categoryName}")
    public ResponseEntity<String> postInterestAcessCategory(@PathVariable Integer userId,
                                                            @PathVariable String categoryName) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(400).body("Usuario não existente");
        }

        User user = userRepository.getById(userId);
        Category category = categoryRepository.findByNameIgnoreCase(categoryName).get(0);

        if (tagRepository.existsByValue("Cat: "+categoryName)){
            Tag tag = tagRepository.findByValue("Cat: "+categoryName);

            categoryHasTagRepository.save(new CategoryHasTag(category, tag));
            interestAccessRepository.save(new InterestAccess(user, tag, LocalDate.now()));

            return ResponseEntity.status(201).build();
        }
        else {
            Tag tag = tagRepository.save(new Tag("Cat: " + categoryName));

            categoryHasTagRepository.save(new CategoryHasTag(category, tag));
            interestAccessRepository.save(new InterestAccess(user, tag, LocalDate.now()));

            return ResponseEntity.status(201).build();
        }
    }

    @PostMapping("service/{userId}/{serviceName}")
    public ResponseEntity<String> postInterestAcessService(@PathVariable Integer userId,
                                                            @PathVariable String serviceName) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(400).body("Usuario não existente");
        }

        User user = userRepository.getById(userId);
        Service service = serviceRepository.findByName(serviceName);

        if (tagRepository.existsByValue("Serv: "+serviceName)){
            Tag tag = tagRepository.findByValue("Serv: "+serviceName);

            serviceHasTagRepository.save(new ServiceHasTag(service,tag));
            interestAccessRepository.save(new InterestAccess(user,tag, LocalDate.now()));

            return  ResponseEntity.status(201).build();
        }
        else {
            Tag tag = tagRepository.save(new Tag("Serv: "+serviceName));

            serviceHasTagRepository.save(new ServiceHasTag(service,tag));
            interestAccessRepository.save(new InterestAccess(user,tag, LocalDate.now()));

            return  ResponseEntity.status(201).build();
        }
    }

    @PostMapping("user/{userId}/{workerId}")
    public ResponseEntity<String> postInterestAcessUser(@PathVariable Integer userId,
                                                        @PathVariable Integer workerId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(400).body("Usuario não existente");
        }

        User user = userRepository.getById(userId);
        User worker = userRepository.getById(workerId);

        if (tagRepository.existsByValue("User: "+worker.getId_user())){
            Tag tag = tagRepository.findByValue("User: "+worker.getId_user());

            userHasTag.save(new UserHasTag(worker,tag));
            interestAccessRepository.save(new InterestAccess(user,tag, LocalDate.now()));

            return  ResponseEntity.status(201).build();
        }
        else {
            Tag tag = tagRepository.save(new Tag("User: "+worker.getId_user()));

            userHasTag.save(new UserHasTag(worker,tag));
            interestAccessRepository.save(new InterestAccess(user,tag, LocalDate.now()));

            return  ResponseEntity.status(201).build();
        }
    }

    @GetMapping("/avg_last_seven_days/{id_user}")
    public  ResponseEntity<Integer> AvgLastSevenDays(@PathVariable Integer id_user){
       return ResponseEntity.status(200).body(interestAccessRepository.countIntestAcess(id_user,LocalDate.now().minusDays(7)));
    }

    @GetMapping("/getListAvgLastSevenDays/{id_user}")
    public  ResponseEntity<List<Double>> ListAvgLastSevenDays(@PathVariable Integer id_user){


        return ResponseEntity.status(200).body(ratingRepository.getAvgRatingForWorkerLastSevenDays(id_user,LocalDate.now().minusDays(6)));
    }



}
