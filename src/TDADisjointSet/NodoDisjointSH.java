package TDADisjointSet;

public class NodoDisjointSH {
    private int elemento;
    private NodoDisjointSH padre;


    public NodoDisjointSH(int elemento, NodoDisjointSH padre, int rank){
        this.elemento = elemento;
        this.padre = padre;
    }

    public void setPadre(NodoDisjointSH padre){
        this.padre = padre;
    }

    public NodoDisjointSH getPadre(){
        return padre;
    }

    public int getElemento(){
        return elemento;
    }

}
