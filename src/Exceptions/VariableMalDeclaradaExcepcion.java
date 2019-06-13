package Exceptions;
/**
 * Exception utilizada cuando la variable que se intenta declarar es invalida.
 * @author Matias Kloster
 * @author Luciano Baschiera
 *
 */
public class VariableMalDeclaradaExcepcion extends Exception {
	/**
	 * Crea una instacia de la excepcion, la cual ocurre cuando se quiere ingresar un identificador invalido para una variable.
	 * @param msg Mensaje con informacion del error
	 */
	public VariableMalDeclaradaExcepcion(String msg){
		super(msg);	
	}
}

