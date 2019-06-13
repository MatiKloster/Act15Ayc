package TDAGrafo;

import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.PositionList;
import TDAMapeo.InvalidKeyException;

public class prueba {

	public static void main(String[] args)  {
		Graph<Character,Integer> G=new Grafo<Character,Integer>();
		Vertex<Character> a=G.insertVertex('a');
		Vertex<Character> b=G.insertVertex('b');
		Vertex<Character> c=G.insertVertex('c');
		Vertex<Character> d=G.insertVertex('d');
		Vertex<Character> e=G.insertVertex('e');
		Vertex<Character> g=G.insertVertex('g');
		Vertex<Character> h=G.insertVertex('h');Vertex<Character> j=G.insertVertex('j');
		try {
			Edge<Integer> e1=G.insertEdge(a, b, 52);
			Edge<Integer> e2=G.insertEdge(b, c, 37);
			Edge<Integer> e3=G.insertEdge(c, d, 10);
			Edge<Integer> e4=G.insertEdge(d, a, 73);
			Edge<Integer> e5=G.insertEdge(a, e, 91);
			G.insertEdge(e, c, 1);
			G.insertEdge(e, g, 12);
			G.insertEdge(h, j, 99);
			G.insertEdge(g, h, 99);
			
			System.out.println("Vertices");
			for(Vertex<Character> w:G.vertices()){
				//System.out.println(w.element());
				System.out.println("Arcos incidentes a "+ w.element()+" = ");
				for(Edge<Integer> v:G.incidentEdges(w)){
					System.out.println(v.element());
				}
			}
			
			System.out.println("Arcos");
			for(Edge<Integer> w:G.edges()){
				System.out.println(w.element());
			}
			System.out.println(((Grafo<Character,Integer>)G).hayCamino(a, g, new DoubleLinkedList<Vertex<Character>>()));
			((Grafo<Character,Integer>)G).metodO(a, g);
			System.out.println("Arcos despues del metodo");
			for(Edge<Integer> w:G.edges()){
				System.out.println(w.element());
			}
		} catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException | InvalidPositionException | EmptyListException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}



/**System.out.println("DFS");
((Grafo<Character, Integer>) G).dfsShell();

System.out.println("BFS");
((Grafo<Character, Integer>) G).bfsShell();
System.out.println("FINISH BFS");
PositionList<Vertex<Character>> camino=new DoubleLinkedList<Vertex<Character>>();
System.out.println("Hay camino entre a y j?"+((Grafo<Character, Integer>) G).hayCamino(a,j,camino) +"  este es=");

for(Vertex<Character> v:camino){
	System.out.println(v.element());
}
PositionList<Vertex<Character>> caminoCostoMinimo=new DoubleLinkedList<Vertex<Character>>();
Solucion costoCaminoMinimo=new Solucion();
costoCaminoMinimo.setValor(Integer.MAX_VALUE);
((Grafo<Character, Integer>) G).CaminoCostoMinimo(a, g, caminoCostoMinimo, costoCaminoMinimo);
System.out.println("Entre a y g hay "+costoCaminoMinimo.getValor()+ " y el camino es");
for(Vertex<Character> v:caminoCostoMinimo){
	System.out.println(v.element());
}*/