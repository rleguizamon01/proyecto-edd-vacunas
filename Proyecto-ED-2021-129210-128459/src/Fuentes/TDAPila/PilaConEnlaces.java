package TDAPila;

/**
 * 
 * Estructura de datos que implementa la interfaz stack
 * @param <E> Tipo de dato que contiene la pila
 */

public class PilaConEnlaces<E> implements Stack<E>{
	
	protected Nodo<E> head;
	protected int tamaño;
	
	/**
	 *  Constructor de una pila con enlaces
	 */
	public PilaConEnlaces(){
		head = null;
		tamaño = 0;
	}
	@Override
	public void push(E item) {
		head = new Nodo<E>(item, head);
		tamaño++;
	}
	@Override
	public boolean isEmpty() {
		return head == null;
	}
	@Override
	public E pop() throws EmptyStackException{
		if(isEmpty())
			throw new EmptyStackException("La pila está vacía");
		E aux = head.getElemento();
		head = head.getSiguiente();
		tamaño--;
		return aux;	
	}
	
	@Override
	public int size() {
		return tamaño;
	}
	
	@Override
	public E top() throws EmptyStackException{
		if(isEmpty())
			throw new EmptyStackException("La pila está vacía");
		return head.getElemento();
	}
	
}
