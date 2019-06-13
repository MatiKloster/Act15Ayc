package TDACola;

public interface Queue<E> {
	//Insertar un elemento en la cola
	public void enqueue(E e);
	//Elimina el 1er elemento de la cola y lo retorna
	public E dequeue()throws EmptyQueueException;
	//retorna el 1er elemento de la cola
	public E front() throws EmptyQueueException;
	//TRUE si la cola esta vacia, FALSE en caso contrario
	public boolean isEmpty();
	//Retorna la cantidad de elementos de la cola
	public int size();
}
