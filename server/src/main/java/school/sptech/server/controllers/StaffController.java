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

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private SchedulingRepository dbRepositoryScheduling;
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


    private List<Integer> getRegions(List<String> list_ceps,List<Integer> valores_do_cep){
        Integer centro = 0;
        Integer zona_norte = 0;
        Integer zona_leste = 0;;
        Integer zona_sul = 0;
        Integer zona_oeste = 0;
        List<Integer> list_count_per_region= new ArrayList<>();
        System.out.println(list_ceps);
        System.out.println(valores_do_cep);
        for (Integer i = 0; i < list_ceps.size(); i++) {
               if(Integer.parseInt(list_ceps.get(i).substring(0, 4)) >= 1000 && Integer.parseInt(list_ceps.get(i).substring(0, 4)) < 2000){
                    centro+= valores_do_cep.get(i);
               }else if(Integer.parseInt(list_ceps.get(i).substring(0, 4)) >= 2000 && Integer.parseInt(list_ceps.get(i).substring(0, 4)) < 3000){
                    zona_norte+= valores_do_cep.get(i);
               }else if((Integer.parseInt(list_ceps.get(i).substring(0, 4)) >= 3000 && Integer.parseInt(list_ceps.get(i).substring(0, 4)) < 4000)
                        || (Integer.parseInt(list_ceps.get(i).substring(0, 4)) >= 8000 && Integer.parseInt(list_ceps.get(i).substring(0, 4)) < 8500)){
                    zona_leste+= valores_do_cep.get(i);
               }else if(Integer.parseInt(list_ceps.get(i).substring(0, 4)) >= 5000 && Integer.parseInt(list_ceps.get(i).substring(0, 4)) < 5900){
                    zona_oeste+= valores_do_cep.get(i);
               }else if(Integer.parseInt(list_ceps.get(i).substring(0, 4)) >= 4000 && Integer.parseInt(list_ceps.get(i).substring(0, 4)) < 5000){
                    zona_sul+= valores_do_cep.get(i);
               }else{
                   centro+= valores_do_cep.get(i);
               }
        }
        list_count_per_region.add(centro);
        list_count_per_region.add(zona_sul);
        list_count_per_region.add(zona_norte);
        list_count_per_region.add(zona_leste);
        list_count_per_region.add(zona_oeste);
        return list_count_per_region;
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/ceps")
    public ResponseEntity<List<Integer>> getCeps(){
        List<Integer> countIntegers =dbRepositoryScheduling.getCountIntegerGroupCep();
        List<String> getCeps =dbRepositoryScheduling.getCepGroup();
        List<Integer> final_list = getRegions(getCeps,countIntegers);
        return ResponseEntity.status(200).body(final_list);
    }









}
