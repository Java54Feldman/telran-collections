package telran.util;

import java.util.Comparator;

public class TreeMap<K, V> extends AbstractMap<K, V> implements SortedMap<K, V> {

	TreeSet<Entry<K, V>> treeSet = ((TreeSet<Entry<K, V>>)set);
	@Override
	public K firstKey() {
		Entry<K, V> entry = treeSet.first();
		return entry.getKey();
	}

	@Override
	public K lastKey() {
		Entry<K, V> entry = treeSet.last();
		return entry.getKey();
	}

	@Override
	public K floorKey(K key) {
		Entry<K, V> entry = treeSet.floor(new Entry<>(key, null));
		return entry == null ? null : entry.getKey();
	}

	@Override
	public K ceilingKey(K key) {
		Entry<K, V> entry = treeSet.ceiling(new Entry<>(key, null));
		return entry == null ? null : entry.getKey();
	}

	@Override
	protected Set<K> getEmptyKeySet() {
		
		return new TreeSet<K>();
	}
	public TreeMap() {
		this(Comparator.naturalOrder());
		
	}
	public TreeMap(Comparator<Entry<K, V>> comp) {
		set = new TreeSet<>(comp);
		treeSet = ((TreeSet<Entry<K, V>>)set);
	}

}
