package TDAColaCP;

public interface Entry<K,V> {
	/**
	 * @return Clave de la entrada
	 */
	public K getKey();
	
	/**
	 * @return Valor de la entrada
	 */
	public V getValue();
	
	/**
	 * @return Una cadena conteniendo la clave y el valor
	 */
	public String toString();
}
