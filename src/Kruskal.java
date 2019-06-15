import TDAColaPrioridad.Heap;
import TDAColaPrioridad.Pesado;
import TDALista.DoubleLinkedList;

import java.util.Iterator;

public class Kruskal {
    private Grafo graph;

    public Kruskal(Grafo graph){
        this.graph=graph;
    }

    public DoubleLinkedList<Pesado> arcosOrdenadosCH() throws Exception {
        DoubleLinkedList<Pesado> ar=new DoubleLinkedList<>();
        DoubleLinkedList<Pesado> t=new DoubleLinkedList<>();
        Heap heap=new Heap(graph.getArcosCount());
        for (Iterator<Pesado> arco = graph.getArcos().iterator(); arco.hasNext();) {
            heap.insert(arco.next());
        }
        while (!heap.isEmpty()){
            ar.addLast(heap.removeMin());
        }
        int n=graph.getNodosCount();
        DisjointSet d =new DisjointSet(graph.getNodosCount());
        d.initialize(graph.getNodos());
        do{
            Pesado e=ar.removeFirst();
            NodoDisjoint compu= d.find(e.getArco().getNodo1());
            NodoDisjoint compv= d.find(e.getArco().getNodo2());
            if (compu!=compv){
                d.union(compu.getElemento(), compv.getElemento());
                t.addLast(e);
            }
        } while (t.size()< n-1);
        return t;
    }

    public DoubleLinkedList<Pesado> arcosOrdenadosSH() throws Exception {
        DoubleLinkedList<Pesado> ar=new DoubleLinkedList<>();
        DoubleLinkedList<Pesado> t=new DoubleLinkedList<>();
        Heap heap=new Heap(graph.getArcosCount());
        for (Iterator<Pesado> arco = graph.getArcos().iterator(); arco.hasNext();) {
            heap.insert(arco.next());
        }
        while (!heap.isEmpty()){
            ar.addLast(heap.removeMin());
        }
        int n=graph.getNodosCount();
        DisjointSetSH d =new DisjointSetSH(graph.getNodosCount());
        d.initialize(graph.getNodos());
        do{
            Pesado e=ar.removeFirst();
            NodoDisjoint compu= d.find(e.getArco().getNodo1());
            NodoDisjoint compv= d.find(e.getArco().getNodo2());
            if (compu!=compv){
                d.union(compu.getElemento(), compv.getElemento());
                t.addLast(e);
            }
        } while (t.size()< n-1);
        return t;
    }
}
