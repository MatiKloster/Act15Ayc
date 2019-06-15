import TDACola.ColaEnlazada;
import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDAColaPrioridad.Pesado;
import TDADisjointSet.DisjointSet;
import TDADisjointSet.NodoDisjoint;

public class Conexo {
    private int[] padre;
    private Color[] color;
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

    public void BFS(Grafo graph) throws EmptyQueueException {
        color=new Color[graph.getNodosCount()];
        for(int n:graph.getNodos())
            color[n]=Color.blanco;
        padre=new int[graph.getNodosCount()];
        Queue<Integer> Q=new ColaEnlazada<>();
        for(int n:graph.getNodos()){
            color[n]= Color.gris;
            padre[n]=0;
            Q.enqueue(n);
            visitarBFS(graph,Q);
        }
    }

    private void visitarBFS(Grafo graph,Queue<Integer> Q) throws EmptyQueueException {

        while(!Q.isEmpty()){
            int n=Q.dequeue();
            Pesado [] adyacentes = graph.getAdyacentes(n);
            for(Pesado pesado: adyacentes){
                int v = pesado.getArco().getNodo2();
                if(color[v] == Color.blanco){
                    Q.enqueue(v);
                    color[v] = Color.gris;
                }
            }
            Q.dequeue();
            color[n] = Color.negro;
        }
    }

    public boolean conexoBFS(Grafo graph) throws EmptyQueueException {
        BFS(graph);
        int count=0;int i=0;
        while ((count<2)&&(i<padre.length)) {
            if (padre[i++]==0)
                count++;
        }
        return count==2;
    }
}
