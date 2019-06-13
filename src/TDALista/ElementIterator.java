package TDALista;
import java.util.*;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;
public class ElementIterator<E> implements Iterator <E> {
	protected PositionList<E> list; // Lista a iterrar
	protected Position<E> cursor; // Posición del elemento corriente
	public ElementIterator (PositionList <E> l ){
		list = l;
		if (list.isEmpty()) cursor = null;
		else
			try {
				cursor = list.first();
			} catch (EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public boolean hasNext() { return cursor != null; }
	public E next () throws NoSuchElementException {
		if ( cursor == null )
			throw new NoSuchElementException ("no tiene siguiente");
		E toReturn = cursor.element();
		try {
			cursor = (cursor == list.last()) ? null : list.next(cursor);
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;
	}
}