import Exceptions.InvalidPositionException;
import TDACola.ColaEnlazada;
import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDADisjointSet.DisjointSet;
import TDADisjointSet.NodoDisjoint;
import business.Pesado;

public class Conexo {
    private int[] padre;
    private Color[] color;
    public boolean conexoDS(Grafo graph) throws InvalidPositionException {
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
        color = new Color[graph.getNodosCount()];
        padre = new int[graph.getNodosCount()];
        for (int n : graph.getNodos()){
            color[n] = Color.blanco;
            padre[n]= -1;
        }
        Queue<Integer> Q=new ColaEnlazada<>();
        for(int n:graph.getNodos()){
            if(color[n]==Color.blanco){
                color[n]= Color.gris;
                Q.enqueue(n);
                visitarBFS(graph,Q);
            }
        }
    }

    private void visitarBFS(Grafo graph,Queue<Integer> Q) throws EmptyQueueException {

        while(!Q.isEmpty()){
            int n=Q.front();
            Pesado [] adyacentes = graph.getAdyacentes(n);
            for(Pesado pesado: adyacentes){
                if(pesado!=null){
                    int v = pesado.getArco().getNodo2();
                    if(v==n){
                        v=pesado.getArco().getNodo1();
                    }
                    if(color[v] == Color.blanco){
                        Q.enqueue(v);
                        color[v] = Color.gris;
                        padre[v]= n;
                    }
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
            if (padre[i++]==-1)
                count++;
        }
        return count<2;

    }
}
