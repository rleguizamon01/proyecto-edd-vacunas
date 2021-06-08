package TDAPila;

public class Nodo<E> {
	private E elemento;
	private Nodo<E> siguiente;
	
	/**
	 * 
	 * @param item Item del nodo
	 * @param sig Siguiente nodo
	 */
	public Nodo(E item, Nodo<E> sig) {
		elemento = item;
		siguiente = sig;
	}
	
	/**
	 * 
	 * @param item Item del nodo
	 */
	public Nodo(E item) {
		this(item, null);
	}
	
	
	/**
	 * 
	 * @param elemento Elemento del nodo
	 */
	public void setElemento(E elemento) {
		this.elemento = elemento;
	}
	
	/**
	 * 
	 * @param siguiente Siguiente nodo
	 */
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
	
	/**
	 * 
	 * @return Elemento del nodo
	 */
	public E getElemento() {
		return elemento;
	}
	
	/**
	 * 
	 * @return Siguiente nodo
	 */
	public Nodo<E> getSiguiente(){
		return siguiente;
	}
	
	/**
	 * @return Una cadena que contiene el elemento
	 */
	public String toString() {
		return elemento.toString();
	}
}
