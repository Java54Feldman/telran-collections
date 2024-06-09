package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class HashSet<T> extends AbstractCollection<T> implements Set<T> {
	private static final int DEFAULT_HASH_TABLE_LENGTH = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	List<T>[] hashTable;
	float factor;

	public HashSet(int hashTableLength, float factor) {
		hashTable = new List[hashTableLength];
		this.factor = factor;
	}

	public HashSet() {
		this(DEFAULT_HASH_TABLE_LENGTH, DEFAULT_FACTOR);
	}

	private class HashSetIterator implements Iterator<T> {
		Iterator<T> iterator;
		int iteratorIndex;
		int prevIteratorIndex;
		Iterator<T> prevIterator;
		boolean flNext = false;

		HashSetIterator() {
			iteratorIndex = 0;
			iterator = getIterator(0);
			setIteratorIndex();
		}

		private Iterator<T> getIterator(int index) {
			List<T> list = hashTable[index];
			return list == null ? null : list.iterator();
		}

		@Override
		public boolean hasNext() {
			return iteratorIndex < hashTable.length;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = iterator.next();
			prevIterator = iterator;
			prevIteratorIndex = iteratorIndex;
			setIteratorIndex();
			flNext = true;
			return res;
		}

		private void setIteratorIndex() {
			int limit = hashTable.length - 1; // for not doing checking index inside iteration
			while (iteratorIndex < limit && (iterator == null || !iterator.hasNext())) {
				iteratorIndex++;
				iterator = getIterator(iteratorIndex);
			}
			if (iteratorIndex == limit && (hashTable[iteratorIndex] == null || !iterator.hasNext())) {
				iteratorIndex++;
			}

		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			prevIterator.remove();
			size--;
			if (hashTable[prevIteratorIndex].size() == 0) {
				hashTable[prevIteratorIndex] = null;
			}
			flNext = false;
		}

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
