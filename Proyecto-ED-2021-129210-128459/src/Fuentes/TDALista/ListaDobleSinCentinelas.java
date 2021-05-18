package TDALista;

import java.util.Iterator;

public class ListaDobleSinCentinelas<E> implements PositionList<E>{
	
	private class DNodo<E> implements Position<E>{
		
		private E elemento;
		private DNodo<E> siguiente,previo;
		
		// Constructores:
		public DNodo( E item, DNodo<E> sig, DNodo<E> prev  ) {
			elemento=item;
			siguiente=sig;
			previo=prev;
		} 
		
		public DNodo( E item ) { 
			this(item, null, null); 
		
		}
		public DNodo() { 
			this(null, null, null); 
		
		}
		
		// Setters:
		public void setElemento( E elemento ){ 
			this.elemento = elemento; 
		}
		
		public void setSiguiente( DNodo<E> siguiente ){ 
			this.siguiente = siguiente; 
		}
		
		public void setPrevio( DNodo<E> prev ){ 
			previo=prev;
		}
		
		// Getters:
		public E getElemento() { 
			return elemento; 
		}
		
		public DNodo<E> getSiguiente() { 
			return siguiente; 
		}
		
		public DNodo<E> getPrevio() { 
			return previo; 
		}
		@Override
		public E element() {
			return elemento;
		}
		
	}
		
	protected DNodo<E> cabeza,rabo;
	protected int tamaño;
	
	public ListaDobleSinCentinelas() {
		cabeza = null;
		rabo = null;
		tamaño = 0;
	}
	
	@Override
	public int size() {
		return tamaño;
	}

	@Override
	public boolean isEmpty() {
		return tamaño==0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if (tamaño==0)
			throw new EmptyListException("lista vacia");
		return cabeza;
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if (tamaño==0)
			throw new EmptyListException("lista vacia");
		return rabo;
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		
		DNodo<E> pos= checkPosition(p);
		if (pos==rabo)
				throw new BoundaryViolationException("es la ultima posicion");
		return pos.getSiguiente();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> pos= checkPosition(p);
		if (pos==cabeza)
			throw new BoundaryViolationException("es la primera posicion");
		return pos.getPrevio();
	}

	@Override
	public void addFirst(E element) {
		DNodo<E> nuevo = new DNodo<E>(element);
		
		if (tamaño==0) {
			nuevo.setSiguiente(null);
			nuevo.setPrevio(null);
			cabeza=nuevo;
			rabo=nuevo;
		} else {
			nuevo.setPrevio(null);
			nuevo.setSiguiente(cabeza);
			cabeza.setPrevio(nuevo);
			cabeza=nuevo;
		}
		tamaño++;
	}

	@Override
	public void addLast(E element) {
		DNodo<E> nuevo = new DNodo<E>(element);
		
		if (tamaño==0) {
			nuevo.setSiguiente(null);
			nuevo.setPrevio(null);
			cabeza=nuevo;
			rabo=nuevo;
		} else {
			nuevo.setSiguiente(null);
			nuevo.setPrevio(rabo);
			rabo.setSiguiente(nuevo);
			rabo=nuevo;
		}
		tamaño++;
	}

	@Override
	public void addAfter(Position<E> p, E element)throws InvalidPositionException {
		DNodo<E> pos = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(element);
		nuevo.setSiguiente(pos.getSiguiente());
		nuevo.setPrevio(pos);
		if (pos==rabo) {
			rabo=nuevo;
		} else {
			nuevo.getSiguiente().setPrevio(nuevo);
		}
		pos.setSiguiente(nuevo);
		
		tamaño++;
	}

	private DNodo<E> checkPosition( Position<E> p ) throws InvalidPositionException {
		try {
			if( p == null ) throw new InvalidPositionException("Posición nula");
			if (p.element() == null) throw new InvalidPositionException("p eliminada previamente");
			return (DNodo<E>) p;
		} catch( ClassCastException e ) {
			throw new InvalidPositionException( "p no es un nodo de lista" );
		}
}
	
	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> pos = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(element);
		nuevo.setPrevio(pos.getPrevio());
		nuevo.setSiguiente(pos);
		if (pos==cabeza) {
			cabeza=nuevo;
		}else {
			nuevo.getPrevio().setSiguiente(nuevo);
		}
		pos.setPrevio(nuevo);
		
		tamaño++;
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {

		DNodo<E> n = checkPosition(p);
		
		if(n==cabeza && n==rabo) {
			cabeza=null;
			rabo=null;
		}else if(n==cabeza) {
			n.getSiguiente().setPrevio(null);
			cabeza=n.getSiguiente();
		}else if(n==rabo) {
			n.getPrevio().setSiguiente(null);
			rabo=n.getPrevio();
		}else {
			n.getPrevio().setSiguiente(n.getSiguiente());
			n.getSiguiente().setPrevio(n.getPrevio());
		}
		tamaño--;
		E aux = p.element(); 
		n.setElemento(null); 
		n.setSiguiente(null);
		n.setPrevio(null); 
		return aux;
		
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);
		E aux = p.element(); 
		n.setElemento(element);
		return aux;
	}
	
	public String toString() {
		Iterator<E> it = iterator(); // Le pido el iterador a la lista this.
		String s = "[";
		while( it.hasNext() ) {
			s += it.next(); // Hay un cast implícito de E a String, equivale a: s+=it.next().toString();
			if( it.hasNext() ) // Hago append de una coma si quedan elementos
				s += ", ";
		}
		s += "]";
		return s;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>( this );
	}

	@Override
	public Iterable<Position<E>> positions() {
		ListaDobleSinCentinelas<Position<E>> lista= new ListaDobleSinCentinelas<Position<E>>();
		if(tamaño!=0) {
		try {
			Position<E> aux=this.first() ;
			Position<E> ultimo=this.last() ;
		
		while(aux!=ultimo) {
			lista.addLast(aux);
			aux=next(aux);
		}
		lista.addLast(ultimo);
		}	
		 catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		}
		return lista;
	}
				
}
