package TDAColaPrioridad;

public class Pesado {
    private Arco arco;
    private int peso;

    public Pesado(int i, int j, int peso) {
        this.arco = new Arco(i, j);
        this.peso = peso;
    }

    public int getPeso(){
        return peso;
    }

    public Arco getArco(){
        return arco;
    }

    private class Arco {
        private int nodo1;
        private int nodo2;

        public Arco(int i, int j) {
            this.nodo1 = i;
            this.nodo2 = j;
        }
    }
}