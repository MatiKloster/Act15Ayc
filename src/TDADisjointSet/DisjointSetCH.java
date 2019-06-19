package TDADisjointSet;

import Exceptions.InvalidPositionException;
import TDALista.DoubleLinkedList;
import TDALista.NodoD;


public class DisjointSetCH {
    DoubleLinkedList<NodoDisjoint> parentList;
    NodoD<NodoDisjoint>[] nodos;

    public DisjointSetCH(int n) {
        //Crea una lista de nodos padre (cantidad de subconjuntos)
        parentList= new DoubleLinkedList<>();
        //Crea una lista de nodos del tamaño del entero pasado por parámetro
        nodos = new NodoD[n];
    }

    public void initiate(int[] nodos){
        //Crea un conjunto para cada nodo en el arreglo de nodos
        for(int i= 0; i < nodos.length; i++){
            makeSet(nodos[i]);
        }
    }

    //Crea conjuntos con un nodo
    public void makeSet(int n) {
        //Crea un nodo con elemento n
        NodoDisjoint nodo = new NodoDisjoint(n,null,0);
        //Se asigna como padre de sí mismo
        nodo.setPadre(nodo);
        //Agrega al nodo a la cabeza del arreglo de padres
        nodos[n] = parentList.addFirst(nodo);

    }

    //Retorna el nodo que representa al conjunto en el que se encuentra el nodo pasado por parámetro
    public NodoDisjoint find(int x) {
        //Si el nodo no es su propio padre, significa que no es el representante del conjunto.
        if (nodos[x].element().getPadre() != nodos[x].element()) {
            //Entonces llamamos recursivamente a find con el padre del nodo x
            nodos[x].element().setPadre(find(nodos[x].element().getPadre().getElemento()));
        }
        //Caso contrario, estamos con el nodo representante del conjunto.
        return nodos[x].element().getPadre();
    }

    //Une a los conjuntos que contienen a 'x' y a 'y'
    public void union(int x, int y) {
        //Busca a los conjuntos de x e y
        NodoDisjoint xRoot = find(x), yRoot = find(y);
        //Si find no retornó el mismo nodo, significa que no pertenecen al mismo conjunto,
        if (xRoot.getElemento() != yRoot.getElemento()) {
            //Si el rango del conjunto que contiene a x es menor que el del conjunto que contiene a y
            if (xRoot.getRank() < yRoot.getRank()) {
                //Entonces ponemos al conjunto de x como hijo de la raíz del conjunto de y
                xRoot.setPadre(yRoot);
                try {
                    //Por lo tanto x ya no es un nodo padre de un conjunto, lo remuevo.
                    parentList.remove(nodos[x]);
                } catch (InvalidPositionException e) {
                    e.printStackTrace();
                }
            }
            //Caso contrario, el rango del conjunto de x puede ser mayor que el que contiene a y
            else if (yRoot.getRank() < xRoot.getRank()) {
                //Entonces ponemos al conjunto de y como hijo de la raíz del conjunto de x
                yRoot.setPadre(xRoot);
                try {
                    //Por lo tanto y ya no es un nodo padre de un conjunto, lo remuevo.
                    parentList.remove(nodos[y]);
                } catch (InvalidPositionException e) {
                    e.printStackTrace();
                }
            }
            //Si llegamos hasta aquí, el rango de ambos conjuntos es igual
            else
            {
                //Ponemos al conjunto de x como padre del conjunto de y (es lo mismo hacer la inversa)
                yRoot.setPadre(xRoot);
                try {
                    parentList.remove(nodos[y]);
                } catch (InvalidPositionException e) {
                    e.printStackTrace();
                }
                //Y recalculamos el rango del conjunto de x
                xRoot.setRank();
            }
        }
    }
    //Retorna la cantidad de conjuntos en la estructura
    public int parentSize(){
        return parentList.size();
    }
}
