package Exceptions;
/**
 * Exception utilizada cuando los parentesis de una expresión no están balanceados apropiadamente
 * @author Matias Kloster
 * @author Luciano Baschiera
 *
 */
public class ExcepcionParentesisMalBalanceados extends Exception {
	/**
	 * Crea una instacia de la excepcion, la cual ocurre cuando los parentesis de una expresión no están balanceados apropiadamente.
	 * @param msg Mensaje con informacion del error
	 */
	public ExcepcionParentesisMalBalanceados(String msg){
		super(msg);	
	}
}