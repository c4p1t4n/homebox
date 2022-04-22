package school.sptech.server;

import school.sptech.server.model.Category;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CsvFile {

        public static void recordCsvFile(ListObj<Category> list, String fileName){
            FileWriter file = null;
            Formatter output = null;
            Boolean doesntWork = false;
            fileName += ".csv";

            try {
                file = new FileWriter(fileName);
                output = new Formatter(file);
            }
            catch (IOException error){
                System.out.println("Erro ao abrir o arquivo!");
                System.exit(1);
            }

            try {
                for (int i = 0; i < list.getSize(); i++) {
                    Category categ = list.getElement(i);
                    output.format("%d;%s\n", categ.getIdCategory(), categ.getName());
                }
            }
            catch (FormatterClosedException error){
                System.out.println("Erro ao gravar arquivo!");
                doesntWork = true;
            }
            finally {
                output.close();
                try {
                    file.close();
                }
                catch (IOException error){
                    System.out.println("Erro ao fechar o arquivo!");
                    doesntWork = true;
                }
                if (doesntWork){
                    System.exit(1);
                }
            }
        }

        public static void readCsvFile(String fileName){
            FileReader file = null;
            Scanner input = null;
            Boolean doesntWork = false;
            fileName += ".csv";

            try {
                file = new FileReader(fileName);
                input = new Scanner(file).useDelimiter(";|\\n");
            }
            catch (FileNotFoundException error){
                System.out.println("Arquivo não encontrado");
                System.exit(1);
            }

            try {
                System.out.printf("%3s %-14s\n", "ID", "NOME");
                while (input.hasNext()){
                    int id = input.nextInt(); // Não pode ser nextLine() aqui
                    String name = input.next();
                    System.out.printf("%03d %-14s\n", id, name);
                }
            }
            catch (NoSuchElementException error){
                System.out.println("Arquivo com problemas");
            }
            catch (IllegalStateException error){
                System.out.println("Erro na leitura do arquivo");
            }
            finally {
                input.close();
                try {
                    file.close();
                }
                catch (IOException error){
                    System.out.println("Erro ao abrir o arquivo!");
                    doesntWork = true;
                }
                if (doesntWork){
                    System.exit(1);
                }
            }
        }
}
