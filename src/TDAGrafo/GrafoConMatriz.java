package TDAGrafo;

import Exceptions.EmptyListException;
import TDALista.*;
public class GrafoConMatriz<V,E> implements Graph<V,E> {
	protected PositionList<Vertice<V,E>> listaVertice; // preguntar si tengo que poner vertice o vertex
	protected PositionList<Arco<V,E>> listaArco; 		// idem edge o arco 
	protected Edge<E>[][] matriz;
	protected int cantVertices;
	private class Arco<V,E>implements Edge<E> {
		private E rotulo;
		private Vertice<V,E> sucesor,predecesor;
		private Position<Arco<V,E>> posicionEnListaArcos;
		public Arco(E rotulo, Vertice<V,E> sucesor,Vertice<V,E> predecesor){
			this.rotulo=rotulo;
			this.sucesor=sucesor;
			this.predecesor=predecesor;
		}
		public E element() {
			return rotulo;
		}
		public Vertice<V,E> getSucesor(){
			return sucesor;
		}
		public Vertice<V,E> getPredecesor(){
			return this.predecesor;
		}
		
		public void setPredecesor(Vertice<V,E> predecesor){
			this.predecesor=predecesor;
		}
		public void setPosicionEnListaArcos(Position<Arco<V,E>> pos){
			this.posicionEnListaArcos=pos;
		}
		public Position<Arco<V,E>> getPosicionEnListaArcos(){
			return this.posicionEnListaArcos;
		}
	}
	private class Vertice<V,E> implements Vertex<V> {
		private V rotulo;
		private Position<Vertice<V,E>> posicionEnListaVertice;
		private int indice;
		
		public Vertice(V rotulo,int indice){
			this.rotulo=rotulo;
			this.indice=indice;
		}
		public V element() {
			return rotulo;
		}
		
		public void setRotulo(V rotulo){
			this.rotulo=rotulo;
		}
		public Position<Vertice<V,E>> getPosicionEnNodos(){
			return posicionEnListaVertice;
		}
		public void setPosicionEnNodos(Position<Vertice<V,E>> pos){
			this.posicionEnListaVertice=pos;
		}
		public int getIndice(){
			return indice;
		}
	}
	@SuppressWarnings("unchecked")
	public GrafoConMatriz(int n){
		listaVertice=new DoubleLinkedList<Vertice<V,E>>();
		listaArco=new DoubleLinkedList<Arco<V,E>>();
		matriz=(Edge<E>[][])new Arco[n][n];
		cantVertices=0;
	}
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista=new DoubleLinkedList<Vertex<V>>();
		for(Vertex<V> v:listaVertice){
			lista.addLast(v);
		}
		return lista;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista=new DoubleLinkedList<Edge<E>>();
		for(Edge<E> e:listaArco){
			lista.addLast(e);
		}
		return lista;
	}

	private Vertice<V,E> checkVertex(Vertex<V> v)throws InvalidVertexException{ // preguntar que tengo que controlar ademas de esto
		Vertice<V,E> toRet=null;
		if(v==null || listaVertice.isEmpty()) throw new InvalidVertexException("Vertice invalido");
		else{
			try{
				toRet=(Vertice<V,E>)v;
			}
			catch(ClassCastException e){ 
				throw new InvalidVertexException("Vertice invalido");}
		}
		return toRet;
	}
	private Arco<V,E> checkEdge(Edge<E> e)throws InvalidEdgeException{ // preguntar que tengo que controlar ademas de esto
		Arco<V,E> toRet=null;
		if(e==null || listaArco.isEmpty()) throw new InvalidEdgeException("Arco invalido");
		else{
			try{
				toRet=(Arco<V,E>)e;
				if(toRet.getPredecesor()==null || toRet.getSucesor()==null) throw new InvalidEdgeException("Arco invalido");
			}
			catch(ClassCastException h){ 
				throw new InvalidEdgeException("Arco invalido");}
		}
		return toRet;
	}
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		PositionList<Edge<E>> lista=new DoubleLinkedList<Edge<E>>();
		Vertice<V,E> ver=checkVertex(v);
		int fila=ver.getIndice();
		for(int col=0;col<this.cantVertices;col++){
			if(matriz[fila][col]!=null)
				lista.addLast(matriz[fila][col]);
		}
		return lista;
	}

	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		Vertice<V,E>ver=checkVertex(v);
		Arco<V,E> arc=checkEdge(e);
		if(arc.getPredecesor()!= ver && arc.getSucesor()!=ver) throw new InvalidEdgeException("Arco invalido");
		else{
			return (arc.getPredecesor()!=ver)?arc.getPredecesor():arc.getSucesor();
		}
	}

	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> arc=checkEdge(e);
		Vertex<V>[] toRet=(Vertex<V>[])new Vertice[2];
		if(arc.getPredecesor()!=null && arc.getSucesor()!=null){
			toRet[0]=arc.getPredecesor();
			toRet[1]=arc.getSucesor();
		}
		return toRet;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		Vertice<V,E> v1=checkVertex(v);
		Vertice<V,E>v2=checkVertex(w);
		return matriz[v1.indice][v2.indice]!=null;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice<V,E> ver=checkVertex(v);
		V toRet=ver.element();
		ver.setRotulo(x);
		return toRet;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> nv=new Vertice<V,E>(x,cantVertices++);
		this.listaVertice.addLast(nv);
		try {
			nv.setPosicionEnNodos(listaVertice.last());
		} catch (EmptyListException e) {
			return null;
		}
		return nv;
	}


	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		Vertice<V,E>v1=checkVertex(v);
		Vertice<V,E>v2=checkVertex(w);
		Arco<V,E> arc=new Arco<V,E>(e,v1,v2);
		int fila=v1.getIndice();
		int col=v2.getIndice();
		matriz[fila][col]=matriz[col][fila]=arc;
		listaArco.addLast(arc);
		try {
			arc.setPosicionEnListaArcos(listaArco.last());
		} catch (EmptyListException e1) {
			e1.printStackTrace();
		}
		return arc;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
	return null;	
	}

}
