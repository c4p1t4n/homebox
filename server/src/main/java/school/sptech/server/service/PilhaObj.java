package school.sptech.server.service;

public class PilhaObj<T> {

    private T[] pilha;
    private int topo;

    public T[] getPilha() {
        return pilha;
    }

    public void setPilha(T[] pilha) {
        this.pilha = pilha;
    }

    public int getTopo() {
        return topo;
    }

    public void setTopo(int topo) {
        this.topo = topo;
    }

    public PilhaObj(int capacidade) {
        this.topo = -1;
        this.pilha = (T[]) new Object[capacidade];
    }

    public Boolean isEmpty() {
        return topo == -1;
    }

    public Boolean isFull() {
        return pilha.length - 1 == topo;
    }

    public void push(T info) {
        if (!isFull()) {

            pilha[++topo] = info;
        } else {

            for (int i = 0; i < pilha.length-1; i++) {
                pilha[i] = pilha[i+1];
            }
            pilha[topo] = info;
        }
    }

    public T pop() {
        if (!isEmpty()) {
            return pilha[topo--];

        }

        return null;
    }

    public T peek() {
        if (!isEmpty()) {
            return pilha[topo];

        }
        return null;

    }

    public void multipop(int k) {
        while (topo != -1) {
            for (int i = 0; i <= k; i++) {
                this.pop();
            }
        }
    }

    public void exibe() {
        if (!isEmpty()) {
            for (int i = 0; i < pilha.length; i++) {
                System.out.println(pilha[i]);
            }
        }
    }
}