package TDALista;

/**
 * Comparador de objetos
 * @param <E> Tipo de datos de los objetos a comparar
 */
public class DefaultComparator<E> implements java.util.Comparator<E> {
	
	public int compare( E a, E b ) throws ClassCastException {
		return ((Comparable<E>)a).compareTo(b);
	}
}