package TDACola;
public class Nodo<E> { 
	private E elemento; 
	private Nodo<E> siguiente; 
		
	public Nodo() {
		this(null, null); 
	}   
	public Nodo(E e) {
		this(e,null); 
	} 
	public Nodo( E e, Nodo<E> sig )  { 
		elemento=e; 
		siguiente=sig; 
	}		
	public E getElemento(){
		return elemento;
	}
	public void setElemento(E elemento){
		this.elemento=elemento;
	}
	public Nodo<E> getSiguiente(){
		return siguiente;
	}
	public void setSiguiente(Nodo<E>siguiente){
		this.siguiente=siguiente;
	}
}