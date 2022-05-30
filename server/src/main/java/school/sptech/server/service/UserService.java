package school.sptech.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.sptech.server.model.Category;
import school.sptech.server.model.User;
import school.sptech.server.repository.ServiceRepository;
import school.sptech.server.repository.UserRepository;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository dbUserRepository;
    @Autowired
    ServiceRepository dbRepositoryService;

    public UserService() {

    }

    public void saveUser(User user) {
        dbUserRepository.save(user);
    }

    public boolean existsById(Integer id) {
        return dbUserRepository.existsById(id);
    }

    public List<User> getAllCustomer() {
        return dbUserRepository
                .findAll()
                .stream()
                .filter(user -> user.getType().equals("customer"))
                .collect(Collectors.toList());

    }

    public String encriptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes("UTF-8"));
        return new BigInteger(1, messageDigest.digest()).toString(16);
    }

    public User login(String email, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        password = encriptPassword(password);

        return dbUserRepository.findByEmailAndPassword(email, password);

    }
    public boolean existsByEmail(String email) {

        return dbUserRepository.existsByEmail(email);

    }

    public List<User> getAllWorkers() {
        return dbUserRepository
                .findAll()
                .stream()
                .filter(user -> user.getType().equals("worker"))
                .collect(Collectors.toList());

    }

    public List<User> getAll() {
        return dbUserRepository.findAll();
    }

    public Optional<User> findById(Integer id) {
        if (dbUserRepository.findById(id).isPresent()) {
            return dbUserRepository.findById(id);
        }
        return Optional.empty();
    }

    public List<Category> getWorkerCategories(Integer id) {
        List<Category> categories = dbRepositoryService.findDistinctCategoryByWorkerId(id);

        return categories;
    }
}
