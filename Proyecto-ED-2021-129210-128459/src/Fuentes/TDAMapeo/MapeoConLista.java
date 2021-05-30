package TDAMapeo;

import java.util.Iterator;
import TDALista.*;

public class MapeoConLista<K,V> implements Map<K,V>{
	protected ListaDobleSinCentinelas<Entrada<K,V>> s;
	
	public MapeoConLista() {
		s = new ListaDobleSinCentinelas<Entrada<K,V>>();
	}

	public int size() {
		return s.size();
	} // O(1)

	public boolean isEmpty() {
		return s.isEmpty();
	} // O(1)

	public V get(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("get(k):: k nula");
		
		for(Position<Entrada<K, V>> p: s.positions()) // c1 + n + n(c2)
			if(p.element().getKey().equals(key))
				return p.element().getValue();
		
		return null;
	} // O(n)

	public V put(K key, V value) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("get(k):: k nula");
		
		for(Position<Entrada<K,V>> p: s.positions()) { // c1 + n + n(c2)
			if(p.element().getKey().equals(key)) {
				V aux = p.element().getValue();
				p.element().setValue(value);
				return aux;
			}
		}
		
		s.addLast(new Entrada<K,V>(key, value)); // O(1)
		return null;
	} // O(n)

	public V remove(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("get(k):: k nula");
		
		for(Position<Entrada<K, V>> p : s.positions())
			if(p.element().getKey().equals(key)) {
				try {
					V value = p.element().getValue();
					s.remove(p);
					return value;
				} catch (InvalidPositionException e) {
					e.printStackTrace();
				}
				
			}
		return null;
	} // O(n)

	public Iterable<K> keys() {
		PositionList<K> l = new ListaDobleSinCentinelas<K>();
		for(Entrada<K,V> e: s) {
			l.addLast(e.getKey());
		}
		return l;
	} // O(n)

	public Iterable<V> values() {
		PositionList<V> l = new ListaDobleSinCentinelas<V>();
		for(Entrada<K,V> e: s) {
			l.addLast(e.getValue());
		}
		return l;
	} // O(n)

	public Iterable<Entry<K,V>> entries() {
		PositionList<Entry<K,V>> aux = new ListaDobleSinCentinelas<Entry<K,V>>();
		
		if(!s.isEmpty()) {
			try {
				Position<Entrada<K,V>> posFinal = s.last();
				Position<Entrada<K,V>> posIni = s.first();
				
				while(posIni != null) {
					aux.addLast(posIni.element());
					posIni = (posIni == posFinal) ? null : s.next(posIni);
				}
			} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		return aux;
	} // O(n)
}
