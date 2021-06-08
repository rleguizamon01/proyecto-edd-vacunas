package TDAMapeo;

public interface Entry<K,V> {
	/**
	 * Devuelve la llave de la entrada
	 * @return llave de la entrada
	 */
	public K getKey();
	
	/**
	 * Devuelve el valor de la entrada
	 * @return valor de la entrada
	 */
	public V getValue();
	
	/**
	 * Devuelve una cadena mostrando la entrada
	 * @return cadena de la entrada
	 */
	public String toString();
}
