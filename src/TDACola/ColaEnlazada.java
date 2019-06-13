package TDACola;


public class ColaEnlazada<E> implements Queue<E> {
	protected Nodo<E> head,tail;  // cabeza y talon
	protected int tamaño;
	public ColaEnlazada(){
		head=null;
		tail=null;
		tamaño=0;
	}
	public void enqueue(E e){
		Nodo<E> aux=new Nodo<E>();
		aux.setElemento(e); // inicializo el nodo
		aux.setSiguiente(null);// lo apunto a null
		if (this.isEmpty()){ // Si la cola esta vacia, al elemento lo hago 'cabeza'
			head=aux;
		}
		else{ // y si no, al ultimo elemento que esta en la cola lo hago apuntar al nuevo que acaba de entrar (los enlazo) 
			tail.setSiguiente(aux);
		}
	tail=aux; // siempre que inserto un elemento va a ser el 'talon' de la cola
	tamaño++;
	}
	public E dequeue()throws EmptyQueueException{
		if(this.isEmpty()){
			throw new EmptyQueueException("Cola vacia");
		}
		else{
			E aux= head.getElemento();
			head=head.getSiguiente();
			tamaño--;
			if(this.isEmpty()){
				tail=null; //////// me tengo que asegurar que si saco un elemento y justo ese era el ultimo a tail le tengo que asignar null, sino me queda ligado 
			}
			return aux;
		}
	}
	public E front()throws EmptyQueueException{
		if(this.isEmpty()){
			throw new EmptyQueueException("Cola vacia");
		}
		else{
			return head.getElemento();
		}
	}
	public boolean isEmpty(){
		return tamaño==0;
	}
	public int size(){
		return tamaño;
	}
}
