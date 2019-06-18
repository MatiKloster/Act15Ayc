package business;

public class Arco {
    private int nodo1;
    private int nodo2;

    public Arco(int i, int j) {
        this.nodo1 = i;
        this.nodo2 = j;
    }

    public int getNodo1() {
        return nodo1;
    }

    public int getNodo2() {
        return nodo2;
    }
}
