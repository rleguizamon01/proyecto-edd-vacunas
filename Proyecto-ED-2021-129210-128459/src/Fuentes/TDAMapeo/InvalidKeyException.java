package TDAMapeo;
/**
 * Se lanza cuando la llave ingresada en el mapeo es inv�lido
 * @author teche
 *
 */
public class InvalidKeyException extends Exception {
	/**
	 * Inicializa una instancia de InvalidKeyException y muestra un mensaje
	 * @param msg mensaje de la excepci�n
	 */
	public InvalidKeyException(String msg) {
		super(msg);
	}
}
