package TDAColaCP;

/**
 * 
 * Comparador de objetos
 * @param <E> Tipo de dato de objetos que compara
 */
public class Comparador<E> implements java.util.Comparator<E> {

	@Override
	public int compare(E o1, E o2) {
		
		return ((Comparable<E>) o1).compareTo(o2);
	}
	

}