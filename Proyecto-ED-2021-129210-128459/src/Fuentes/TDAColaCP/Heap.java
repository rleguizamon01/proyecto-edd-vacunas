package TDAColaCP;

import java.util.Comparator;


/**
 * Clase que implementa la interfaz PriorityQueue
 * @param <K> Tipo de dato de la clave, de los objetos que contiene la cola con prioridad
 * @param <V> Tipo de dato del valor, de los objetos que contiene la cola con prioridad
 */
public class Heap<K,V> implements PriorityQueue<K,V>{
	
	/**
	 * Clase auxiliar que implementa la interfaz Entry. 
	 * Utilizada para ingresar pares de datos en la clase Heap 
	 */
	
	private class Entrada<K,V> implements Entry<K,V>{
		private K clave; private V valor;
		
		/**
		 * Constructor de la clase entrada
		 * @param clave Clave de la entrada
		 * @param valor Valor de la entrada
		 */
		public Entrada(K clave, V valor) {
			this.clave = clave;
			this.valor = valor;
		}
		
		@Override
		public K getKey() { return clave; }

		@Override
		public V getValue() { return valor; }
		
		@Override
		public String toString() {
			return "(" + clave + ", " + valor + ")"; 
		}
	}
	
	protected Entrada<K,V> [] elems;
	protected Comparator<K> comp;
	protected int size;
	
	/**
	 * Constructor de la clase heap
	 * @param maxElems Cantidad inicial maxima de la cola
	 * @param comp Comparador que se usara para ordenar los elementos de la cola
	 */
	public Heap(int maxElems, Comparator<K> comp ) {
		
		elems = (Entrada<K,V> []) new Entrada[maxElems];
		this.comp = comp; 
		size = 0;
	}
	
	/**
	 * Constructor sobrecargado de la clase heap
	 * @param comp Comparador que se usara para ordenar los elementos de la cola
	 */
	public Heap( Comparator<K> comp ) {
		elems = (Entrada<K,V> []) new Entrada[8];
		this.comp = comp; 
		size = 0; 
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0; 
	}
	
	@Override
	public Entry<K,V> min() throws EmptyPriorityQueueException{
		if (isEmpty())
			throw new EmptyPriorityQueueException("Cola con prioridad vacia.");
		return elems[1];
	}
	
	@Override
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		
		if(key == null)
			throw new InvalidKeyException("Clave nula");
		
		if (size==elems.length-1) { 
			Entrada<K,V> [] elemsNuevo=(Entrada<K,V> []) new Entrada[elems.length*2]; 
			for (int j=0;j<=size;j++)
				elemsNuevo[j]=elems[j];
			elems=elemsNuevo;
		}
		
		Entrada<K,V> entrada = new Entrada<K,V>(key, value); 
		elems[++size] = entrada; 
		
		int i = size; 
		boolean seguir = true; 
		while ( i>1 && seguir ) {
			Entrada <K,V> elemActual = elems[i]; 
			Entrada <K,V> elemPadre = elems[i/2]; 
			if( comp.compare(elemActual.getKey(), elemPadre.getKey()) < 0) {
				Entrada<K,V> aux = elems[i]; 
				elems[i] = elems[i/2];
				elems[i/2] = aux;
				i /= 2;
			} else
				seguir = false; 
		}
		return entrada;
	}
	
	@Override
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
		Entry<K,V> entrada = min();
		if( size == 1 ) { 
			elems[1] = null; 
			size = 0; 
			return entrada; 
		}
		else {
			elems[1] = elems[size]; 
			elems[size] = null; 
			size--;
			int i = 1; 
			boolean seguir = true;
			while ( seguir ) {
				int hi = i*2; 
				int hd = i*2+1;
				boolean tieneHijoIzquierdo = hi <= size(); 
				boolean tieneHijoDerecho = hd <= size();
				if( !tieneHijoIzquierdo ) 
					seguir = false; 
				else {
					int m; 
					if( tieneHijoDerecho ) {
						
						if( comp.compare( elems[hi].getKey(), elems[hd].getKey()) < 0 ) 
							m = hi;
						else 
							m = hd;
					} else m = hi; 
				
				if( comp.compare(elems[i].getKey(), elems[m].getKey()) > 0 ) {
					Entrada<K,V> aux = elems[i]; 
					elems[i] = elems[m];
					elems[m] = aux;
					i = m; 
				
				} else seguir = false;
				}} 
				return entrada;
			}
		}
	}