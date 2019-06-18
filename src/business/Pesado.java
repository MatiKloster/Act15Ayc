package business;

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


}