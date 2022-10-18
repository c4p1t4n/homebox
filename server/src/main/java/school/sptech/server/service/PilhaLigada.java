package school.sptech.server.service;

public class PilhaLigada {

    // Atributos
    private Node head;
    private int topo;

    // Construtor
    public PilhaLigada() {
        head = new Node(" ");
        topo = -1;
    }

    // Métodos

    // Método para verificar se a pilha está vazia
    public boolean isEmpty() {
        if (topo == -1) {
            return true;
        } else {
            return false;
        }
    }

    /* Método push - recebe o valor que será o info do novo nó
       Insere o novo nó logo após o head
     */
    public void push(String valor) {
        Node novo = new Node(valor);
        novo.setNext(head.getNext());
        head.setNext(novo);
        topo++;
    }

//    public Node peek() {
//        if (isEmpty()) {
//            return null;
//        }
//        return head.;
//    }

    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return head.getNext().getInfo();
    }

    /* Método exibe - percorre a lista, exibindo os infos dos nós
     */
    public void exibe()
    {
        Node atual = head.getNext();
        while (atual != null) {
            System.out.println(atual.getInfo());
            atual = atual.getNext();
        }
    }

    public void setTopo(int topo) {
        this.topo = topo;
    }

    /* Método buscaNode - recebe o valor que será pesquisado na lista
       Retorna o endereço do nó, caso encontre o valor ou null, caso não encontre
     */
    public String buscaNode(String valor) {
        Node atual = head.getNext();
        while (atual != null) {
            if (atual.getInfo().equalsIgnoreCase(valor)) {
                return atual.getInfo();
            }
            atual = atual.getNext();
        }
        return null;
    }

    /* Método removeNode - recebe o valor que será removido da lista
       Retorna true se conseguiu remover ou false caso contrário
     */
    public Boolean pop(String valor) {
        Node ant = head;
        Node atual = head.getNext();
        while (atual != null) {
            if (atual.getInfo().equalsIgnoreCase(valor)) {
                ant.setNext(atual.getNext());
                return true;
            }
            ant = atual;
            atual = atual.getNext();
        }
        return false;
    }

    /* Método getTamanho - percorre a lista, calculando o seu tamanho */
    public int getTamanho() {
        Node atual = head.getNext();
        int tam = 0;
        while (atual != null) {
            tam++;
            atual = atual.getNext();
        }
        return tam;
    }

}
