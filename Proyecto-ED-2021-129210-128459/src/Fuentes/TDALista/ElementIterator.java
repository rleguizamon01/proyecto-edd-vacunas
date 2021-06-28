package TDALista;

import java.lang.*;
import java.util.*;

public class ElementIterator<E> implements Iterator <E> {
	protected PositionList<E> list;
	protected Position<E> cursor;
	
	public ElementIterator (PositionList <E> l ) {
		try {
			list = l;
			if (list.isEmpty()) cursor = null;
			else cursor = list.first();
		} catch( EmptyListException e ) {
			e.printStackTrace();
		}
		
	}
	
	public boolean hasNext() { return cursor != null; }
	
	public E next () throws NoSuchElementException { 
		
		if ( cursor == null ) 
			throw new NoSuchElementException ("Error: No hay siguiente");
		E toReturn = cursor.element(); 
		try {
		cursor = (cursor == list.last()) ? null : list.next(cursor); 
		} catch( EmptyListException|InvalidPositionException|BoundaryViolationException e ) {
			e.printStackTrace();
		}
		return toReturn; 
		
	}
	
	public void remove() {}
	
}