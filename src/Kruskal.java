import TDAColaPrioridad.Heap;
import TDAColaPrioridad.Pesado;
import TDADisjointSet.DisjointSet;
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
        DoubleLinkedList<Pesado> ar=ordernarLista();
        DoubleLinkedList<Pesado> t=new DoubleLinkedList<>();
        int n=graph.getNodosCount();
        DisjointSet d =new DisjointSet(graph.getNodosCount());
        d.initiate(graph.getNodos());
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
        DoubleLinkedList<Pesado> ar=ordernarLista();
        DoubleLinkedList<Pesado> t=new DoubleLinkedList<>();
        int n=graph.getNodosCount();
        DisjointSetSH d =new DisjointSetSH(graph.getNodosCount());
        d.initiate(graph.getNodos());
        do{
            Pesado e=ar.removeFirst();
            NodoDisjointSH compu= d.find(e.getArco().getNodo1());
            NodoDisjointSH compv= d.find(e.getArco().getNodo2());
            if (compu!=compv){
                d.union(compu.getElemento(), compv.getElemento());
                t.addLast(e);
            }
        } while (t.size()< n-1);
        return t;
    }

    private DoubleLinkedList<Pesado> ordernarLista() throws Exception {
        DoubleLinkedList<Pesado> ar=new DoubleLinkedList<>();
        Heap heap=new Heap(graph.getArcosCount());
        for (Pesado pesado : graph.getArcos()) {
            heap.insert(pesado);
        }
        while (!heap.isEmpty()){
            ar.addLast(heap.removeMin());
        }
        return ar;
    }
}