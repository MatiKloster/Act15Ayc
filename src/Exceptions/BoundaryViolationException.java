package Exceptions;
/**
 * Clase BoundaryViolationException utilizada para lanzar excepciones en casos de sobrepaso de limites de las estructuras
 * de datos.
 * @author Matias Kloster
 * @author Luciano Baschiera
 */
public class BoundaryViolationException extends Exception {
	/**
	 * Crea una instacia de la excepcion, la cual ocurre en casos de sobrepaso de limites de las estructuras.
	 * @param msg Mensaje con informacion del error
	 */
	public BoundaryViolationException(String msg){
		super(msg);	
	}
}
