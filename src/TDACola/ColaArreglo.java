package TDACola;
public class ColaArreglo<E> implements Queue<E> {
	protected E[] arr;
	protected int f,r;
	public ColaArreglo(){
		arr=(E[]) new Object[10000];
		f=0;r=0;
	}
	//Comandos
	public void enqueue(E e){
		arr[r]=e;
		r=(r+1) % arr.length;
	}
	//Consultas
	public E dequeue() throws EmptyQueueException{
		if(this.isEmpty()){
			throw new EmptyQueueException("Cola vacia");
		}
		else{
			E aux=arr[f];
			arr[f]=null;
			f=(f+1) % arr.length;
			return aux;
		}
	}
	public E front()throws EmptyQueueException{
		if(this.isEmpty()){
			throw new EmptyQueueException("Cola Vacia");
		}
		else{
			return arr[f];
		}
	}
	public boolean isEmpty(){
		return r==f;
	}
	public int size(){
		return (arr.length - f + r) % arr.length;
		
	}
}
