package TDAPila;

public class PilaConEnlaces<E> implements Stack<E>{
	
	protected Nodo<E> head;
	protected int tama�o;
	
	public PilaConEnlaces(){
		head = null;
		tama�o = 0;
	}
	
	public void push(E item) {
		head = new Nodo<E>(item, head);
		tama�o++;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public E pop() throws EmptyStackException{
		if(isEmpty())
			throw new EmptyStackException("La pila est� vac�a");
		E aux = head.getElemento();
		head = head.getSiguiente();
		tama�o--;
		return aux;	
	}
	
	public int size() {
		return tama�o;
	}
	
	public E top() throws EmptyStackException{
		if(isEmpty())
			throw new EmptyStackException("La pila est� vac�a");
		return head.getElemento();
	}
}
