package Exceptions;
/**
 * Exception utilizada cuando se introduce una clave invalida
 * @author Matias Kloster
 * @author Luciano Baschiera
 *
 */
public class InvalidKeyException extends Exception {
	/**
	 * Crea una instacia de la excepcion, la cual ocurre cuando quiero operar sobre un mapeo con una llave invalida.
	 * @param msg Mensaje con informacion del error
	 */
	public InvalidKeyException(String msg){
		super(msg);
	}
}
