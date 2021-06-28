package TDACola;
/**
 * Se dispara cuando la cola esta vacia
 *
 */

public class EmptyQueueException extends Exception {
	/**
	 * 
	 * @param s mensaje de la excepcion
	 */
	public EmptyQueueException(String s) {
		super(s);
	}
}
