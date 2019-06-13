package TDALista;
public class Nodo<E> implements Position<E>{ 
	private E elemento; 
	private Nodo<E> siguiente; 
		
	public Nodo() {
		this(null, null); 
	}   
	public Nodo(E e) {
		this(e,null); 
	} 
	public Nodo( E e, Nodo<E> sig )  { 
		this.elemento=e; 
		this.siguiente=sig; 
	}		
	public E element(){
		return this.elemento;
	}
	public void setElemento(E elemento){
		this.elemento=elemento;
	}
	public Nodo<E> getSiguiente(){
		return this.siguiente;
	}
	public void setSiguiente(Nodo<E>siguiente){
		this.siguiente=siguiente;
	}
}