package school.sptech.server;

public class ListObj<T>{

    private T[] array;
    private int elemNumber;

    public ListObj(int size) {
        array = (T[]) new Object[size];
        elemNumber = 0;
    }

    public void add(T element) {
        if (elemNumber >= array.length) {
            System.out.println("Lista está cheia");
        }
        else {
            array[elemNumber++] = element;
        }
    }

    public void show() {
        if (elemNumber == 0) {
            System.out.println("\nA lista está vazia.");
        }
        else {
            for (int i = 0; i < elemNumber; i++) {
                System.out.println(array[i]);
            }
            System.out.println();
        }
    }

    public int search(T element) {
        for (int i = 0; i < elemNumber; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removeByIndex (int index) {
        if (index < 0 || index >= elemNumber) {
            System.out.println("\nÍndice inválido!");
            return false;
        }
        for (int i = index; i < elemNumber - 1; i++) {
            array[i] = array[i+1];
        }
        elemNumber--;          // decrementa nroElem
        return true;
    }

    public boolean removeElement(T element) {
        return removeByIndex(search(element));
    }

    public int getSize(){
        return elemNumber;
    }

    public T getElement(int index){
        if (index < 0 || index >= elemNumber){
            return null;
        } else {
            return array[index];
        }
    }

    public void clear(){
        elemNumber = 0;
    }

}

