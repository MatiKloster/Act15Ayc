package TDALista;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

public class SimpleLinkedList<E> implements PositionList<E>{
	protected Nodo <E> head;
	protected int longitud;
	
	
	public SimpleLinkedList(){
		this.head=null;
		longitud=0;
	}
	
	
	public int size(){return longitud;}
	
	public boolean isEmpty(){return longitud==0;}
	
	public Position<E> first()throws EmptyListException{
		if(isEmpty()){
			throw new EmptyListException("Lissta vacia");
		}
		else{
			return (Position<E>) head;
		}
	}
	
	public Position<E> last()throws EmptyListException{
		if(isEmpty()){
			throw new EmptyListException("Lista vacia");
		}
		else{
			Nodo<E> p=head;
			while(p.getSiguiente()!=null){
					p=p.getSiguiente();
			}
			return p;		
		}
	}
	
	public Position<E> prev(Position<E> p)throws InvalidPositionException,BoundaryViolationException{
		Nodo<E> aux=null;
		try{
			if(!isEmpty() && p==first()){
				throw new BoundaryViolationException(" Lista::prev(): " +"Posición primera");
			}
			else{
				Nodo<E> n=checkPosition(p);
				aux=head;
				while(aux.getSiguiente()!=n && aux.getSiguiente()!=null){
					aux=aux.getSiguiente();
				}
				if(aux.getSiguiente()==null){
					throw new InvalidPositionException(" Lista::prev(): " +"Posición no pertenece a la lista");
				}
			}
		}
		catch(EmptyListException e){
			System.out.println(e.getMessage());
		}
		return aux;
	}
	
	
	public Position<E> next(Position<E> p)throws InvalidPositionException,BoundaryViolationException{
		 Nodo<E> n=checkPosition(p);
		 if(n.getSiguiente()==null){
			 throw new BoundaryViolationException("No existe el siguiente elemento del ultimo elemento");
		 }
		 else{
			 return n.getSiguiente();
		 }
	}
	
	private Nodo<E> checkPosition(Position<E>p)throws InvalidPositionException{
		try{
			if (p==null ||isEmpty()){
				throw new InvalidPositionException("Posicion nula");
			}
			return (Nodo<E>) p;
		}
		catch(ClassCastException e){
			throw new InvalidPositionException("La posicion corresponde a otra lista");
		}
	}
	
	public void addFirst(E e){
		head=new Nodo<E>(e,head);
		longitud++;
	}
	public void addLast(E e){
		if(isEmpty()){addFirst(e);
		}
		else{
			Nodo<E> aux=head;
			while(aux.getSiguiente()!=null){
				aux=aux.getSiguiente();
			}
			aux.setSiguiente(new Nodo<E>(e,null));
			longitud++;
		}
	}
	public void addAfter(Position<E> p,E e)throws InvalidPositionException{
		if(isEmpty()){
			throw new InvalidPositionException("Lista vacia");
		}
		else{
			Nodo<E> n=checkPosition(p);
			Nodo<E> nuevo=new Nodo<E>(e);
			nuevo.setSiguiente(n.getSiguiente());
			n.setSiguiente(nuevo);
			longitud++;
		}
	}
	public void addBefore(Position<E> p,E e)throws InvalidPositionException{
		Nodo<E> n=checkPosition(p);
		try {
			if(p==first()){
				addFirst(e);
			}
			else{
				Nodo<E> anterior=(Nodo<E>)prev(p);
				Nodo<E> nuevo=new Nodo<E>(e,n);
				anterior.setSiguiente(nuevo);
				longitud++;
			}
		} catch (EmptyListException | BoundaryViolationException e1) {
			System.out.println(e1.getMessage());
		}	
	}
	public E remove(Position<E> p)throws InvalidPositionException{
		if(isEmpty()){
			throw new InvalidPositionException("Lista vacia");
		}
		else{
			Nodo<E>n=checkPosition(p);
			E aux=p.element();
			if(n==head){
				head=head.getSiguiente();
			}
			else{
				try{
					Nodo<E> anterior=(Nodo<E>)prev(p);
					anterior.setSiguiente(n.getSiguiente());
				}
				catch(BoundaryViolationException e){
					System.out.println(e.getMessage());
				}

			}
			longitud--;
			return aux;
		}
	}
	public E set(Position<E> p,E e)throws InvalidPositionException{
		Nodo<E> n=checkPosition(p);
		E aux=p.element();
		n.setElemento(e);
		return aux;
	}public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}


	@Override
	public Iterable<Position<E>> positions() {
			PositionList<Position<E>> P=new SimpleLinkedList<Position<E>>();
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