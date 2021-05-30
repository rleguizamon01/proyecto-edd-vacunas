package TDAMapeo;

import TDALista.ListaDobleSinCentinelas;
import TDALista.PositionList;

public class HashAbierto<K,V> implements Map<K,V> {
	
	protected Map<K,V>[] buckets;
	protected int n;
	protected int N;
	protected static final float factor = 0.9F;
	
	public HashAbierto() {
		n = 0;
		N = 13;
		buckets = (Map<K,V>[]) new MapeoConLista[N];
		for(int i = 0; i < N; i++)
			buckets[i] = new MapeoConLista<K,V>();
	}
	
	
	protected int hashCode(K clave) throws InvalidKeyException{
		checkKey(clave);
		return Math.abs(clave.hashCode() % N);
	}
	
	private void checkKey(K clave) throws InvalidKeyException{
		if(clave == null)
			throw new InvalidKeyException("Clave nula");
	}
	
	private void reHash() {
		Iterable<Entry<K,V>> entradas = entries();
		
		N = proxPrimo(N*2);

		n = 0;
		buckets = (Map<K,V>[]) new MapeoConLista[N];
		
		for(int i = 0; i < N; i++) 
			buckets[i] = new MapeoConLista<K,V>();
		
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
		
		return buckets[hashCode(key)].get(key);
	}

	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);

		V t = buckets[hashCode(key)].put(key, value);
		
		if(t == null)
			n++;
		
		if((float)n/N >= factor)
			reHash();
			
		return t;
	}
	
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
		
		V t = buckets[hashCode(key)].remove(key);
		
		if(t != null)
			n--;
		
		return t;
	}

	public Iterable<K> keys() {
		PositionList<K> l = new ListaDobleSinCentinelas<K>();
		
		for(int i = 0; i < N; i++)
			if(!buckets[i].isEmpty())
				for(K key : buckets[i].keys())
					l.addLast(key);
		return l;
	}

	public Iterable<V> values() {
		PositionList<V> l = new ListaDobleSinCentinelas<V>();
		
		for(int i = 0; i < N; i++)
			if(!buckets[i].isEmpty())
				for(V val : buckets[i].values())
					l.addLast(val);
		return l;
	}

	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> l = new ListaDobleSinCentinelas<Entry<K,V>>();
		
		for(int i = 0; i < N; i++)
			if(!buckets[i].isEmpty())
				for(Entry<K,V> e : buckets[i].entries())
					l.addLast(e);
		return l;
	}
	
	

}
