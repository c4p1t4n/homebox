package school.sptech.server.csv;

import school.sptech.server.model.UserWorker;
import school.sptech.server.service.ListObj;

public class UserWorkerTest {

    public static void main(String[] args) {

        ListObj<UserWorker> userWorkerList = new ListObj(5);
        UserWorker user1 = new UserWorker(1, "José Silva", "jose@gmail.com", "senhaExemplo1",
                                          "00011122233", "tokenExemplo1", "worker", "foto.png",
                                          "1234567");
        UserWorker user2 = new UserWorker(2, "Lara Alves", "lara@gmail.com", "senhaExemplo2",
                                          "00011122244", "tokenExemplo2", "worker", "foto.png",
                                          "1234577");
        UserWorker user3 = new UserWorker(3, "Felipe Abreu", "felipe@gmail.com", "senhaExemplo3",
                                          "00011122255", "tokenExemplo3", "worker", "foto.png",
                                          "1234588");

        userWorkerList.add(user1);
        userWorkerList.add(user3);
        userWorkerList.add(user2);

//        System.out.printf("%5s %-14s %-20s %-14s %-14s %-14s %-10s %-14s %-14s\n",
//                          "ID", "NOME", "EMAIL", "SENHA", "CPF", "TOKEN", "TIPO", "FOTO", "CEP");
//        userWorkerList.show();

        UserWorkerCsv.recordCsvFile(userWorkerList, "userWorker");
        UserWorkerCsv.readCsvFile("userWorker");

        System.out.println("--- Exibindo antes do bubble sort ---");
        userWorkerList.show();

        bubbleSort(userWorkerList);

        System.out.println();
        System.out.println("--- Exibindo após o bubble sort ---");
        userWorkerList.show();
    }

    public static void bubbleSort(ListObj<UserWorker> v) {

        int aux = 0;

        for (int i = 0; i < v.getSize() - 1; i++){
            for (int j = 1; j < v.getSize() - i; j++){
                if (v.getElement(j-1).getId() > v.getElement(j).getId()){
                    aux = v.getElement(j).getId();
                    v.getElement(j).setId(v.getElement(j-1).getId());
                    v.getElement(j-1).setId(aux);
                }
            }
        }

    }
}
