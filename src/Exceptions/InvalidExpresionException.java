package Exceptions;
/**
 * Exception utilizada cuando se introduce una expresión invalida.
 * @author Matias Kloster
 * @author Luciano Baschiera
 *
 */
public class InvalidExpresionException extends Exception {
	/**
	 * Crea una instacia de la excepcion, la cual ocurre cuando se introduce una expresion invalida.
	 * @param msg Mensaje con informacion del error
	 */
	public InvalidExpresionException(String msg){
		super(msg);	
	}
}
