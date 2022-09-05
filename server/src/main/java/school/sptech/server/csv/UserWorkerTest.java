package school.sptech.server.csv;

import school.sptech.server.model.User;
import school.sptech.server.service.ListObj;

public class UserWorkerTest {

    public static void main(String[] args) {

        ListObj<User> userWorkerList = new ListObj<User>(5);
        User user1 = new User(1, "José Silva", "jose@gmail.com", "senhaExemplo1",
                "00011122233", "tokenExemplo1", "worker", "foto.png",
                "1234567");
        User user2 = new User(2, "Lara Alves", "lara@gmail.com", "senhaExemplo2",
                "00011122244", "tokenExemplo2", "worker", "foto.png",
                "1234577");
        User user3 = new User(3, "Felipe Abreu", "felipe@gmail.com", "senhaExemplo3",
                "00011122255", "tokenExemplo3", "worker", "foto.png",
                "1234588");

        userWorkerList.add(user1);
        userWorkerList.add(user3);
        userWorkerList.add(user2);

        // System.out.printf("%5s %-14s %-20s %-14s %-14s %-14s %-10s %-14s %-14s\n",
        // "ID", "NOME", "EMAIL", "SENHA", "CPF", "TOKEN", "TIPO", "FOTO", "CEP");
        // userWorkerList.show();

        UserWorkerCsv.recordCsvFile(userWorkerList, "userWorker");
        UserWorkerCsv.readCsvFile("userWorker");

        System.out.println("--- Exibindo antes do bubble sort ---");
        userWorkerList.show();

        ListObj.bubbleSort(userWorkerList);

        UserWorkerCsv.recordCsvFile(userWorkerList, "userWorker");
        UserWorkerCsv.readCsvFile("userWorker");

        System.out.println();
        System.out.println("--- Exibindo após o bubble sort ---");
        userWorkerList.show();

        System.out.println("--- Exibindo com o método recursivo ---");
        userWorkerList.recursiveShow(userWorkerList, 0);
    }

}
