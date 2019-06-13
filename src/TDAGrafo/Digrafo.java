package TDAGrafo;
import java.util.Iterator;

import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;
import TDALista.*;

public class Digrafo<V,E> implements GraphD<V,E> {
	protected PositionList<Vertice<V,E>> nodos;
	
	private class Arco<V,E>implements Edge<E> {
		private E rotulo;
		private Vertice<V,E> sucesor,predecesor;
		private Position<Arco<V,E>> posicionEnAdyacentes;
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
		public Position<Arco<V,E>> getPosicionEnAdyacentes(){
			return this.posicionEnAdyacentes;
		}
		
		public void setPredecesor(Vertice<V,E> predecesor){
			this.predecesor=predecesor;
		}
		public void setPosicionEnAdyacentes(Position<Arco<V,E>> pos){
			this.posicionEnAdyacentes=pos;
		}
	}
	private class Vertice<V,E> implements Vertex<V> {
		private V rotulo;
		private PositionList<Arco<V,E>> adyacentes;
		private Position<Vertice<V,E>> posicionEnNodos;
		
		public Vertice(V rotulo){
			this.rotulo=rotulo;
			adyacentes=new DoubleLinkedList<Arco<V,E>>();
		}
		public V element() {
			return rotulo;
		}
		
		public PositionList<Arco<V,E>> getAdyacentes(){
			return adyacentes;
		}
		
		public void setRotulo(V rotulo){
			this.rotulo=rotulo;
		}
		public Position<Vertice<V,E>> getPosicionEnNodos(){
			return posicionEnNodos;
		}
		public void setPosicionEnNodos(Position<Vertice<V,E>> pos){
			this.posicionEnNodos=pos;
		}
	}
	
	public Digrafo(){
		nodos=new DoubleLinkedList<Vertice<V,E>>();
	}
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista=new DoubleLinkedList<Vertex<V>>();
		for(Vertex<V> v: nodos){
			lista.addLast(v);
		}
		return lista;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista=new DoubleLinkedList<Edge<E>>();
		for(Vertice<V,E> v:nodos){
			try {
				for(Edge<E>w: succesorEdges(v)){
					lista.addLast(w);
				}
			} catch (InvalidVertexException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> ver=checkVertex(v);
		PositionList<Edge<E>> lista=new DoubleLinkedList<Edge<E>>();
		Arco<V,E> aux;
		for(Edge<E> w: edges()){
			aux=(Arco<V,E>)w;
			if(aux.getSucesor()==ver){
				lista.addLast(aux);
			}
		}
		return lista;
	}

	private Vertice<V,E> checkVertex(Vertex<V> v)throws InvalidVertexException{
		Vertice<V,E> toRet=null;
		if(v==null|| nodos.isEmpty())
			throw new InvalidVertexException("Vertice invalido");
		
		else{
			try{
				toRet=(Vertice<V,E>)v;
				//Preguntar si tengo que verificar que este en la lista de vertices
			}
			catch(ClassCastException e){
				throw new InvalidVertexException("Vertice Invalido");
			}
		}
		return toRet;
	}
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> nv=checkVertex(v);
		PositionList<Edge<E>> lista=new DoubleLinkedList<Edge<E>>();
		for(Edge<E>w: nv.getAdyacentes() ){
			lista.addLast(w);
		}
		return lista;
	}

	private Arco<V,E> checkEdge(Edge<E> e)throws InvalidEdgeException{
		Arco<V,E> toRet=null;
		if(e==null)
			throw new InvalidEdgeException("Arco invalido");
		
		else{
			try{
				toRet=(Arco<V,E>)e;
				if(toRet.getPredecesor()==null || toRet.getSucesor()==null){
					throw new InvalidEdgeException("Arco invalido");
				}
			}
			catch(ClassCastException q){
				throw new InvalidEdgeException("Arco Invalido");
			}
		}
		return toRet;
	}
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		Vertice<V,E> ver=checkVertex(v);
		Arco<V,E> arc=checkEdge(e);
		if(arc.getPredecesor()!=ver && arc.getSucesor()!=ver){
			throw new InvalidEdgeException("El arco no es adyacente al vertice");
		}
		else{
			return (arc.getPredecesor()!=ver)?arc.getPredecesor():arc.getSucesor();
		}
		
	}

	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		Vertex<V>[] toRet=(Vertex<V>[]) new Vertex[2];
		Arco<V,E> arc=checkEdge(e);
		toRet[0]=arc.getPredecesor();
		toRet[1]=arc.getSucesor();
		return toRet;
	}

	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		Vertice<V,E>v1=checkVertex(v);
		Vertice<V,E>v2=checkVertex(w);
		Iterator<Edge<E>> it=incidentEdges(v1).iterator();
		Iterator<Edge<E>> it2=succesorEdges(v1).iterator();
		boolean encontre=false;
		while(!encontre && (it.hasNext() || it2.hasNext())){
			if(it.hasNext()){
				encontre=((Arco<V,E>)it.next()).getSucesor()==v2;
			}
			if(it2.hasNext()){
				encontre=((Arco<V,E>)it2.next()).getPredecesor()==v2;
			}
		}
		return encontre;
	}

	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice<V,E> ver=checkVertex(v);
		V toRet=ver.element();
		ver.setRotulo(x);
		return toRet;
	}

	
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> v=new Vertice<V,E>(x);
		nodos.addLast(v);
		try {
			v.setPosicionEnNodos(nodos.last());
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return v;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		Vertice<V,E>v1=checkVertex(v);
		Vertice<V,E>v2=checkVertex(w);
		Arco<V,E> arc=new Arco<V,E>(e,v1,v2);
		v1.getAdyacentes().addLast(arc);
		try {
			arc.setPosicionEnAdyacentes(v1.getAdyacentes().last());
		} catch (EmptyListException e1) {
			e1.printStackTrace();
		}
		return arc;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E>ver=checkVertex(v);
		Position<Vertice<V,E>> pos=ver.getPosicionEnNodos();
		try {
			return nodos.remove(pos).element();
		} catch (InvalidPositionException e) {
			return null; // preguntar si devuelvo null o lanzo excepcion
		}
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> arc=checkEdge(e);
		Position<Arco<V,E>> pos=arc.getPosicionEnAdyacentes();
		Vertice<V,E> ver=arc.getPredecesor();
		try {
			return ver.getAdyacentes().remove(pos).element();
		} catch (InvalidPositionException e1) {
			return null;
		}
	}

}
