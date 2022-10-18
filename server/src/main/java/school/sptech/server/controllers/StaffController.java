package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.Staff;
import school.sptech.server.model.User;
import school.sptech.server.repository.*;
import school.sptech.server.request.LoginRequest;
import school.sptech.server.service.UserService;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private SchedulingRepository dbRepositoryScheduling;
    @Autowired
    private SchedulingStatusRepository dbRepositorySchedulingStatus;
    @Autowired
    private StaffRepository dbRepositoryStaff;
    @Autowired
    private UserService dbServiceUser;


    @Autowired
    private ChatRepository dbRepositoryChat;
    @Autowired
    private RatingRepository dbRepositoryRating;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping()
    public ResponseEntity<List<Staff>> getStaff() {
        List<Staff> users = dbRepositoryStaff.findAll();
        return !users.isEmpty() ? ResponseEntity.status(200).body(users) : ResponseEntity.status(204).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping()
    public ResponseEntity<Object> cadastrarStaff(@RequestBody Staff newStaff) {

        try {
            dbRepositoryStaff.save(newStaff);

            return ResponseEntity.status(200).build();
        } catch (NullPointerException npe) {
            return ResponseEntity.status(400).build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<Staff> login(@RequestBody LoginRequest loginCredentials)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        Staff staff = dbServiceUser.staffLogin(loginCredentials.getEmail(), loginCredentials.getPassword());

        if (staff != null)
            return ResponseEntity.status(200).body(staff);

        return ResponseEntity.status(404).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/get-count-last-seven-days")
    public ResponseEntity<List<List<Integer>>> getCountLastSevenDaysDone() {
        List<Integer> countSchedulingDone =  dbRepositoryScheduling.getCountSchedulingLastSevenDaysDone(LocalDate.now().minusDays(7));
        List<Integer> countScheduling =  dbRepositoryScheduling.getCountSchedulingLastSevenDays(LocalDate.now().minusDays(7));
        ArrayList<List<Integer>> arr = new ArrayList<>();
        arr.add(countSchedulingDone);
        arr.add(countScheduling);
        return ResponseEntity.status(200).body(arr);
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/get-scheduling-last-seven-days")
    public ResponseEntity<List<List<Date>>> getSchedulingLastSevenDays() {
        List<Date> schedulingsDone =  dbRepositoryScheduling.getSchedulingLastSevenDaysDone(LocalDate.now().minusDays(7));
        List<Date> schedulings =  dbRepositoryScheduling.getSchedulingLastSevenDays(LocalDate.now().minusDays(7));
        ArrayList<List<Date>> arr = new ArrayList<>();
        arr.add(schedulingsDone);
        arr.add(schedulings);
        return ResponseEntity.status(200).body(arr);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/ratio-worker-customer")
    public ResponseEntity<List<Integer>> getSchedulingVsChats() {
        ArrayList<User> customers = (ArrayList<User>) dbServiceUser.getAllCustomer();
        ArrayList<User> workers = (ArrayList<User>) dbServiceUser.getAllWorkers();
        ArrayList<Integer> arrayCount = new ArrayList<>();
        System.out.println(workers.size());
        arrayCount.add(workers.size());
        arrayCount.add(customers.size());
        return ResponseEntity.status(200).body(arrayCount);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/avg-rating-worker")
    public ResponseEntity<Double> getAvgWorks() {
        return ResponseEntity.status(200).body(dbRepositoryRating.getAvgRatingWorker());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/scheduling-chat")
    public ResponseEntity<List<Integer>> getChatsPerScheduling() {
        Integer scheduling =dbRepositoryScheduling.getCountScheduling(LocalDate.now().minusDays(360));
        Integer chats =dbRepositoryChat.getCountChats(LocalDate.now().minusDays(360));

        ArrayList<Integer> arrayCount = new ArrayList<>();

        arrayCount.add(scheduling);
        arrayCount.add(chats);

        return ResponseEntity.status(200).body(arrayCount);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/ceps")
    public ResponseEntity<List<String>> getCep() {
        List<String> scheduling =dbRepositoryScheduling.getCepGroup();

        return ResponseEntity.status(200).body(scheduling);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/count-ceps")
    public ResponseEntity<List<Integer>> getCountCep() {
        List<Integer> scheduling =dbRepositoryScheduling.getCountIntegerGroupCep();
        return ResponseEntity.status(200).body(scheduling);
    }




}
