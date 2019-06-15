import TDAColaPrioridad.Pesado;
import TDADisjointSet.DisjointSet;
import TDADisjointSet.NodoDisjoint;

public class Conexo {
    public boolean conexoDS(Grafo graph){
        DisjointSet d= new DisjointSet(graph.getNodosCount());
        d.initiate(graph.getNodos());
        for (Pesado pesado: graph.getArcos()){
            NodoDisjoint compu = d.find(pesado.getArco().getNodo1());
            NodoDisjoint compv = d.find(pesado.getArco().getNodo2());
            if (compu!=compv){
                d.union(compu.getElemento(), compv.getElemento());
            }
            if (d.parentSize()==1)
                break;
        }
        return d.parentSize()==1;
    }
}
