package TDAColaPrioridad;
/**
 * Exception utilizada cuando se quiere operar sobre una Cola con prioridades vacia
 * @author Matias Kloster
 *
 */
public class EmptyPriorityQueueException extends Exception {
	/**
	 * Crea una instacia de la excepcion, la cual ocurre cuando se quiere operar sobre una Cola con prioridades vacia.
	 * @param msg Mensaje con informacion del error
	 */
	public EmptyPriorityQueueException(String msg){
		super(msg);
	}
}