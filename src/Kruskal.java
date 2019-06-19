import TDAColaPrioridad.Heap;
import TDAColaPrioridad.Pesado;
import TDADisjointSet.DisjointSetCH;
import TDADisjointSet.DisjointSetSH;
import TDADisjointSet.NodoDisjoint;
import TDADisjointSet.NodoDisjointSH;
import TDALista.DoubleLinkedList;

public class Kruskal {
    private Grafo graph;

    public Kruskal(Grafo graph){
        this.graph=graph;
    }

    public DoubleLinkedList<Pesado> arcosOrdenadosCH() throws Exception {
        //Ordenamos los arcos en una lista
        DoubleLinkedList<Pesado> ar=ordernarLista();
        //Creamos una lista para los arcos del árbol de cubrimiento minimal
        DoubleLinkedList<Pesado> t=new DoubleLinkedList<>();
        int n=graph.getNodosCount();
        DisjointSetCH d =new DisjointSetCH(graph.getNodosCount());
        //Inicializamos la estructura de disjoint-set
        d.initiate(graph.getNodos());
        do{
            //Obtenemos el primer arco de la lista ordenada y lo eliminamos de la misma
            Pesado e=ar.removeFirst();
            //Buscamos los nodos que representan al conjunto en donde se encuentran los nodos del arco removido
            NodoDisjoint compu= d.find(e.getArco().getNodo1());
            NodoDisjoint compv= d.find(e.getArco().getNodo2());
            if (compu!=compv){
                //Si son distintos conjuntos, los unimos
                d.union(compu.getElemento(), compv.getElemento());
                //Y agregamos el arco a la lista del árbol de cubrimiento minimal
                t.addLast(e);
            }
          //Cortamos cuando el tamaño de la lista de arcos del árbol es igual a la cantidad de nodos - 1
        } while (t.size()< n-1);
        //Retornamos la lista con los arcos que representan el árbol de cubrimiento minimal.
        return t;
    }

    public DoubleLinkedList<Pesado> arcosOrdenadosSH() throws Exception {
        //Ordenamos los arcos en una lista
        DoubleLinkedList<Pesado> ar=ordernarLista();
        //Creamos una lista para los arcos del árbol de cubrimiento minimal
        DoubleLinkedList<Pesado> t=new DoubleLinkedList<>();
        int n=graph.getNodosCount();
        DisjointSetSH d =new DisjointSetSH(graph.getNodosCount());
        //Inicializamos la estructura de disjoint-set
        d.initiate(graph.getNodos());
        do{
            //Obtenemos el primer arco de la lista ordenada y lo eliminamos de la misma
            Pesado e=ar.removeFirst();
            //Buscamos los nodos que representan al conjunto en donde se encuentran los nodos del arco removido
            NodoDisjointSH compu= d.find(e.getArco().getNodo1());
            NodoDisjointSH compv= d.find(e.getArco().getNodo2());
            if (compu!=compv){
                //Si son distintos conjuntos, los unimos
                d.union(compu.getElemento(), compv.getElemento());
                //Y agregamos el arco a la lista del árbol de cubrimiento minimal
                t.addLast(e);
            }
          //Cortamos cuando el tamaño de la lista de arcos del árbol es igual a la cantidad de nodos - 1
        } while (t.size()< n-1);
        //Retornamos la lista con los arcos que representan el árbol de cubrimiento minimal.
        return t;
    }

    private DoubleLinkedList<Pesado> ordernarLista() throws Exception {
        //Creamos una lista para retornar los arcos ordenados
        DoubleLinkedList<Pesado> ar=new DoubleLinkedList<>();
        //Creamos un heap para ordenar los arcos, con tamaño a+1 (Porque la celda 0 del heap queda inutilizada).
        Heap heap=new Heap(graph.getArcosCount()+1);
        for (Pesado pesado : graph.getArcos()) {
            //Insertamos cada arco del grafo en el heap
            heap.insert(pesado);
        }
        while (!heap.isEmpty()){
            //Insertamos cada arco removido del min-heap en la lista
            ar.addLast(heap.removeMin());
        }
        //Retornamos la lista con los arcos ordenados por peso
        return ar;
    }
    public DoubleLinkedList<Pesado> conHeapSH() throws Exception {
        //Creamos una lista para los arcos del árbol de cubrimiento minimal
        DoubleLinkedList<Pesado> t=new DoubleLinkedList<>();
        //Creamos un heap que contenga los arcos del grafo
        Heap heap=ordenarAenHeap();
        int n=graph.getNodosCount();
        DisjointSetSH d =new DisjointSetSH(n);
        //Inicializamos la estructura disjoint-set
        d.initiate(graph.getNodos());
        do{
            //Obtenemos el primer arco del heap y lo eliminamos del mismo.
            Pesado e=heap.removeMin();
            //Buscamos los nodos que representan al conjunto en donde se encuentran los nodos del arco removido
            NodoDisjointSH compu= d.find(e.getArco().getNodo1());
            NodoDisjointSH compv= d.find(e.getArco().getNodo2());
            if (compu!=compv){
                //Si son distintos conjuntos, los unimos
                d.union(compu.getElemento(), compv.getElemento());
                //Y agregamos el arco a la lista del árbol de cubrimiento minimal
                t.addLast(e);
            }
            //Cortamos cuando el tamaño de la lista de arcos del árbol es igual a la cantidad de nodos - 1
        } while (t.size()< n-1);
        //Retornamos la lista con los arcos que representan el árbol de cubrimiento minimal.
        return t;
    }
    private Heap ordenarAenHeap(){
        //Creamos un heap para ordenar los arcos, con tamaño a+1 (Porque la celda 0 del heap queda inutilizada).
        Heap heap=new Heap(graph.getArcosCount()+1);
        for (Pesado pesado : graph.getArcos()) {
            //Insertamos todos los arcos del grafo en el heap
            heap.insert(pesado);
        }
        return heap;
    }
    public DoubleLinkedList<Pesado> conHeapCH() throws Exception {
        //Creamos una lista para los arcos del árbol de cubrimiento minimal
        DoubleLinkedList<Pesado> t=new DoubleLinkedList<>();
        //Creamos un heap que contenga los arcos del grafo
        Heap heap=ordenarAenHeap();
        int n=graph.getNodosCount();
        DisjointSetCH d =new DisjointSetCH(n);
        //Inicializamos la estructura disjoint-set
        d.initiate(graph.getNodos());
        do{
            //Obtenemos el primer arco del heap y lo eliminamos del mismo.
            Pesado e=heap.removeMin();
            //Buscamos los nodos que representan al conjunto en donde se encuentran los nodos del arco removido
            NodoDisjoint compu= d.find(e.getArco().getNodo1());
            NodoDisjoint compv= d.find(e.getArco().getNodo2());
            if (compu!=compv){
                //Si son distintos conjuntos, los unimos
                d.union(compu.getElemento(), compv.getElemento());
                //Y agregamos el arco a la lista del árbol de cubrimiento minimal
                t.addLast(e);
            }
            //Cortamos cuando el tamaño de la lista de arcos del árbol es igual a la cantidad de nodos - 1
        } while (t.size()< n-1);
        //Retornamos la lista con los arcos que representan el árbol de cubrimiento minimal.
        return t;
    }
}
