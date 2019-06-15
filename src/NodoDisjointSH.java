public class NodoDisjointSH {
    private int elemento;
    private NodoDisjoint padre;


    public NodoDisjointSH(int elemento, NodoDisjoint padre, int rank){
        this.elemento = elemento;
        this.padre = padre;
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

}
