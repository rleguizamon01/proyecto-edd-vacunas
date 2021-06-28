package TDALista;
/**
 * Se dispara cuando la lista esta vacia
 *
 */
public class EmptyListException extends Exception {
	/**
	 * 
	 * @param s mensaje de la excepcion
	 */
	public EmptyListException (String s) {
		super(s);
	}
}

