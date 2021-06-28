package TDAColaCP;

/**
 * Se dispara cuando se trata de crear una entrada con clave nula
 *
 */
public class InvalidKeyException extends Exception {
	/**
	 * 
	 * @param s mensaje de la excepcion
	 */
	public InvalidKeyException(String msg) {
		super(msg);
	}
}
