package TDAColaCP;

public class ComparadorInverso<E> implements java.util.Comparator<E> {

	@Override
	public int compare(E o1, E o2) {
		
		return ((Comparable<E>) o2).compareTo(o1);
	}
	
	

}