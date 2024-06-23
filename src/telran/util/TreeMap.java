package telran.util;

import java.util.Comparator;

public class TreeMap<K, V> extends AbstractMap<K, V> implements SortedMap<K, V> {

	@Override
	public K firstKey() {
		return ((TreeSet<Entry<K, V>>) set).first().getKey();
	}

	@Override
	public K lastKey() {
		return ((TreeSet<Entry<K, V>>) set).last().getKey();
	}

	@Override
	public K floorKey(K key) {
		Entry<K, V> pattern = new Entry<>(key, null);
		Entry<K, V> entry = ((TreeSet<Entry<K, V>>) set).floor(pattern);
		K res = null;
		if (entry != null) {
			res = entry.getKey();
		}
		return res;
	}

	@Override
	public K ceilingKey(K key) {
		Entry<K, V> pattern = new Entry<>(key, null);
		Entry<K, V> entry = ((TreeSet<Entry<K, V>>) set).ceiling(pattern);
		K res = null;
		if (entry != null) {
			res = entry.getKey();
		}
		return res;
	}

	@Override
	protected Set<K> getEmptyKeySet() {
		return new TreeSet<K>();
	}
	public TreeMap() {
		set = new TreeSet<>();
	}
	public TreeMap(Comparator<Entry<K, V>> comp) {
		set = new TreeSet<>(comp);
	}

}
