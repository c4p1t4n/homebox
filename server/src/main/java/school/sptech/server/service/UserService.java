package school.sptech.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.server.model.User;
import school.sptech.server.model.UserCustomer;
import school.sptech.server.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository dbUserRepository;

    public UserService() {



    }
    public void saveUser(User user){
        dbUserRepository.save(user);
    }

    public boolean existsById(Integer id){
        return dbUserRepository.existsById(id);
    }









   public List<User> getAllCustomer(){
        return  dbUserRepository
                .findAll()
                .stream()
                .filter(user -> user.getType().equals("Customer"))
                .collect(Collectors.toList());


   }

   public  List<User> getAllWorkers(){



       return  dbUserRepository
               .findAll()
               .stream()
               .filter(user -> user.getType().equals("Customer"))
               .collect(Collectors.toList());

   }

    public  List<User> findAll(){

        return  dbUserRepository.findAll();
            }


}

