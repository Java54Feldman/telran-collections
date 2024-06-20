package telran.util;

public abstract class AbstractMap<K, V> implements Map<K, V> {
	protected Set<Entry<K, V>> set;
	abstract protected Set<K> getEmptyKeySet();
	@Override
	public V get(K key) {
		Entry<K, V> pattern = new Entry<>(key, null);
		Entry<K, V> entry = set.get(pattern);
		V res = null;
		if (entry != null) {
			res = entry.getValue();
		}
		return res;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		//также как в гете.
		//если существует, то меняем только значение сеттером
		//нет - добавляем новую пару
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<K> keySet() {
		Set<K> setKey = getEmptyKeySet();
		set.forEach(e -> setKey.add(e.getKey()));
		return setKey;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return set;
	}

	@Override
	public Collection<V> values() {
		ArrayList<V> res = new ArrayList<>();
		set.forEach(e -> res.add(e.getValue()));
		return res;
	}

}
