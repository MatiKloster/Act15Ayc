package TDADisjointSet;

import Exceptions.InvalidPositionException;
import TDALista.DoubleLinkedList;
import TDALista.NodoD;

import java.util.ArrayList;

public class DisjointSetSH {

    DoubleLinkedList<NodoDisjointSH> parentList;
    NodoD<NodoDisjointSH>[] nodos;


    // Constructor
    public DisjointSetSH(int n) {
        parentList = new DoubleLinkedList<NodoDisjointSH>();
        nodos = new NodoD[n];

    }

    public void initiate(int[] nodos){
        for(int i= 0; i <= nodos.length; i++){
         makeSet(nodos[i]);
        }
    }

    // Creates n sets with single item in each
    public void makeSet(int n) {
        NodoDisjointSH nodo = new NodoDisjointSH(n,null,0);
        nodos[n] = parentList.addFirst(nodo);
    }

    // Returns representative of x's set
    public NodoDisjointSH find(int x) {

        if (nodos[x-1].element().getPadre() != null) {
            // if x is not the parent of itself
            // Then x is not the representative of
            // his set,
            return find(nodos[x].element().getPadre().getElemento());
            // so we recursively call Find on its parent
            // and move i's node directly under the
            // representative of this set
        }

        return nodos[x].element();
    }

    // Unites the set that includes x and the set
    // that includes x
    public void union(int x, int y) throws InvalidPositionException {
        // Find representatives of two sets
        NodoDisjointSH xRoot = find(x), yRoot = find(y);

        if (xRoot.getElemento() != yRoot.getElemento()) {
            yRoot.setPadre(xRoot);
            parentList.remove(nodos[y]);
        }
    }

    public int parentSize(){
        return parentList.size();
    }
}
