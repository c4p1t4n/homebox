package school.sptech.server.service;

public class Node {

    // Atributos
    private String info;
    private Node next;

    // Construtor
    public Node(String info) {
        this.info = info;
        next = null;
    }

    // Métodos

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
