package school.sptech.server.csv;

import school.sptech.server.model.User;
import school.sptech.server.service.ListObj;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserWorkerCsv {

    public static void recordCsvFile(ListObj<User> list, String fileName) {
        FileWriter file = null;
        Formatter output = null;
        Boolean doesntWork = false;
        fileName += ".csv";

        try {
            file = new FileWriter(fileName);
            output = new Formatter(file);
        } catch (IOException error) {
            System.out.println("Erro ao abrir o arquivo!");
            System.exit(1);
        }

        try {
            for (int i = 0; i < list.getSize(); i++) {
                User user = list.getElement(i);
                output.format("%d;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        user.getId(), user.getName(), user.getEmail(),
                        user.getPassword(), user.getCpf(), user.getToken(),
                        user.getType(), user.getPicture(), user.getCep());
                // public UserWorker(Integer id, String name, String email, String password,
                // String cpf, String token,
                // String type, String picture, String cep) {
                // super(id, name, email, password, cpf, token, type, picture, cep);
            }
        } catch (FormatterClosedException error) {
            System.out.println("Erro ao gravar arquivo!");
            doesntWork = true;
        } finally {
            output.close();
            try {
                file.close();
            } catch (IOException error) {
                System.out.println("Erro ao fechar o arquivo!");
                doesntWork = true;
            }
            if (doesntWork) {
                System.exit(1);
            }
        }
    }

    public static void readCsvFile(String fileName) {
        FileReader file = null;
        Scanner input = null;
        Boolean doesntWork = false;
        fileName += ".csv";

        try {
            file = new FileReader(fileName);
            input = new Scanner(file).useDelimiter(";|\\n");
        } catch (FileNotFoundException error) {
            System.out.println("Arquivo não encontrado");
            System.exit(1);
        }

        try {
            System.out.printf("%5s %-14s %-20s %-14s %-14s %-14s %-10s %-14s %-14s\n",
                    "ID", "NOME", "EMAIL", "SENHA", "CPF", "TOKEN", "TIPO", "FOTO", "CEP");
            while (input.hasNext()) {
                int id = input.nextInt(); // Não pode ser nextLine() aqui
                String name = input.next();
                String email = input.next();
                String password = input.next();
                String cpf = input.next();
                String token = input.next();
                String type = input.next();
                String picture = input.next();
                String cep = input.next();
                System.out.printf("%05d %-14s %-20s %-14s %-14s %-14s %-10s %-14s %-14s\n",
                        id, name, email, password, cpf, token, type, picture, cep);
            }
        } catch (NoSuchElementException error) {
            System.out.println("Arquivo com problemas");
        } catch (IllegalStateException error) {
            System.out.println("Erro na leitura do arquivo");
        } finally {
            input.close();
            try {
                file.close();
            } catch (IOException error) {
                System.out.println("Erro ao abrir o arquivo!");
                doesntWork = true;
            }
            if (doesntWork) {
                System.exit(1);
            }
        }
    }
}
