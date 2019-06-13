package Exceptions;
/**
 * Clase EmptyTreeException
 * Extiende a Exception
 * Utilizada para lanzar excepciones en caso de que el árbol a utilizar se encuentre vacío.
 * @author Matias Kloster
 * @author Luciano Baschiera
 *
 */
public class EmptyTreeException extends Exception {
	/**
	 * Crea una instacia de la excepcion, la cual ocurre en caso de que el árbol a utilizar se encuentre vacío.
	 * @param msg Mensaje con informacion del error
	 */
	public EmptyTreeException(String msg){
		super(msg);
	}
}
