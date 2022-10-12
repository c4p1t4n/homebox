package school.sptech.server.service;

public class TesteHash {

    public static void main(String[] args) {

        HashTable hashTable1 = new HashTable(4);

        System.out.println("Exibindo a hashtable após inserir 3 valores (8, 10, 5)");
        hashTable1.insere(1, "oi");
        hashTable1.insere(3, "ola");
        hashTable1.exibe();

//        System.out.println();
//        System.out.println("Exibindo a hashtable após excluir 1 valor (8)");
//        hashTable1.remove(8);
//        hashTable1.exibe();
//
//        System.out.println();
//        System.out.println("Buscando um valor existente na hashtable (5)");
//        System.out.println(hashTable1.busca(5));
//
//        System.out.println();
//        System.out.println("Buscando um valor inexistente na hashtable (8)");
//        System.out.println(hashTable1.busca(8));
    }

}
