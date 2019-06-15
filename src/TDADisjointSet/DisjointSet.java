package TDADisjointSet;

import Exceptions.InvalidPositionException;
import TDALista.DoubleLinkedList;
import TDALista.NodoD;

import java.util.ArrayList;

public class DisjointSet {
    int[] rank;
    DoubleLinkedList<NodoDisjoint> parentList;
    NodoD<NodoDisjoint>[] nodos;

    int n;

    // Constructor
    public DisjointSet(int n) {
        rank = new int[n];
        parentList= new DoubleLinkedList<NodoDisjoint>();
        nodos = new NodoD[n];
        this.n = n;

    }

    public void initiate(int[] nodos){
        for(int i= 0; i <= nodos.length; i++){
            makeSet(nodos[i]);
        }
    }

    // Creates n sets with single item in each
    public void makeSet(int n) {
        NodoDisjoint nodo = new NodoDisjoint(n,null,0);
        nodos[n] = parentList.addFirst(nodo);

    }

    // Returns representative of x's set
    public NodoDisjoint find(int x) {
        // Finds the representative of the set
        // that x is an element of
        if (nodos[x].element().getPadre() != null) {
            // if x is not the parent of itself
            // Then x is not the representative of
            // his set,
            nodos[x].element().setPadre(find(nodos[x].element().getPadre().getElemento()));

            // so we recursively call Find on its parent
            // and move i's node directly under the
            // representative of this set
        }

        return nodos[x].element().getPadre();
    }

    // Unites the set that includes x and the set
    // that includes x
    public void union(int x, int y) {
        // Find representatives of two sets
        NodoDisjoint xRoot = find(x), yRoot = find(y);

        if (xRoot.getElemento() != yRoot.getElemento()) {

            // If x's rank is less than y's rank
            if (xRoot.getRank() < yRoot.getRank()) {

                // Then move x under y  so that depth
                // of tree remains less
                xRoot.setPadre(yRoot);
                try {
                    parentList.remove(nodos[x]);
                } catch (InvalidPositionException e) {
                    e.printStackTrace();
                }
            }
                // Else if y's rank is less than x's rank
            else if (yRoot.getRank() < xRoot.getRank()) {

                // Then move y under x so that depth of
                // tree remains less
                yRoot.setPadre(xRoot);
                try {
                    parentList.remove(nodos[y]);
                } catch (InvalidPositionException e) {
                    e.printStackTrace();
                }
            }
            else // if ranks are the same
            {
                // Then move y under x (doesn't matter
                // which one goes where)
                yRoot.setPadre(xRoot);
                try {
                    parentList.remove(nodos[y]);
                } catch (InvalidPositionException e) {
                    e.printStackTrace();
                }

                // And increment the the result tree's
                // rank by 1
                xRoot.setRank();
            }
        }
    }
}
