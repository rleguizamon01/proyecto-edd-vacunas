package TDALista;

import java.lang.*;
import java.util.*;

public class ElementIterator<E> implements Iterator <E> {
	protected PositionList<E> list; // Lista a iterar
	protected Position<E> cursor; // Posición del elemento corriente
	
	public ElementIterator (PositionList <E> l ) {
		try {
			list = l; // Guardo la referencia a la lista a iterar
			if (list.isEmpty()) cursor = null; // Si la lista está vacía, la posición corriente es nula
			else cursor = list.first(); // Sino la posición corriente es la primera de la lista
		} catch( EmptyListException e ) {
			e.printStackTrace();
		}
		
	}
	// Hay siguiente si el cursor no está más allá de la última posición
	
	public boolean hasNext() { return cursor != null; }
	
	public E next () throws NoSuchElementException { 
		
		if ( cursor == null ) // Si el cursor es null, el cliente no testeó que hasNext fuera true
			throw new NoSuchElementException ("Error: No hay siguiente");
		E toReturn = cursor.element(); // Salvo el elemento corriente
		try {
		cursor = (cursor == list.last()) ? null : list.next(cursor); // Avanzo a la siguiente posición
		} catch( EmptyListException|InvalidPositionException|BoundaryViolationException e ) {
			e.printStackTrace();
		}
		return toReturn; // Retorno el elemento salvado
		
	}
	
	public void remove() { /* No lo haremos. */ }
	/* De hacerlo: Remueve el último ítem retornado por next(), no se puede llamar a remove() hasta 
	que no se haya ejecutado otro next(). Hay que agregar más control en las otras operaciones. */
}