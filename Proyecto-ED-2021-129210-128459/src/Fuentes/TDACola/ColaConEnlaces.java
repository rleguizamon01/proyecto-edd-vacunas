package TDACola;

public class ColaConEnlaces<E> implements Queue<E>{
	
	protected Nodo<E> head,tail;
	protected int tam;
	
	
	public ColaConEnlaces() {
		head = tail = null;
		tam = 0;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return tam;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return tam==0;
	}

	@Override
	public E front() throws EmptyQueueException {
		if(tam == 0)
			 throw new EmptyQueueException("La cola est� vac�a");
		else
			return head.getElemento();
	}

	@Override
	public void enqueue(E element) {
		 Nodo<E> nodo = new Nodo<E>(element);
		 if (tam == 0)
			 head = nodo;
		 else
			 tail.setSiguiente( nodo );
		 tail = nodo;
		 tam++;
		
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		 if(tam == 0)
			 throw new EmptyQueueException("La cola est� vac�a");
		 E aux = head.getElemento();
		 head = head.getSiguiente();
		 tam--;
		 if(tam == 0)
			 tail = null;
		 return aux;
	}

}
