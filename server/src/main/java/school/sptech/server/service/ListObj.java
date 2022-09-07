package school.sptech.server.service;

import school.sptech.server.model.User;

@SuppressWarnings("unchecked")
public class ListObj<T> {

    private T[] array;
    private int elemNumber;

    public ListObj(int size) {
        array = (T[]) new Object[size];
        elemNumber = 0;
    }

    public void add(T element) {
        if (elemNumber >= array.length) {
            System.out.println("Lista está cheia");
        } else {
            array[elemNumber++] = element;
        }
    }

    public void show() {
        if (elemNumber == 0) {
            System.out.println("\nA lista está vazia.");
        } else {
            for (int i = 0; i < elemNumber; i++) {
                System.out.println(array[i]);
            }
            System.out.println();
        }
    }

    public void recursiveShow(ListObj<User> v, int index) {
        if (elemNumber == 0) {
            System.out.println("\nA lista está vazia.");
        } else {
            recursiveShow2(v, index);
        }
    }

    public void recursiveShow2(ListObj<User> v, int index) {
        if (index < v.getSize()) {
            System.out.println(v.getElement(index));
            recursiveShow(v, index + 1);
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

    public boolean removeByIndex(int index) {
        if (index < 0 || index >= elemNumber) {
            System.out.println("\nÍndice inválido!");
            return false;
        }
        for (int i = index; i < elemNumber - 1; i++) {
            array[i] = array[i + 1];
        }
        elemNumber--; // decrementa nroElem
        return true;
    }

    public boolean removeElement(T element) {
        return removeByIndex(search(element));
    }

    public int getSize() {
        return elemNumber;
    }

    public T getElement(int index) {
        if (index < 0 || index >= elemNumber) {
            return null;
        } else {
            return array[index];
        }
    }

    public void clear() {
        elemNumber = 0;
    }

    public void overwriteObj(int index, T element) {
        array[index] = element;
    }

    public static void bubbleSort(ListObj<User> v) {

        User aux;

        for (int i = 0; i < v.getSize() - 1; i++) {
            for (int j = 1; j < v.getSize() - i; j++) {
                if (v.getElement(j - 1).getId() > v.getElement(j).getId()) {
                    aux = v.getElement(j);
                    v.overwriteObj(j, v.getElement(j - 1));
                    v.overwriteObj(j - 1, aux);
                }
            }
        }
    }

}
