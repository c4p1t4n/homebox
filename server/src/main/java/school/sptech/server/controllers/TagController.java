package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.*;
import school.sptech.server.repository.*;
import school.sptech.server.service.UserService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController @CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tag")
public class TagController {

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
    UserHasTagRepository userHasTag;

    @Autowired
    ServiceHasTagRepository serviceHasTagRepository;

    @Autowired
    CategoryHasTagRepository categoryHasTagRepository;


    @Autowired
    InterestAccessRepository interestAccessRepository;


    @PostMapping("/user/{idUser}")
    public ResponseEntity<String> postUserHasTag(@RequestBody Tag tag ,@PathVariable Integer idUser){
        if(!dbServiceUser.existsById(idUser)){
            return ResponseEntity.status(400).body("Usuario não existente");
        }

        List<Tag> listTag = tagRepository.findAll();

        for(Tag actual_tag:listTag){
            if(actual_tag.getValue().equals(tag.getValue())){
                tag = actual_tag;
                userHasTag.save(new UserHasTag(userRepository.getById(idUser),tag));
                return ResponseEntity.status(201).build();
            }
        }

        tagRepository.save(tag);
        userHasTag.save(new UserHasTag(userRepository.getById(idUser),tag));
        return ResponseEntity.status(201).build();

    }

    @PostMapping("/category/{idCategory}")
    public ResponseEntity<String> postCategoryHasTag(@RequestBody Tag tag,@PathVariable Integer idCategory){

        if(!categoryRepository.existsById(idCategory)){
            return ResponseEntity.status(400).body("Usuario não existente");
        }

        List<Tag> listTag= tagRepository.findAll();

        for(Tag actual_tag:listTag){
            if(actual_tag.getValue().equals(tag.getValue())){
                tag = actual_tag;
                categoryHasTagRepository.save(new CategoryHasTag(categoryRepository.getById(idCategory),tag));
                return ResponseEntity.status(201).build();
            }
        }

        tagRepository.save(tag);
        categoryHasTagRepository.save(new CategoryHasTag(categoryRepository.getById(idCategory),tag));
        return ResponseEntity.status(201).build();

    }


    @PostMapping("/service/{idService}")
    public ResponseEntity<String> postServiceHasTag(@RequestBody Tag tag,@PathVariable  Integer idService){
        List<Tag> listTag= tagRepository.findAll();

        if(!serviceRepository.existsById(idService)){
            return ResponseEntity.status(400).body("Usuario não existente");
        }

        for(Tag actual_tag:listTag){
            if(actual_tag.getValue().equals(tag.getValue())){
                tag = actual_tag;
                serviceHasTagRepository.save(new ServiceHasTag(idService,serviceRepository.getById(idService),tag));
                return ResponseEntity.status(201).build();
            }
        }

        tagRepository.save(tag);
        serviceHasTagRepository.save(new ServiceHasTag(serviceRepository.getById(idService),tag));
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags(){
      List<Tag> tags = tagRepository.findAll();

        return tags.isEmpty() ? status(204).build() : status(200).body(tags);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserHasTag>> getAllUserHasTags(){
        List<UserHasTag> userHasTags = userHasTag.findAll();

        return userHasTags.isEmpty() ? status(204).build() : status(200).body(userHasTags);
    }


    @GetMapping("/service")
    public ResponseEntity<List<ServiceHasTag>> getAllServiceHasTags(){
        List<ServiceHasTag> serviceHasTags = serviceHasTagRepository.findAll();

        return serviceHasTags.isEmpty() ? status(204).build() : status(200).body(serviceHasTags);
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryHasTag>> getAllCategoryHasTags(){
        List<CategoryHasTag> categoryHasTags = categoryHasTagRepository.findAll();

        return categoryHasTags.isEmpty() ? status(204).build() : status(200).body(categoryHasTags);
    }
}
