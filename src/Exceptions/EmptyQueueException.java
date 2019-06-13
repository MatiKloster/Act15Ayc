package Exceptions;
/**
 * Clase EmptyQueueException, utilizada para lanzar una excepcion de cola vacia
 * @author Matias Kloster
 * @author Luciano Baschiera
 *
 */
public class EmptyQueueException extends Exception{
	/**
	 * Crea una instacia de la excepcion, la cual ocurre en caso de que la cola a utilizar este vacia.
	 * @param msg Mensaje con informacion del error
	 */
	public EmptyQueueException(String msg){
		super(msg);
	}
}
