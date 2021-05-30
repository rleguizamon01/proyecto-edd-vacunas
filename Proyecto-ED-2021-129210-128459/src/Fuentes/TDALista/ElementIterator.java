package TDALista;

import java.lang.*;
import java.util.*;

public class ElementIterator<E> implements Iterator <E> {
	protected PositionList<E> list; // Lista a iterar
	protected Position<E> cursor; // Posici�n del elemento corriente
	
	public ElementIterator (PositionList <E> l ) {
		try {
			list = l; // Guardo la referencia a la lista a iterar
			if (list.isEmpty()) cursor = null; // Si la lista est� vac�a, la posici�n corriente es nula
			else cursor = list.first(); // Sino la posici�n corriente es la primera de la lista
		} catch( EmptyListException e ) {
			e.printStackTrace();
		}
		
	}
	// Hay siguiente si el cursor no est� m�s all� de la �ltima posici�n
	
	public boolean hasNext() { return cursor != null; }
	
	public E next () throws NoSuchElementException { 
		
		if ( cursor == null ) // Si el cursor es null, el cliente no teste� que hasNext fuera true
			throw new NoSuchElementException ("Error: No hay siguiente");
		E toReturn = cursor.element(); // Salvo el elemento corriente
		try {
		cursor = (cursor == list.last()) ? null : list.next(cursor); // Avanzo a la siguiente posici�n
		} catch( EmptyListException|InvalidPositionException|BoundaryViolationException e ) {
			e.printStackTrace();
		}
		return toReturn; // Retorno el elemento salvado
		
	}
	
	public void remove() { /* No lo haremos. */ }
	/* De hacerlo: Remueve el �ltimo �tem retornado por next(), no se puede llamar a remove() hasta 
	que no se haya ejecutado otro next(). Hay que agregar m�s control en las otras operaciones. */
}