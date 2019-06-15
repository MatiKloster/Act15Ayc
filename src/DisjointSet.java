import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import java.io.*;
import java.util.*;

class DisjointSet {
    int[] rank;
    ArrayList<NodoDisjoint> parentList;
    NodoDisjoint[] nodos;

    int n;

    // Constructor
    public DisjointSet(int n) {
        rank = new int[n];
        parentList= new ArrayList<NodoDisjoint>();
        nodos = new NodoDisjoint[n];
        this.n = n;

    }

    // Creates n sets with single item in each
    void makeSet(int n) {
        NodoDisjoint nodo = new NodoDisjoint(n,null,0);
        nodos[n] = nodo;
        parentList.add(nodo);
    }

    // Returns representative of x's set
    NodoDisjoint find(int x) {
        // Finds the representative of the set
        // that x is an element of
        if (nodos[x].getPadre() != null) {
            // if x is not the parent of itself
            // Then x is not the representative of
            // his set,
            nodos[x].setPadre(find(nodos[x].getPadre().getElemento()));

            // so we recursively call Find on its parent
            // and move i's node directly under the
            // representative of this set
        }

        return nodos[x].getPadre();
    }

    // Unites the set that includes x and the set
    // that includes x
    void union(int x, int y) {
        // Find representatives of two sets
        NodoDisjoint xRoot = find(x), yRoot = find(y);

        if (xRoot.getElemento() != yRoot.getElemento()) {

            // If x's rank is less than y's rank
            if (xRoot.getRank() < yRoot.getRank()) {

                // Then move x under y  so that depth
                // of tree remains less
                xRoot.setPadre(yRoot);
                parentList.remove(xRoot);
            }
                // Else if y's rank is less than x's rank
            else if (yRoot.getRank() < xRoot.getRank()) {

                // Then move y under x so that depth of
                // tree remains less
                yRoot.setPadre(xRoot);
                parentList.remove(yRoot);
            }
            else // if ranks are the same
            {
                // Then move y under x (doesn't matter
                // which one goes where)
                yRoot.setPadre(xRoot);
                parentList.remove(yRoot);

                // And increment the the result tree's
                // rank by 1
                xRoot.setRank();
            }
        }
    }
}
