package TDAColaCP;
/**
 * Se dispara cuando la cola esta vacia
 *
 */
public class EmptyPriorityQueueException extends Exception {
	/**
	 * 
	 * @param s mensaje de la excepcion
	 */
	public EmptyPriorityQueueException(String s) {
		super(s);
	}
}
