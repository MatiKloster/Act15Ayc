import Exceptions.InvalidPositionException;
import TDACola.ColaEnlazada;
import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDADisjointSet.DisjointSet;
import TDADisjointSet.NodoDisjoint;
import business.Pesado;

public class Conexo {
    private int[] padre;
    private Color[] color; // usamos arreglos globales asi no tenemos que pasarlos como parametro
    /*
    * Estrategia basica: Cargar los arcos en la estructura Disjoint set y luego si la cantidad de conjuntos en la estructura es 1
    * entonces el grafo es conexo
    * */
    public boolean conexoDS(Grafo graph) throws InvalidPositionException {
        DisjointSet d= new DisjointSet(graph.getNodosCount());
        d.initiate(graph.getNodos());// inicializamos la ed
        for (Pesado pesado: graph.getArcos()){
            NodoDisjoint compu = d.find(pesado.getArco().getNodo1());//
            NodoDisjoint compv = d.find(pesado.getArco().getNodo2());//buscamos el elemento identificador de cada nodo
            if (compu!=compv){// si son distintos, significa que estan en conjuntos separados
                d.union(compu.getElemento(), compv.getElemento());
            }
            if (d.parentSize()==1) // si en algun momento la cantidad de conjuntos en la ed es 1, terminamos
                break;
        }
        return d.parentSize()==1;
    }

    public void BFS(Grafo graph) throws EmptyQueueException {
        color = new Color[graph.getNodosCount()];// arreglo necesario para no pasar por un mismo nodo 2 veces
        padre = new int[graph.getNodosCount()];// arreglo necesario para verificar la conectitud
        for (int n : graph.getNodos()){
            color[n] = Color.blanco;
            padre[n]= -1; //inicializamos con -1, luego si hay 2 nodos con padre -1 entonces el grafo no es conexo
        }
        Queue<Integer> Q=new ColaEnlazada<>();
        for(int n:graph.getNodos()){ // recorremos todos los nodos
            if(color[n]==Color.blanco){ // si no recorri este nodo
                color[n]= Color.gris;
                Q.enqueue(n);
                visitarBFS(graph,Q);//lo visito
            }
        }
    }

    private void visitarBFS(Grafo graph,Queue<Integer> Q) throws EmptyQueueException {
        while(!Q.isEmpty()){
            int n=Q.front(); // pregunto por el tope de la cola
            Pesado [] adyacentes = graph.getAdyacentes(n);
            for(Pesado pesado: adyacentes){ // visito todos los adyacentes al nodo tope de la cola
                if(pesado!=null){
                    int v = pesado.getArco().getNodo2();
                    if(v==n){ // este if es para obtener el nodo diferente al ya obtenido por el tope de la cola
                        v=pesado.getArco().getNodo1();
                    }
                    if(color[v] == Color.blanco){ //si el adyacente no lo recorri
                        Q.enqueue(v);// lo encolo y por lo tanto lo recorro
                        color[v] = Color.gris;
                        padre[v]= n;// al adyacente le seteo que pude llegar a el, atraves del padre obtenido por el tope de la cola
                    }
                }
            }
            Q.dequeue();// una vez que recorri todos los adyacentes , desencolo el nodo y lo pongo en negro
            color[n] = Color.negro;
        }
    }

    public boolean conexoBFS(Grafo graph) throws EmptyQueueException {
        BFS(graph); // calculo bfs y asi obtengo al  arreglo padre
        int count=0;int i=0;
        while ((count<2)&&(i<padre.length)) { // si el contador es mayor a 1 significa que ya encontre 2 nodos con padre -1, lo cual implica
                                                // que no es conexo
            if (padre[i++]==-1)
                count++;
        }
        return count<2;
    }
}
