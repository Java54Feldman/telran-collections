package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class HashSet<T> implements Set<T> {
	private static final int DEFAULT_HASH_TABLE_LENGTH = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	List<T>[] hashTable;
	int size;
	float factor;

	public class HashSetIterator implements Iterator<T> {
		int index = 0;
		Iterator<T> currentListIterator = null;

		public HashSetIterator() {
			advanceIndex();
		}

		public void advanceIndex() {
			while (index < hashTable.length && (hashTable[index] == null || hashTable[index].size() == 0)) {
				index++;
			}
			if (index < hashTable.length) {
				currentListIterator = hashTable[index].iterator();
			}
		}

		@Override
		public boolean hasNext() {
			boolean hasNext = true;
			if (currentListIterator == null || !currentListIterator.hasNext()) {
				index++;
				advanceIndex();
				hasNext = currentListIterator != null && currentListIterator.hasNext();
			}
			return hasNext;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T nextElement = currentListIterator.next();
			if(!currentListIterator.hasNext()) {
				index++;
				advanceIndex();
			}
			return nextElement;

		}

	}

	public HashSet(int hashTableLength, float factor) {
		hashTable = new List[hashTableLength];
		this.factor = factor;
	}

	public HashSet() {
		this(DEFAULT_HASH_TABLE_LENGTH, DEFAULT_FACTOR);
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if (!contains(obj)) {
			if ((float) size / hashTable.length >= factor) {
				hashTableReallocation();
			}
			addObjInHashTable(obj, hashTable);
			size++;
			res = true;
		}
		return res;
	}

	private void hashTableReallocation() {
		List<T>[] tmp = new List[hashTable.length * 2];
		for (List<T> list : hashTable) {
			if (list != null) {
				for (T obj : list) {
					addObjInHashTable(obj, tmp);
				}
			}
		}
		hashTable = tmp;

	}

	private void addObjInHashTable(T obj, List<T>[] table) {
		int index = getIndex(obj, table);
		List<T> list = table[index];
		if (list == null) {
			list = new LinkedList<>();
			table[index] = list;
		}
		list.add(obj);

	}

	private int getIndex(T obj, List<T>[] table) {
		int hashCode = obj.hashCode();
		int index = Math.abs(hashCode % table.length);
		return index;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = contains(pattern);
		if (res) {
			int index = getIndex(pattern, hashTable);
			hashTable[index].remove(pattern);
			size--;
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		// O[1] !!
		int index = getIndex(pattern, hashTable);
		List<T> list = hashTable[index];
		return list != null && list.contains(pattern);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator();
	}

	@Override
	public T get(T pattern) {
		T res = null;
		int index = getIndex(pattern, hashTable);
		List<T> list = hashTable[index];
		if (list != null) {
			for (T element : list) {
				if (element.equals(pattern)) {
					res = element;
				}
			}
		}
		return res;
	}

}
