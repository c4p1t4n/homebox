package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.InterestAccess;
import school.sptech.server.model.Tag;
import school.sptech.server.model.User;
import school.sptech.server.repository.*;
import school.sptech.server.service.UserService;

import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/interestAcess")
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

    @PostMapping("/interestAcess/{user_id}/{tag_id}")
    public ResponseEntity postInterestAcess(@PathVariable Integer user_id, @PathVariable Integer tag_id){

        if(userRepository.existsById(user_id) && tagRepository.existsById(tag_id)) {
            User userInterestAcess = userRepository.getById(user_id);
            Tag tagInterestAcess = tagRepository.getById(tag_id);
            interestAccessRepository.save(new InterestAccess(userInterestAcess, tagInterestAcess, LocalDate.now()));
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).body("Usuario ou Tag não existente");
    }

    @PostMapping("/user/{idInterestAcess}")
    public ResponseEntity postInterestAcess(@RequestBody Tag tag ,@PathVariable Integer idInterestAcess){
        List<Tag> listTag = tagRepository.findAll();

        if(!interestAccessRepository.existsById(idInterestAcess)){
            return ResponseEntity.status(400).body("Usuario não existente");
        }
        InterestAccess actualInterestAcess = interestAccessRepository.getById(idInterestAcess);

        for(Tag actual_tag:listTag){
            if(actual_tag.getValue().equals(tag.getValue())){
                tag = actual_tag;


                interestAccessRepository.save(new InterestAccess(actualInterestAcess.getId(),actualInterestAcess.getUser(),tag,actualInterestAcess.getAccessDate()));
                return ResponseEntity.status(201).build();
            }
        }

        tagRepository.save(tag);
        interestAccessRepository.save(new InterestAccess(actualInterestAcess.getId(),actualInterestAcess.getUser(),tag,actualInterestAcess.getAccessDate()));
        return ResponseEntity.status(201).build();

    }

    @GetMapping("/avg_last_seven_days/{id_user}")
    public  ResponseEntity AvgLastSevenDays(@PathVariable Integer id_user){
       return ResponseEntity.status(200).body(interestAccessRepository.countIntestAcess(id_user,LocalDate.now().minusDays(7)));
    }

    @GetMapping("/getListAvgLastSevenDays/{id_user}")
    public  ResponseEntity ListAvgLastSevenDays(@PathVariable Integer id_user){


        return ResponseEntity.status(200).body(ratingRepository.getAvgRatingForWorkerLastSevenDays(id_user,LocalDate.now().minusDays(7)));
    }



}
