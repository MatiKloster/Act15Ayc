package TDADisjointSet;

import Exceptions.InvalidPositionException;
import TDALista.DoubleLinkedList;
import TDALista.NodoD;


public class DisjointSetSH {
    DoubleLinkedList<NodoDisjointSH> parentList;
    NodoD<NodoDisjointSH>[] nodos;

    public DisjointSetSH(int n) {
        //Crea una lista de nodos padre (cantidad de subconjuntos)
        parentList = new DoubleLinkedList<>();
        //Crea un arreglo de nodos del tamaño pasado por parámetro
        nodos = new NodoD[n];
    }

    public void initiate(int[] nodos){
        //Crea un conjunto para cada nodo en el arreglo de nodos
        for(int i= 0; i < nodos.length; i++){
         makeSet(nodos[i]);
        }
    }

    //Crea conjuntos con un nodo.
    public void makeSet(int n) {
        //Crea un nodo con elemento n
        NodoDisjointSH nodo = new NodoDisjointSH(n,null,0);
        //Se asigna a sí mismo como padre
        nodo.setPadre(nodo);
        //agrega al nodo a la cabeza de la lista de padres
        nodos[n] = parentList.addFirst(nodo);
    }

    //Retorna el nodo que representa al conjunto en el que se encuentra el nodo pasado por parámetro
    public NodoDisjointSH find(int x) {
        //Si el nodo no es su propio padre, significa que no es el representante del conjunto.
        if (nodos[x].element().getPadre() != nodos[x].element()) {
            //Entonces llamamos recursivamente a find con el padre del nodo x
            return find(nodos[x].element().getPadre().getElemento());
        }
        //Caso contrario, estamos con el nodo representante del conjunto.
        return nodos[x].element().getPadre();
    }

    //Une a los conjuntos que contienen a 'x' y a 'y'
    public void union(int x, int y) throws InvalidPositionException {
        //Busca a los conjuntos de x e y
        NodoDisjointSH xRoot = find(x), yRoot = find(y);
        //Si find no retornó el mismo nodo, significa que no pertenecen al mismo conjunto,
        if (xRoot.getElemento() != yRoot.getElemento()) {
            //Seteo a la raíz del conjunto de x como padre de la raíz del conjunto de y.
            yRoot.setPadre(xRoot);
            //Por lo tanto y ya no es un nodo padre de un conjunto, lo remuevo.
            parentList.remove(nodos[y]);
        }
    }
    //Retorna la cantidad de conjuntos en la estructura
    public int parentSize(){
        return parentList.size();
    }
}
