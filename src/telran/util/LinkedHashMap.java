package telran.util;

import telran.util.LinkedList.Node;

public class LinkedHashMap<K, V> extends AbstractMap<K, V> {

	@Override
	protected Set<K> getEmptyKeySet() {
		
		return new LinkedHashSet<K>();
	}
	public LinkedHashMap() {
		set = new LinkedHashSet<Entry<K, V>> ();
	}

}
