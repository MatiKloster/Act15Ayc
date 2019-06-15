import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import java.io.*;
import java.util.*;

class DisjointSetSH {

    ArrayList<NodoDisjoint> parentList;
    NodoDisjoint[] nodos;


    // Constructor
    public DisjointSetSH(int n) {
        parentList= new ArrayList<NodoDisjoint>();
        nodos = new NodoDisjoint[n];

    }

    // Creates n sets with single item in each
    void makeSet(int n) {
        NodoDisjoint nodo = new NodoDisjoint(n,null,0);
        nodos[n] = nodo;
        parentList.add(nodo);
    }

    // Returns representative of x's set
    NodoDisjoint find(int x) {

        if (nodos[x].getPadre() != null) {
            // if x is not the parent of itself
            // Then x is not the representative of
            // his set,
            return find(nodos[x].getPadre().getElemento());
            // so we recursively call Find on its parent
            // and move i's node directly under the
            // representative of this set
        }

        return nodos[x];
    }

    // Unites the set that includes x and the set
    // that includes x
    void union(int x, int y) {
        // Find representatives of two sets
        NodoDisjoint xRoot = find(x), yRoot = find(y);

        if (xRoot.getElemento() != yRoot.getElemento()) {
            yRoot.setPadre(xRoot);
            parentList.remove(yRoot);
        }
    }
}
