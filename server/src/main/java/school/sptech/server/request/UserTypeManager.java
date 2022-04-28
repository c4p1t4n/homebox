package school.sptech.server.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.server.model.User;
import school.sptech.server.model.UserCustomer;
import school.sptech.server.repository.UserCustomerRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserTypeManager  implements  Iterator{
    private List<User> users;
    private  int sizeList;

    private int currentPosition;


    @Autowired
    UserCustomerRepository dbRepositoryCustomer;

    public UserTypeManager() {
        this.users = new ArrayList<>();
        this.sizeList = 0;
        this.currentPosition = 0;


    }




    @Override
    public  boolean hasNext(){
        System.out.println(currentPosition<sizeList);
        return  currentPosition < sizeList ;
    }

    @Override
    public User getNext() {
        if(hasNext()){
            System.out.println("tem proximo");
            User user = users.get(currentPosition+1);
            System.out.println("Usuario no getNext" + user);
            System.out.println("Current Position" + currentPosition);
            currentPosition+=1;
            System.out.println("Current Position"+ currentPosition);
            return user;
        }

        return  null;

    }

    public  User getCurrent(){
        return users.get(currentPosition);
    }

    @Override
    public void ResetList(){
        currentPosition =0;
        sizeList = 0;
        users.clear();
    }

    public void addUser(User user){
        if(currentPosition>=sizeList){
            users.set(sizeList,user);
            return;
        }else{
            users.add(user);
        }

        sizeList++;
    }


 public void addAllUsers(List<UserCustomer> users){

        this.users.addAll(dbRepositoryCustomer.findAll());
        sizeList += dbRepositoryCustomer.findAll().size() -1;
    }



   public List<User> getAllCustomer(){
        List<User> allCustomers = new ArrayList<>();

        while (hasNext()){
            if(getNext().getType().equals("customer")){

                allCustomers.add(getCurrent());
                System.out.println("AllCustomers" + allCustomers);
            }
        }


        return  allCustomers;


   }

   public  List<User> getAllWorkers(){
       List<User> allWorkers = new ArrayList<>();
       for (int i = 0; i < users.size()-1; i++) {
           if(i==0){
               if(getCurrent().getType().equals("worker")){
                   allWorkers.add(getCurrent());
               }
           }
           if(getNext() !=null){
               if(getCurrent().getType().equals("worker")){
                   allWorkers.add(getCurrent());
               };

           }else{

           }
       }

       return  allWorkers;


   }

    public  List<User> getAllUsers(){
        List<User> allUsers = new ArrayList<>();
        for(User user:users){
           allUsers.add(user);
        }

        return  allUsers;


    }


}

