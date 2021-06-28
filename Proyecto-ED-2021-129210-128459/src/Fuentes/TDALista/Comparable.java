package TDALista;

public interface Comparable<E> {
	
	/**
	 * 
	 * @param element Elemento con el que se va a comparar 
	 * @return Entero que depende de la comparacion realizada
	 */
	public int compareTo(E element);
	
}