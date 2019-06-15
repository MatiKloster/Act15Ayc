public class NodoDisjoint {
    private int elemento;
    private NodoDisjoint padre;
    private int rank;


    public NodoDisjoint(int elemento, NodoDisjoint padre, int rank){
        this.elemento = elemento;
        this.padre = padre;
        this.rank = rank;
    }

    public void setPadre(NodoDisjoint padre){
        this.padre = padre;
    }

    public NodoDisjoint getPadre(){
        return padre;
    }

    public int getElemento(){
        return elemento;
    }


    public int getRank() {
        return rank;
    }

    public void setRank(){
        rank++;
    }

}
