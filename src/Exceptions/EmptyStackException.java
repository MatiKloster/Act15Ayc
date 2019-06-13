package Exceptions;
/**
 * Exception utilizada cuando la pila se encuentra vacia
 * @author Matias Kloster
 * @author Luciano Baschiera
 *
 */
public class EmptyStackException extends Exception{
	/**
	 * Crea una instacia de la excepcion, la cual ocurre cuando la pila se encuentra vacia.
	 * @param msg Mensaje con informacion del error
	 */
	public EmptyStackException(String msg){
		super(msg);
	}

}

