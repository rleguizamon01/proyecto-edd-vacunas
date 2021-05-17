package TDAPila;

public class PilaConEnlaces<E> implements Stack<E>{
	
	protected Nodo<E> head;
	protected int tamaño;
	
	public PilaConEnlaces(){
		head = null;
		tamaño = 0;
	}
	
	public void push(E item) {
		head = new Nodo<E>(item, head);
		tamaño++;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public E pop() throws EmptyStackException{
		if(isEmpty())
			throw new EmptyStackException("La pila está vacía");
		E aux = head.getElemento();
		head = head.getSiguiente();
		tamaño--;
		return aux;	
	}
	
	public int size() {
		return tamaño;
	}
	
	public E top() throws EmptyStackException{
		if(isEmpty())
			throw new EmptyStackException("La pila está vacía");
		return head.getElemento();
	}
}
