package school.sptech.server.service;

public class HashTable {

    private PilhaLigada[] tab;

    public HashTable(int entrada) {
        tab = new PilhaLigada[entrada];
        for (int i=0; i < tab.length; i++){
            tab[i] = new PilhaLigada();
        }
    }

    public int funcaoHash(int valor) {
        return valor % tab.length;
    }

    public void insere(int valor, String chave){
        tab[funcaoHash(valor)].push(chave);
    }

    public boolean busca(int valor, String chave){
        if(tab[funcaoHash(valor)].buscaNode(chave) != null){
            return true;
        } else {
            return false;
        }
    }

//    public boolean remove(int valor){
//        if(tab[funcaoHash(valor)].removeNode(valor) == true){
//            return true;
//        } else {
//            return false;
//        }
//    }

    public void exibe() {
        for (int i = 0; i < tab.length; i++) {
            tab[i].exibe();
        }
    }
}
