package Exceptions;
/**
 * Clase InvalidPositionException
 * Extiende a Exception
 * Utilizada para lanzar excepciones en caso de utilizar posiciones invalidas en un método.
 * @author Matias Kloster
 * @author Luciano Baschiera
 *
 */
public class InvalidPositionException extends Exception{
	/**
	 * Crea una instacia de la excepcion, la cual ocurre cuando algun metodo quiere operar sobre una posicion invalida.
	 * @param msg Mensaje con informacion del error
	 */
	public InvalidPositionException(String msg){
		super(msg);
	}
}
