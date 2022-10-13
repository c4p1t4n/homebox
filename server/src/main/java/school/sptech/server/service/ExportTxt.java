//package school.sptech.server.service;
//
//import school.sptech.server.model.Category;
//import school.sptech.server.model.User;
//
//import java.io.*;
//import java.math.BigInteger;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//public class ExportTxt {
//    public String encriptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//        messageDigest.update(password.getBytes("UTF-8"));
//        return new BigInteger(1, messageDigest.digest()).toString(16);
//    }
//    public static void gravaRegistro(String registro, String nomeArq) {
//        BufferedWriter saida = null;
//        // File file = new File(nomeArq);
//
//        try {
//            saida = new BufferedWriter(new FileWriter(nomeArq, true));
//        }
//        catch (IOException erro) {
//            System.out.println("Erro ao abrir o arquivo: " + erro);
//        }
//
//        try {
//            saida.append(registro + "\n");
//            saida.close();
//        }
//        catch (IOException erro) {
//            System.out.println("Erro ao gravar o arquivo: " + erro);
//        }
//    }
//
//
//    public FilaObj<Object> lerArquivoTxt(String nomeArq) {
//        BufferedReader entrada = null;
//        String registro, tipoRegistro;
//        String name,email,password,cpf,token,type,picture,cep;
//        Integer id;
//        FilaObj<Object> fila = new FilaObj<>(10);
//
//
//        try {
//            entrada = new BufferedReader(new FileReader(nomeArq));
//        }
//        catch (IOException erro) {
//            System.out.println("Erro ao abrir o arquivo: " + erro);
//        }
//
//        try {
//            registro = entrada.readLine();
//
//            while (registro != null) {
//                tipoRegistro = registro.substring(0,2);
//                if (tipoRegistro.equals("01")) {
//                    System.out.println("É um registro de header");
//
//                }
//                else if (tipoRegistro.equals("02")) {
//                    System.out.println(registro);
//                    System.out.println("É um registro de corpo");
//                    System.out.println(registro.substring(3,7));
//                    id = Integer.valueOf(registro.substring(3,7).trim());
//                    name = registro.substring(8,21).trim();
//                    email = registro.substring(21,40).trim();
//                    password = registro.substring(41,107).trim();
//                    cpf = registro.substring(107,117).trim();
//                    token = registro.substring(118,130).trim();
//                    type = registro.substring(130,138).trim();
//                    picture = registro.substring(139,298).trim();
//                    cep = registro.substring(299,309).trim();
//                    password = encriptPassword(password);
//                    fila.insert(new User(id,name,email,password,cpf,token,type,picture,cep));
//
//                }
//                else if (tipoRegistro.equals("03")) {
//                    id = Integer.valueOf(registro.substring(3,7).trim());
//                    name = registro.substring(8,21).trim();
//                    fila.insert(new Category(id,name));
//                }
//                else {
//                    System.out.println("Tipo de registro inválido!");
//                }
//
//                registro = entrada.readLine();
//            }
//
//
//
//
//
//        }
//        catch (IOException erro) {
//            System.out.println("Erro ao ler o arquivo: " + erro);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//        return fila;
//
//    }
//
//
//
//    public StringBuilder gravaArquivoTxt(List<User> lista, List<Category> listCt, String nomeArq) throws IOException {
//        File file = new File(nomeArq);
//        int contaRegCorpo = 0;
//
//        String header = "00USUÁRIOS";
//        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
//        header += "01";
//
//        gravaRegistro(header, nomeArq);
//
//        String corpo;
//        for (User usr : lista) {
//            corpo = "02";
//            corpo += String.format("%5.5s", usr.getId());
//            corpo += String.format("%14.14s", usr.getName());
//            corpo += String.format("%-20.20s", usr.getEmail());
//            corpo += String.format("%64.64s", usr.getPassword());
//            corpo += String.format("%11.11s", usr.getCpf());
//            corpo += String.format("%14.14s", usr.getToken());
//            corpo += String.format("%8.8s", usr.getType());
//            corpo += String.format("%160.160s", usr.getPicture());
//            corpo += String.format("%11.11s", usr.getCep());
//
//
//            contaRegCorpo++;
//            gravaRegistro(corpo, nomeArq);
//        }
//        for (Category usr : listCt) {
//            corpo = "03";
//            corpo += String.format("%5.3s", usr.getIdCategory());
//            corpo += String.format("%30.30s", usr.getName());
//
//            contaRegCorpo++;
//            gravaRegistro(corpo, nomeArq);
//        }
//
//
//
//        String trailer = "01";
//        trailer += String.format("%010d", contaRegCorpo);
//        gravaRegistro(trailer, nomeArq);
//
//        BufferedReader buffer = new BufferedReader(new FileReader(file));
//        StringBuilder teste = new StringBuilder();
//
//        String str;
//        while ((str = buffer.readLine()) != null) {
//            teste.append(str).append("\n");
//        }
//        buffer.close();
//
//       boolean value = Files.deleteIfExists(Path.of(file.getAbsolutePath()));
//        if (value)
//            System.out.println("JavaFile.java is successfully deleted.");
//        else
//            System.out.println("File doesn't exit");
//
//
//        return teste;
//
//
//
//
//    }
//}
