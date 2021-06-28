package TDALista;

/**
 * Se dispara cuando se trata de acceder a una posicion de la lista que no existe
 *
 */
public class BoundaryViolationException extends Exception {
	
	/**
	 * 
	 * @param s mensaje de la excepcion
	 */
	public BoundaryViolationException (String msj) {
		super(msj);
	}
}

