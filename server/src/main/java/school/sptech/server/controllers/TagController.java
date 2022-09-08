package school.sptech.server.controllers;

import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.*;
import school.sptech.server.repository.*;
import school.sptech.server.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
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




    @PostMapping("/{id}")
    public ResponseEntity postTag(@PathVariable  Integer id,@RequestBody String descricao){

        tagRepository.save(new Tag(id,descricao));

        return ResponseEntity.status(201).build();

    }






    @PostMapping("/user/{idUser}")
    public ResponseEntity postUserHasTag(@RequestBody Tag tag ,@PathVariable Integer idUser){
        List<Tag> listTag = tagRepository.findAll();

        if(!dbServiceUser.existsById(idUser)){
            return ResponseEntity.status(400).body("Usuario não existente");
        }

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
    public ResponseEntity postCategoryHasTag(@RequestBody Tag tag,@PathVariable Integer idCategory){
        List<Tag> listTag= tagRepository.findAll();

        if(!categoryRepository.existsById(idCategory)){
            return ResponseEntity.status(400).body("Usuario não existente");
        }

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
    public ResponseEntity postServiceHasTag(@RequestBody Tag tag,@PathVariable  Integer idService){
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
    public ResponseEntity getAllTags(){
      List<Tag> tags = tagRepository.findAll();

        return tags.isEmpty() ? status(204).build() : status(200).body(tags);
    }

    @GetMapping("/user")
    public ResponseEntity getAllUserHasTags(){
        List<UserHasTag> userHasTags = userHasTag.findAll();

        return userHasTags.isEmpty() ? status(204).build() : status(200).body(userHasTags);
    }


    @GetMapping("/service")
    public ResponseEntity getAllServiceHasTags(){
        List<ServiceHasTag> serviceHasTags = serviceHasTagRepository.findAll();

        return serviceHasTags.isEmpty() ? status(204).build() : status(200).body(serviceHasTags);
    }

    @GetMapping("/category")
    public ResponseEntity getAllCategoryHasTags(){
        List<CategoryHasTag> categoryHasTags = categoryHasTagRepository.findAll();

        return categoryHasTags.isEmpty() ? status(204).build() : status(200).body(categoryHasTags);
    }





}
