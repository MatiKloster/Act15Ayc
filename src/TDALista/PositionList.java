package TDALista;
import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;
/**
 * Interface PositionList
 * @author Matias Kloster
 * @author Luciano Baschiera
 *
 * @param <E>
 */
public interface PositionList<E> extends Iterable<E>{
	/** Consulta la cantidad de elementos que tiene la lista
	 * @return la cantidad de elementos que tiene la lista 
	 */
	public int size();
	
	/** Consulta si la lista esta vacia
	 * @return true si la lista esta vacia, false en caso contrario 
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve la posicion del primer elemento de la lista
	 * @return La posicion del primer elemento de la lista;
	 * @throws EmptyListException si la lista esta vacia
	 */
	public Position<E> first() throws EmptyListException;
	
	/**
	 * Devuelve la posicion del ultimo elemento de la lista
	 * @return La posicion del ultimo elemento de la lista
	 * @throws EmptyListException si la lista esta vacia.
	 */
	public Position<E> last() throws EmptyListException;
	
	/**
	 * Devuelve la posicion anterior a una posicion ingresada como paramentro
	 * @param p  Posicion de la cual se desea obtener el anterior
	 * @return La posicion del elemento que predece al elemento en la posicion p
	 * @throws InvalidPositionException si la posicion pasada por paramentro es nula o la lista esta vacia
	 * @throws BoundaryViolationException si la posicion pasada por paramentro es el primer elemento de la lista
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException,BoundaryViolationException;
	
	/**
	 * Devuelve la posicion siguiente a una posicion ingresada como paramentro
	 * @param p Posicion de la cual se desea obtener el siguiente
	 * @return La posicion del elemento siguiente a la posicion pasada como parametro
	 * @throws InvalidPositionException si la posicion pasada como paramentro es nula o la lista esta vacia
	 * @throws BoundaryViolationException si la posicion pasada por parametro es el ultimo elemento de la lista
	 */
	public Position<E> next(Position<E> p) throws InvalidPositionException,BoundaryViolationException;
	
	/**
	 * Inserta un elemento en la primera posicion de la lista
	 * @param e Elemento a ingresar al principio de la lista
	 */
	public void addFirst(E e);
	
	/**
	 * Inserta un elemento en la ultima posicion de la lista
	 * @param e Elemento a ingresar al final de la lista
	 */
	public void addLast(E e);
	
	/**
	 * Inserta un elemento luego de una posicion pasada como paramentro
	 * @param p Posicion en cuya posicion siguiente se insertara el elemento
	 * @param e Elemento a insertar luego de la posicion pasada como paramentro
	 * @throws InvalidPositionException si la posicion pasada como paramentro es nula, no pertenece a la lista, o la lista esta vacia
	 */
	public void addAfter(Position<E> p,E e)throws InvalidPositionException;
	
	/**
	 * Inserta un elemento antes de una posicion pasada como paramentro
	 * @param p Posicion en cuya posicion anterior se insertara el elemento
	 * @param e Elemento a insertar antes de la posicion pasada como paramentro
	 * @throws InvalidPositionException si la posicion pasada como paramentro es nula, no pertenece a la lista, o la lista esta vacia
	 */
	public void addBefore(Position<E> p,E e)throws InvalidPositionException;
	
	/**
	 * Remueve un elemento de la lista, retornandolo
	 * @param p Posicion del elemento a remover
	 * @return Elemento removido de la lista
	 * @throws InvalidPositionException si la posicion es nula,no pertence a la lista, o la lista esta vacia
	 */
	public E remove(Position<E>p)throws InvalidPositionException;
	
	/**
	 *  Establece el elemento en la posición pasados por parámetro. Reemplaza el elemento que se encontraba anteriormente en esa posición y devuelve el elemento anterior
	 * @param p Posicion del elemento a reemplzar
	 * @return e Elemento que estaba antes en la posicion p
	 * @throws InvalidPositionException si la posicion es nula, no pertence a la lista, o la lista esta vacia
	 */
	public E set(Position<E> p,E e)throws InvalidPositionException;
	/**
	 * Devuelve un iterador con todos los elementos de la lista
	 * @return Iterador con todos los elementos de la lista
	 */
	public Iterator<E> iterator();
	/**
	 * Devuelve una coleccion iterable con todas las posiciones de la lista
	 * @return Coleccion iterable con todas las posiciones de la list
	 */
	public Iterable<Position<E>> positions();
}
