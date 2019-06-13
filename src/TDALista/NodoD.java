package TDALista;

public class NodoD<E> implements Position<E> {
	protected E elemento;
	protected NodoD<E> siguiente,anterior;
	public NodoD(E e,NodoD<E> sig,NodoD<E> ant){
		elemento=e;
		this.siguiente=sig;
		this.anterior=ant;
	}
	public NodoD(NodoD<E> sig,NodoD<E> ant){
		siguiente=sig;
		anterior=ant;
	}
	public void setElemento(E e){
		this.elemento=e;
	}
	public void setSiguiente(NodoD<E> sig){
		siguiente=sig;
	}
	public void setAnterior(NodoD<E> ant){
		anterior=ant;
	}
	public E element(){
		return elemento;
	}
	public NodoD<E> getSiguiente(){
		return siguiente;
	}
	public NodoD<E> getAnterior(){
		return anterior;
	}
	
}
