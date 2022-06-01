package school.sptech.server.service;

public class FilaObj<T> {

    private int tamanho;
    private T[] fila;

    public FilaObj(int capacidade) {
        this.fila = (T[]) new Object[capacidade];
        this.tamanho = 0;
    }
    public boolean isEmpty() {
        if(tamanho ==0){
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if(tamanho==fila.length){
            return  true;
        }

        return false;
    }

    public void insert(T info) {
        if(!isFull()){
            fila[tamanho] = info;
            tamanho++;
            return;
        }
        throw new IllegalStateException();

    }
    public T peek() {
        return fila[0];
    }

    public T poll() {
        T primeiro_elemento = fila[0];
        if(!isEmpty()) {
            for (int i = 0; i < fila.length - 1; i++) {
                fila[i] = fila[i + 1];
            }
            fila[--tamanho] = null;
        }
        return  primeiro_elemento;
    }

    public void exibe() {
        for(T element: fila){
            System.out.println(element);
        }
    }

    public T[] getFila() {
        return fila;
    }
}
