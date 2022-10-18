package school.sptech.server.service;

public class TestePilhaLigada {

    public static void main(String[] args) {
        PilhaLigada pilhaLigada1 = new PilhaLigada();

        System.out.println("Acrescentando dois valores a pilha e exibindo");
        pilhaLigada1.push("pintura");
        pilhaLigada1.push("limpeza");
        pilhaLigada1.exibe();

        System.out.println("");
        System.out.println("Exibindo se a pilha está vazia");
        System.out.println(pilhaLigada1.isEmpty());
        System.out.println("Exibindo todos os valores que existem na pilha");
        pilhaLigada1.exibe();

        System.out.println("");
        System.out.println("Exibindo o topo:");
        System.out.println(pilhaLigada1.peek());

        System.out.println("");
        System.out.println("Tentando excluir um valor que não existe da pilha");
        System.out.println(pilhaLigada1.pop("teste"));

        System.out.println("");
        System.out.println("Excluindo um valor que existe na pilha e exibindo novamente todos os valores");
        System.out.println(pilhaLigada1.pop("limpeza"));
        pilhaLigada1.exibe();

    }
}
