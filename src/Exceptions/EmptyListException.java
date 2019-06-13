package Exceptions;
/**
 * Exception utilizada cuando la lista se encuentra vacia
 * @author Matias Kloster
 * @author Luciano Baschiera
 *
 */
public class EmptyListException extends Exception {
	/**
	 * Crea una instacia de la excepcion, la cual ocurre en caso de que la lista a utilizar este vacia.
	 * @param msg Mensaje con informacion del error
	 */
	public EmptyListException(String msg){
		super(msg);
	}
}
