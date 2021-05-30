package TDALista;

import java.util.Iterator;

/**
 * Interface PositionList
 * @author Ctedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computacin, UNS.
 */

public interface PositionList<E> extends Iterable<E>
{
	/**
	 * Consulta la cantidad de elementos de la lista.
	 * @return Cantidad de elementos de la lista.
	 */
	public int size();
	
	/**
	 * Consulta si la lista est vaca.
	 * @return Verdadero si la lista est vaca, falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve la posicin del primer elemento de la lista. 
	 * @return Posicin del primer elemento de la lista.
	 * @throws EmptyListException si la lista est vaca.
	 */
	public Position<E> first() throws EmptyListException;
	
	/**
	 * Devuelve la posicin del ltimo elemento de la lista. 
	 * @return Posicin del ltimo elemento de la lista.
	 * @throws EmptyListException si la lista est vaca.
	 * 
	 */
	public Position<E> last() throws EmptyListException;;
	
	/**
	 * Devuelve la posicin del elemento siguiente a la posicin pasada por parmetro.
	 * @param p Posicin a obtener su elemento siguiente.
	 * @return Posicin del elemento siguiente a la posicin pasada por parmetro.
	 * @throws InvalidPositionException si el posicin pasada por parmetro es invlida o la lista est vaca.
	 * @throws BoundaryViolationException si la posicin pasada por parmetro corresponde al ltimo elemento de la lista.
	 */
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve la posicin del elemento anterior a la posicin pasada por parmetro.
	 * @param p Posicin a obtener su elemento anterior.
	 * @return Posicin del elemento anterior a la posicin pasada por parmetro.
	 * @throws InvalidPositionException si la posicin pasada por parmetro es invlida o la lista est vaca.
	 * @throws BoundaryViolationException si la posicin pasada por parmetro corresponde al primer elemento de la lista.
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Inserta un elemento al principio de la lista.
	 * @param element Elemento a insertar al principio de la lista.
	 */
	public void addFirst(E element);
	
	/**
	 * Inserta un elemento al final de la lista.
	 * @param element Elemento a insertar al final de la lista.
	 */
	public void addLast(E element);
	
	/**
	 * Inserta un elemento luego de la posicin pasada por parmatro.
	 * @param p Posicin en cuya posicin siguiente se insertar el elemento pasado por parmetro.
	 * @param element Elemento a insertar luego de la posicin pasada como parmetro.
	 * @throws InvalidPositionException si la posicin es invlida o la lista est vaca.
	 */
	public void addAfter(Position<E> p, E element) throws InvalidPositionException;
	
	/**
	 * Inserta un elemento antes de la posicin pasada como parmetro.
	 * @param p Posicin en cuya posicin anterior se insertar el elemento pasado por parmetro. 
	 * @param element Elemento a insertar antes de la posicin pasada como parmetro.
	 * @throws InvalidPositionException si la posicin es invlida o la lista est vaca.
	 */
	public void addBefore(Position<E> p, E element) throws InvalidPositionException;
	
	/**
	 * Remueve el elemento que se encuentra en la posicin pasada por parmetro.
	 * @param p Posicin del elemento a eliminar.
	 * @return element Elemento removido.
	 * @throws InvalidPositionException si la posicin es invlida o la lista est vaca.
	 */	
	public E remove(Position<E> p) throws InvalidPositionException;

	/**
	
	 * Establece el elemento en la posicin pasados por parmetro. Reemplaza el elemento que se encontraba anteriormente en esa posicin y devuelve el elemento anterior.
	 * @param p Posicin a establecer el elemento pasado por parmetro.
	 * @param element Elemento a establecer en la posicin pasada por parmetro.
	 * @return Elemento anterior.
	 * @throws InvalidPositionException si la posicin es invlida o la lista est vaca.	 
	 */
	public E set(Position<E> p, E element) throws InvalidPositionException;
	
	/**
	 * Devuelve un un iterador de todos los elementos de la lista.
	 * @return Un iterador de todos los elementos de la lista.
	 */
	public Iterator<E> iterator();
	
	/**
	 * Devuelve una coleccin iterable de posiciones.
	 * @return Una coleccin iterable de posiciones.
	 */
	public Iterable<Position<E>> positions();
}