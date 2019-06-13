package Exceptions;
/**
 * Exception utilizada cuando se introduce una variable no declarada previamente
 * @author Matias Kloster
 * @author Luciano Baschiera
 *
 */
public class ExcepcionVariableNoDeclarada extends Exception {
	/**
	 * Crea una instacia de la excepcion, la cual ocurre cuando se una variable no declarada previamente.
	 * @param msg Mensaje con informacion del error
	 */
	public ExcepcionVariableNoDeclarada(String msg){
		super(msg);	
	}
}