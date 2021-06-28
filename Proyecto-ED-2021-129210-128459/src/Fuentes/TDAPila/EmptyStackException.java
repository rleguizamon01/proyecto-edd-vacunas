package TDAPila;

/**
 * Se dispara cuando la pila esta vacia
 *
 */

public class EmptyStackException extends Exception{
	
	/**
	 * 
	 * @param s mensaje de la excepcion
	 */
	public EmptyStackException(String s) {
		super(s);
	}
}
