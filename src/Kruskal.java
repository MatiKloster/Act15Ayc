import TDAColaPrioridad.Heap;
import TDAColaPrioridad.Pesado;

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
}
