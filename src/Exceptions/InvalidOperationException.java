package Exceptions;
/**
 * Clase InvalidOperationException
 * Extiende a Exception
 * Utilizada para lanzar excepciones en caso de realizar operaciones invalidas.
 * @author Matias Kloster
 * @author Luciano Baschiera
 *
 */
public class InvalidOperationException extends Exception {
	/**
	 * Crea una instacia de la excepcion, la cual ocurre cuando no se puede realizar determinada operacion(p.ej dividir sobre 0).
	 * @param msg Mensaje con informacion del error
	 */
	public InvalidOperationException(String msg){
		super(msg);	
	}
}