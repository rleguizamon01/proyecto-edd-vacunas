package TDAPila;

/**
 * 
 * Estructura de datos que implementa la interfaz stack
 * @param <E> Tipo de dato que contiene la pila
 */

public class PilaConEnlaces<E> implements Stack<E>{
	
	protected Nodo<E> head;
	protected int tama�o;
	
	/**
	 *  Constructor de una pila con enlaces
	 */
	public PilaConEnlaces(){
		head = null;
		tama�o = 0;
	}
	@Override
	public void push(E item) {
		head = new Nodo<E>(item, head);
		tama�o++;
	}
	@Override
	public boolean isEmpty() {
		return head == null;
	}
	@Override
	public E pop() throws EmptyStackException{
		if(isEmpty())
			throw new EmptyStackException("La pila est� vac�a");
		E aux = head.getElemento();
		head = head.getSiguiente();
		tama�o--;
		return aux;	
	}
	
	@Override
	public int size() {
		return tama�o;
	}
	
	@Override
	public E top() throws EmptyStackException{
		if(isEmpty())
			throw new EmptyStackException("La pila est� vac�a");
		return head.getElemento();
	}
	
}
