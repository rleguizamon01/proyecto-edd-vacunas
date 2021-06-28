package TDAMapeo;
/**
 * Se lanza cuando la llave ingresada en el mapeo es inválido
 * @author teche
 *
 */
public class InvalidKeyException extends Exception {
	/**
	 * Inicializa una instancia de InvalidKeyException y muestra un mensaje
	 * @param msg mensaje de la excepción
	 */
	public InvalidKeyException(String msg) {
		super(msg);
	}
}
