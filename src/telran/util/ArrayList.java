package telran.util;

import java.nio.channels.IllegalSelectorException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> extends AbstractCollection<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator<T> {
		int index = 0;
		boolean flNext = false;

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			flNext = true;
			return array[index++];
		}
		@Override
		public void remove() {
			if(!flNext) {
				throw new IllegalStateException();
			}
			ArrayList.this.remove(--index);
			flNext = false;
		}
	}

	@Override
	/**
	 * adds object at end of array, that is, at index == size
	 */
	public boolean add(T obj) {
		if (size == array.length) {
			allocate();
		}
		array[size++] = obj;
		return true;
	}

	private void allocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public void add(int index, T obj) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (size == array.length) {
			allocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;

	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		T removedObj = array[index];
		size--;
		System.arraycopy(array, index + 1, array, index, size - index);
		return removedObj;
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		while (index < size && !equals(array[index], pattern)) {
			index++;
		}
		return index == size ? -1 : index;
	}

	private boolean equals(T elem1, T elem2) {
		return elem1 == null ? elem1 == elem2 : elem1.equals(elem2);
	}

	@Override
	public int lastIndexOf(T pattern) {
		int index = size - 1;
		while (index >= 0 && !equals(array[index], pattern)) {
			index--;
		}
		return index;
	}
	@Override
	public boolean removeIf(Predicate<T> predicate) {
		//Two indexes on one array
		//no allocation for new array
		//O[N]
		int indexDest = 0;
		Predicate<T> negatePred = predicate.negate();
		int oldSize = size;
		for(int i = 0; i < size; i++) {
			//moving not removed elements
			if(negatePred.test(array[i])) {
				array[indexDest++] = array[i];
			}
		}
		for(int i = indexDest; i < size; i++) {
			//putting null's to removed elements
			array[i] = null;
		}
		size = indexDest;
		return size < oldSize;
	}

}
