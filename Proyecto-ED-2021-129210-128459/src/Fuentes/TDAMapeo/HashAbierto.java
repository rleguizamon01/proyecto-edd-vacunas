package TDAMapeo;

import TDALista.*;

import TDALista.InvalidPositionException;
import TDALista.Position;

/**
 * 
 * Estructura de datos que utiliza una función hash para identificar datos
 * mediante una clave
 * @param <K> Clave de las entradas de la tabla hash
 * @param <V> Valor de las entradas de la tabla hash
 */
public class HashAbierto<K,V> implements Map<K,V> {
	
	protected PositionList<Entrada<K,V>>[] buckets;
	protected int n; // cantidad de entradas
	protected int N; // tamaño del arreglo
	protected static final float factor = 0.5F;
	
	/**
	 * Inicializa una instancia de la clase HashAbierto
	 */
	public HashAbierto() {
		n = 0;
		N = 13;
		buckets = (PositionList<Entrada<K,V>>[]) new ListaDobleSinCentinelas[N];
		for(int i = 0; i < N; i++)
			buckets[i] = new ListaDobleSinCentinelas<Entrada<K,V>>();
	}
	
	/**
	 * Dada una clave devuelve un entero positivo
	 * @param clave a mapear
	 * @return entero positivo mapeado por la función 
	 * @throws InvalidKeyException si la clave pasa por parámetro es inválida
	 */
	private int hashCode(K clave) throws InvalidKeyException{
		checkKey(clave);
		return Math.abs(clave.hashCode() % N);
	}
	
	/**
	 * Chequea que la clave pasada por parámetro no sea nula
	 * @param clave a chequear
	 * @throws InvalidKeyException si la clave es nula
	 */
	private void checkKey(K clave) throws InvalidKeyException{
		if(clave == null)
			throw new InvalidKeyException("Clave nula");
	}
	
	/**
	 * Amplia el tamaño de la tabla Hash al primo más cercano luego de duplicarlo
	 */
	private void reHash() {
		Iterable<Entry<K,V>> entradas = entries();
		
		N = proxPrimo(N*2);

		n = 0;
		buckets = (PositionList<Entrada<K,V>>[]) new ListaDobleSinCentinelas[N];
		
		for(int i = 0; i < N; i++) 
			buckets[i] = new ListaDobleSinCentinelas<Entrada<K,V>>();
		
		for(Entry<K,V> e : entradas) {
			try {
				this.put(e.getKey(), e.getValue());
			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			}
		}
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public V get(K key) throws InvalidKeyException {
		checkKey(key);
		PositionList<Entrada<K,V>> lista = buckets[hashCode(key)];

		for(Position<Entrada<K, V>> p: lista.positions()) // c1 + n + n(c2)
			if(p.element().getKey().equals(key))
				return p.element().getValue();
		
		return null;
	}

	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);

		PositionList<Entrada<K,V>> bucket = buckets[hashCode(key)];
		
		for(Position<Entrada<K,V>> p: bucket.positions()) { // c1 + n + n(c2)
			if(p.element().getKey().equals(key)) {
				V aux = p.element().getValue();
				p.element().setValue(value);
				return aux;
			}
		}
		
		bucket.addLast(new Entrada<K,V>(key, value)); // O(1)
		n++;

		if((float)n/N >= factor)
			reHash();
			
		return null;
	}
	
	/**
	 * Devuelve el primo más cercano a partir del entero ingresado
	 * @param n entero 
	 * @return número primo más cercano
	 */
	private int proxPrimo(int n) {
		boolean aux = false;
		int numPrimo = 0;
		
		for(int i = n+1; !aux; i++) {
			aux = true;
			for(int j = 2; j <= i/2 && aux; j++)
				if(i % j == 0)
					aux = false;
			numPrimo = i;
		}
		
		return numPrimo;
	}

	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		
		PositionList<Entrada<K,V>> bucket = buckets[hashCode(key)];
		
		for(Position<Entrada<K, V>> p : bucket.positions())
			if(p.element().getKey().equals(key)) {
				try {
					V value = p.element().getValue();
					bucket.remove(p);
					n--;
					return value;
				} catch (InvalidPositionException e) {
					e.printStackTrace();
				}
				
			}
		
		return null;
	}

	public Iterable<K> keys() {
		PositionList<K> l = new ListaDobleSinCentinelas<K>();
		
		for(int i = 0; i < N; i++)
			if(!buckets[i].isEmpty())
				for(Entrada<K,V> entrada : buckets[i])
					l.addLast(entrada.getKey());
		return l;
	}

	public Iterable<V> values() {
		PositionList<V> l = new ListaDobleSinCentinelas<V>();
		
		for(int i = 0; i < N; i++)
			if(!buckets[i].isEmpty())
				for(Entrada<K,V> entrada : buckets[i])
					l.addLast(entrada.getValue());
		return l;
	}

	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> l = new ListaDobleSinCentinelas<Entry<K,V>>();
		
		for(int i = 0; i < N; i++)
			if(!buckets[i].isEmpty())
				for(Entry<K,V> e : buckets[i])
					l.addLast(e);
		return l;
	}
	
	/**
	 * Dupla de datos que relaciona clave y valor
	 *
	 * @param <K> clave de la entrada
	 * @param <V> valor de la entrada
	 */
	private class Entrada<K,V> implements Entry<K,V> {
		private K clave;
		private V valor;
		
		public Entrada(K clave, V valor) {
			this.clave = clave;
			this.valor = valor;
		}
		
		public K getKey() {
			return clave;
		}
		
		public V getValue() {
			return valor;
		}
		
		/**
		 * Establece una clave para una entrada
		 * @param clave a establecer
		 */
		public void setKey(K clave) {
			this.clave = clave;
		}
		
		/**
		 * Establece un valor para una entrada
		 * @param valor a establecer
		 */
		public void setValue(V valor) {
			this.valor = valor;
		}
		
		public String toString() {
			return "(" + getKey() + "," + getValue() + ")";
		}
	}
	

}