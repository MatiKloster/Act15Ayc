import TDAColaPrioridad.Heap;
import TDAColaPrioridad.Pesado;
import TDADisjointSet.DisjointSetImp;
import TDALista.DoubleLinkedList;
import TDALista.PositionList;

import java.util.Iterator;

public class Kruskal {
    private Grafo graph;

    public Kruskal(Grafo graph){
        this.graph=graph;
    }

    public Pesado[] arcosOrdenados(){
        Pesado[] ar=new Pesado[graph.getArcosCount()];
        Heap heap=new Heap(graph.getArcosCount());
        for (Iterator<Pesado> arco = graph.getArcos().iterator(); arco.hasNext();) {
            heap.insert(arco.next());
        }
        while (!heap.isEmpty()){
            
        }
    }
    public PositionList<Pesado> arcosConHeap(Grafo graph){
        PositionList<Pesado> T=new DoubleLinkedList<>();
        Heap heap = ordernarAenHeap(graph);
        DisjointSetImp D=new DisjointSetImp(graph.getNodosCount());
        D.initiate(graph.getNodos());
        while(T.size()!=graph.getNodosCount()-1){
            Pesado arcoMin=heap.removeMin();
            int compU=D.f
        }
    }

    private Heap ordernarAenHeap(Grafo graph) {
        Heap heap=new Heap(graph.getArcosCount());
        for (Iterator<Pesado> arco = graph.getArcos().iterator(); arco.hasNext();) {
            heap.insert(arco.next());
        }
        return heap;
    }
}
