package TDALista;

/**
 * Se dispara cuando se trata de acceder a una posicion nula
 *
 */
public class InvalidPositionException extends Exception {
	
	/**
	 * 
	 * @param s mensaje de la excepcion
	 */
	public InvalidPositionException (String msj) {
		super(msj);
	}
}

