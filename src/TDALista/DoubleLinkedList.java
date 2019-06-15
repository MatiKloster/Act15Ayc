package TDALista;
import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

public class DoubleLinkedList<E>  implements PositionList<E> {
	protected NodoD<E> head,tail;
	protected int longitud;
	
	public DoubleLinkedList(){
		longitud=0;
		head=new NodoD<E>(null,null,null);
		tail=new NodoD<E>(null,null,head);
		head.setSiguiente(tail);
	}
	
	public int size(){  //O(1)
		return longitud;
	}
	
	public boolean isEmpty(){  //O(1)
		return longitud==0;
	}
	
	public Position<E> first() throws EmptyListException{ //O(1)
		if(isEmpty()){
			throw new EmptyListException("Lista vacia");
		}
		else{
			return (Position<E> )head.getSiguiente();
		}
	}
	public Position<E> last()throws EmptyListException{ //O(1)
		if(isEmpty()){
			throw new EmptyListException("Lista vacia");
		}
		else{
			return (Position<E>)tail.getAnterior();
		}
	}
	
	public Position<E> prev(Position<E> p)throws InvalidPositionException,BoundaryViolationException{//O(1)
		NodoD<E> n=checkPosition(p);
		if(n.getAnterior()==head){
			throw new BoundaryViolationException("Lista:: prev() quiso buscar el anterior a la primera posicion");
		}
		else{
			return n.getAnterior();
		}
	}
	public Position<E> next(Position<E> p)throws InvalidPositionException,BoundaryViolationException{ //O(1)
		NodoD<E> n=checkPosition(p);
		if(n.getSiguiente()==tail){
			throw new BoundaryViolationException("Lista :: next() quiso encontrar la siguiente posicion a la ultima posicion");
		}
		else{
			return n.getSiguiente();
		}
	}
	
	private NodoD<E> checkPosition(Position<E>p)throws InvalidPositionException{ //O(1)
		try{
			if (p==null || isEmpty()){
				throw new InvalidPositionException("Posicion nula o lista vacia");
			}
			if(p==head){
				throw new InvalidPositionException("La cabeza no es una posicion valida!");
			}
			if(p==tail){
				throw new InvalidPositionException("La cola no es una osicion valida");
			}
			
			NodoD<E> aux=(NodoD<E>) p;
			if((aux.getSiguiente()==null)&& (aux.getAnterior()==null)){
				throw new InvalidPositionException("No es una posicion valida");
			}
			return aux;
			
		}
		catch(ClassCastException e){
			throw new InvalidPositionException("La posicion corresponde a otra lista");
		}
	}
	
	public NodoD<E> addFirst(E e){//O(1)
		
		NodoD<E> n=new NodoD<E>(e,head.getSiguiente(),head);
		
		head.getSiguiente().setAnterior(n);
		head.setSiguiente(n);
		longitud++;
		return n;
	}
	
	public void addLast(E e){ //O(1)
		NodoD<E> n=new NodoD<E>(e,tail,tail.getAnterior());
		tail.getAnterior().setSiguiente(n);
		tail.setAnterior(n);
		longitud++;
	}
	
	public void addAfter(Position<E> p,E e)throws InvalidPositionException{ //O(1)
		NodoD<E> n=checkPosition(p);
		if(n!=tail){    
			NodoD<E> a=new NodoD<E>(e,n.getSiguiente(),n);
			n.getSiguiente().setAnterior(a);
			n.setSiguiente(a);
			longitud++;
		}
	}
	
	public void addBefore(Position<E> p,E e)throws InvalidPositionException{//O(1)
		NodoD<E> n=checkPosition(p);
		NodoD<E> a=new NodoD<E>(e,null,null);
		if(n!=head){
			a.setSiguiente(n);
			a.setAnterior(n.getAnterior());
			n.getAnterior().setSiguiente(a);
			n.setAnterior(a);
			longitud++;
		}
	}
	public E remove(Position<E> p)throws InvalidPositionException{//O(1)
		if(isEmpty())
			throw new InvalidPositionException("Lista vacia");
		else{
			NodoD<E> n=checkPosition(p);
			E aux = null;
			if(n!=head && n!=tail){
				aux=n.element();
				n.getAnterior().setSiguiente(n.getSiguiente());
				n.getSiguiente().setAnterior(n.getAnterior());
				longitud--;
			}
		return aux;
		}
	}
	public E set(Position<E> p,E e)throws InvalidPositionException{ //O(1)
		NodoD<E> n=checkPosition(p);
		E aux=null;
		if(n!=head && n!=tail){
			aux= n.element();
			n.setElemento(e);
		}
		return aux;
	}
	public Iterator<E> iterator(){
		return new ElementIterator<E>(this);
	}

	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> P=new DoubleLinkedList<Position<E>>();
		try {
			if(!isEmpty()){
				Position<E> p=first();
				while(true){
					P.addLast(p);
					if(p==last())
						break;
					p=next(p);
				}
			}
		} 
		catch (InvalidPositionException | EmptyListException | BoundaryViolationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
		return P;
	}
		
	}

