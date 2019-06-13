package TDAGrafo;

import java.util.Iterator;

import TDACola.ColaEnlazada;
import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDALista.*;
import TDAMapeo.Entry;
import TDAMapeo.InvalidKeyException;
import TDAMapeo.Map;
import TDAMapeo.MapeoHash;

public class Grafo<V,E> implements Graph<V,E> {
	protected PositionList<Vertice<V,E>> listaVertices;
	protected PositionList<Arco<V,E>> listaArcosND;
	private class Arco<V,E>implements Edge<E> {
		private E rotulo;
		private Vertice<V,E> sucesor,predecesor;
		private Position<Arco<V,E>> posicionEnAdyacentes1,posicionEnAdyacentes2;
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
		public Position<Arco<V,E>> getPosicionEnAdyacentes(){
			return this.posicionEnAdyacentes1;
		}
		public Position<Arco<V,E>> getPosicionEnAdyacentes2(){
			return this.posicionEnAdyacentes2;
		}
		public void setPosicionEnAdyacentes2(Position<Arco<V,E>> pos){
			posicionEnAdyacentes2=pos;
		}
		public void setPredecesor(Vertice<V,E> predecesor){
			this.predecesor=predecesor;
		}
		public void setPosicionEnAdyacentes(Position<Arco<V,E>> pos){
			this.posicionEnAdyacentes1=pos;
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
	
	public Grafo(){
		listaVertices=new DoubleLinkedList<Vertice<V,E>>();
		listaArcosND=new DoubleLinkedList<Arco<V,E>>();
		
	}
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista=new DoubleLinkedList<Vertex<V>>();
		for(Vertice<V,E>w:listaVertices){
			lista.addLast(w);
		}
		return lista;
	}

	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista=new DoubleLinkedList<Edge<E>>();
		for(Arco<V,E>w:listaArcosND){
			lista.addLast(w);
		}
		return lista;
	
	}
	private Vertice<V,E> checkVertex(Vertex<V> v)throws InvalidVertexException{
		Vertice<V,E> toRet=null;
		if(v==null|| listaVertices.isEmpty())
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
	private Arco<V,E> checkEdge(Edge<E> e)throws InvalidEdgeException{
		Arco<V,E> toRet=null;
		if(e==null|| listaArcosND.isEmpty())
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
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> ver=checkVertex(v);
		PositionList<Edge<E>> lista=new DoubleLinkedList<Edge<E>>();
		for(Arco<V,E>w:ver.getAdyacentes()){
			lista.addLast(w);
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
		Vertex<V>[] toRet=(Vertice<V,E>[])new Vertice[2];
		if(arc.getPredecesor()==null || arc.getSucesor()==null) throw new InvalidEdgeException("Arco invalido");
		else{
			toRet[0]=arc.getPredecesor();
			toRet[1]=arc.getSucesor();
		}
		return toRet;
	}


	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		Vertice<V,E>v1=checkVertex(v);
		Vertice<V,E> v2=checkVertex(w);
		Iterator<Arco<V, E>> it=v1.getAdyacentes().iterator();
		boolean encontre=false;
		while(!encontre && it.hasNext()){
			Arco<V,E> aux=it.next();
			encontre=aux.getPredecesor()==v2 || aux.getSucesor()==v2;
		}
		return encontre;
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
		Vertice<V,E> nv=new Vertice<V,E>(x);
		listaVertices.addLast(nv);
		try {
			nv.setPosicionEnNodos(listaVertices.last());
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return nv;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		Vertice<V,E> v1=checkVertex(v);
		Vertice<V,E> v2=checkVertex(w);
		Arco<V,E> arc=new Arco<V,E>(e,v1,v2);
		listaArcosND.addLast(arc);
		
		v1.getAdyacentes().addLast(arc);
		v2.getAdyacentes().addLast(arc);
		try {
			arc.setPosicionEnListaArcos(listaArcosND.last());
			arc.setPosicionEnAdyacentes(v1.getAdyacentes().last());
			arc.setPosicionEnAdyacentes2(v2.getAdyacentes().last());
		} catch (EmptyListException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return arc;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> ver=checkVertex(v);
		if(!ver.getAdyacentes().isEmpty()) throw new InvalidVertexException("Vertice tiene arcos sin eliminar");
		else
			try {
				return listaVertices.remove(ver.getPosicionEnNodos()).element();
			} catch (InvalidPositionException e) {
				return null;
			}
	}

	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> arc=checkEdge(e);
		try {
			arc.getPredecesor().getAdyacentes().remove(arc.getPosicionEnAdyacentes());
			arc.getSucesor().getAdyacentes().remove(arc.getPosicionEnAdyacentes2());
			return listaArcosND.remove(arc.getPosicionEnListaArcos()).element();
		} catch (InvalidPositionException e2) {
			e2.printStackTrace();
			return null;
		}
	}
	public void dfsShell(){
		Map<Vertice<V,E>,Boolean> M=new MapeoHash<Vertice<V,E>,Boolean>();
		try {
			for(Vertice<V,E> ve:this.listaVertices){
				M.put(ve, false);
			}
			for(Vertice<V,E> v:this.listaVertices){
				if(M.get(v)==false) dfs(v,M);
			}
		} catch (InvalidKeyException | InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		}
	}
	public void bfsShell(){
		Map<Vertice<V,E>,Boolean> M=new MapeoHash<Vertice<V,E>,Boolean>();
		try {
			for(Vertice<V,E> ve:this.listaVertices){
				M.put(ve, false);
			}
			for(Vertice<V,E> v:this.listaVertices){
				if(M.get(v)==false) bfs(v,M);
			}
		} catch (InvalidKeyException | InvalidVertexException | InvalidEdgeException | EmptyQueueException e) {
			e.printStackTrace();
		}
	}
	private void dfs(Vertice<V,E> v, Map<Vertice<V,E>,Boolean> m) throws InvalidVertexException, InvalidKeyException, InvalidEdgeException{
		m.put(v, true);
		System.out.println(v.element());
		for(Edge<E> e:this.incidentEdges(v)){
			Vertice<V,E> w=(Vertice<V,E>)opposite(v,e);
			if(m.get(w)==false)
					dfs(w,m);
		}
	}
	private void bfs(Vertice<V,E>v, Map<Vertice<V,E>,Boolean> m) throws InvalidVertexException, InvalidEdgeException, InvalidKeyException, EmptyQueueException{
		Queue<Vertice<V,E>> q=new ColaEnlazada<Vertice<V,E>>();
		q.enqueue(v);
		while (!q.isEmpty()) {
			Vertice<V,E> ver=q.dequeue();
			System.out.println(ver.element());
			m.put(ver, true);
			
			for(Edge<E> e:this.incidentEdges(ver)){
				Vertice<V,E> w=(Vertice<V,E>)this.opposite(ver, e);
				if(m.get(w)==false){ 
					q.enqueue(w);
					m.put(w, true);
				}
			}
		}
	}
	private boolean  bfsBoolean(Vertice<V,E>v,Vertice<V,E> destino, Map<Vertice<V,E>,Boolean> m) throws InvalidVertexException, InvalidEdgeException, InvalidKeyException, EmptyQueueException{
		Queue<Vertice<V,E>> q=new ColaEnlazada<Vertice<V,E>>();
		q.enqueue(v);
		while (!q.isEmpty()) {
			Vertice<V,E> ver=q.dequeue();
			System.out.println(ver.element());
			m.put(ver, true);
			if(ver==destino) return true;
			for(Edge<E> e:this.incidentEdges(ver)){
				Vertice<V,E> w=(Vertice<V,E>)this.opposite(ver, e);
				if(m.get(w)==false){ 
					q.enqueue(w);
					m.put(w, true);
				}
			}
		}
		return false;
	}
	public boolean hayCamino(Vertex<V> origen,Vertex<V> destino,PositionList<Vertex<V>> list) throws InvalidVertexException, InvalidEdgeException, InvalidKeyException, InvalidPositionException, EmptyListException{
		Map<Vertice<V,E>,Boolean> m=new MapeoHash<Vertice<V,E>,Boolean>();
		for(Vertice<V,E> v: this.listaVertices){
			m.put(v, false);
		}
		//return hayCaminoAux((Vertice<V,E>)origen,(Vertice<V,E>)destino,m,list);
		try {
			return bfsBoolean((Vertice<V,E>)origen,(Vertice<V,E>)destino,m);
		} catch (EmptyQueueException e) {
			return false;
		}
	}
	private boolean hayCaminoAux(Vertice<V,E> origen, Vertice<V,E> destino,Map<Vertice<V,E>,Boolean> m,PositionList<Vertex<V>>list) throws InvalidVertexException, InvalidEdgeException, InvalidKeyException, InvalidPositionException, EmptyListException{
		boolean hay=false;
		m.put(origen, true);
		list.addLast(origen);
		if(origen==destino){
			return true;
		}
		else{
			Iterator<Edge<E>> itArc=this.incidentEdges(origen).iterator();
			while(itArc.hasNext() && !hay){
				Vertice<V,E> w=(Vertice<V,E>)this.opposite(origen, itArc.next());
				if(m.get(w)==false){
					hay=hayCaminoAux(w,destino,m,list);
					if(!hay) list.remove(list.last());
				}
			}
			return hay;
		}
	}
	private void clonarLista(PositionList<Vertex<V>>l1,PositionList<Vertex<V>>l2) throws InvalidPositionException{
		for(Position<Vertex<V>> pv:l1.positions()){
			l1.remove(pv);
		}
		for(Position<Vertex<V>>pv:l2.positions()){
			l1.addLast(pv.element());
		}
	}
	private void clonarLista1(PositionList<Arco<V,E>>l1,PositionList<Arco<V,E>>l2) throws InvalidPositionException{
		for(Position<Arco<V,E>> pv:l1.positions()){
			l1.remove(pv);
		}
		for(Position<Arco<V,E>>pv:l2.positions()){
			l1.addLast(pv.element());
		}
	}
	public void CaminoCostoMinimo(Vertex<V> origen, Vertex<V> destino,PositionList<Vertex<V>> caminoCostoMinimo,Solucion costoCaminoMinimo) throws InvalidVertexException, InvalidEdgeException, InvalidKeyException, InvalidPositionException, EmptyListException{
		Map<Vertice<V,E>,Boolean> m=new MapeoHash<Vertice<V,E>,Boolean>();
		for(Vertice<V,E> v: this.listaVertices){
			m.put(v, false);
		}
		PositionList<Vertex<V>>caminoActual=new DoubleLinkedList<Vertex<V>>();
		Integer costoCaminoActual=0;
		CaminoCostoMinimoAux((Vertice<V,E>)origen,(Vertice<V,E>)destino,m,caminoActual,caminoCostoMinimo, costoCaminoActual,costoCaminoMinimo);
	}
	private void CaminoCostoMinimoAux(Vertice<V,E> origen, Vertice<V,E> destino,Map<Vertice<V,E>,Boolean> m,PositionList<Vertex<V>>caminoActual,PositionList<Vertex<V>> caminoCostoMinimo,Integer costoCaminoActual,Solucion costoCaminoMinimo) throws InvalidVertexException, InvalidEdgeException, InvalidKeyException, InvalidPositionException, EmptyListException{
		System.out.println("Costo camino actual ="+costoCaminoActual+" Costo camino minimo ="+costoCaminoMinimo.getValor());
		System.out.println(origen.element());
		m.put(origen, true);
		caminoActual.addLast(origen);
		if(origen==destino){
			if(costoCaminoActual<costoCaminoMinimo.getValor()){
				costoCaminoMinimo.setValor(costoCaminoActual);
				clonarLista(caminoCostoMinimo,caminoActual);
			}
		}
		else{
			Iterator<Arco<V,E>> itArc=origen.getAdyacentes().iterator();
			while(itArc.hasNext()){
				Arco<V,E> e=itArc.next();
				Vertice<V,E> w=(e.getPredecesor()==origen)?e.getSucesor():e.getPredecesor();
				if(m.get(w)==false){
					CaminoCostoMinimoAux(w,destino,m,caminoActual,caminoCostoMinimo,costoCaminoActual+(Integer)e.element(),costoCaminoMinimo);
					
				}
			}
		}
		caminoActual.remove(caminoActual.last());
		m.put(origen, false);
	}
	public void metodO(Vertex<V> o,Vertex<V>d) throws InvalidEdgeException, InvalidKeyException, InvalidVertexException, InvalidPositionException, EmptyListException{
		Map<Vertice<V,E>,Boolean> m=new MapeoHash<Vertice<V,E>,Boolean>();
		for(Vertice<V,E> e:this.listaVertices){
			m.put(e, false);
		}
		
		Vertice<V,E> ori=checkVertex(o);
		Vertice<V,E> des=checkVertex(d);
		boolean hay=false;
		PositionList<Arco<V,E>> lArc=new DoubleLinkedList<Arco<V,E>>();
		PositionList<PositionList<Arco<V,E>>>ListaDeLista=new DoubleLinkedList<PositionList<Arco<V,E>>>();
		metod(ori,des,m,lArc,ListaDeLista);
		for(PositionList<Arco<V,E>>l :ListaDeLista){
			for(Arco<V,E>arc:l){
				if(arc!=null && !listaArcosND.isEmpty()){
					System.out.println("Elimine ="+arc.element());
					E q=removeEdge(arc);
					if(q==null){
						System.out.println("Algo anduvo mal ");
					}
				}
			}
		}
		
	}
	private void metod(Vertice<V,E> origen, Vertice<V,E> destino,Map<Vertice<V,E>,Boolean> visitados,PositionList<Arco<V,E>> lArc,PositionList<PositionList<Arco<V,E>>> listdelist) throws InvalidEdgeException, InvalidKeyException, InvalidPositionException, EmptyListException{
		visitados.put(origen, true);
		boolean hay=false;
		if(origen==destino){
			PositionList<Arco<V,E>>listaAux=new DoubleLinkedList<Arco<V,E>>();
			clonarLista1(listaAux,lArc);
			listdelist.addLast(listaAux);
		}
		else{
			Iterator<Arco<V,E>> it=origen.getAdyacentes().iterator();
			while(it.hasNext()){
				Arco<V,E> ed=it.next();
				Vertice<V,E> w=(ed.getPredecesor()==origen)?ed.getSucesor():ed.getPredecesor();
				if(visitados.get(w)==false){
					lArc.addLast(ed);
					metod(w,destino,visitados,lArc,listdelist);
					lArc.remove(lArc.last());
				}
			}
		}
		visitados.put(origen, false);
	}
}
