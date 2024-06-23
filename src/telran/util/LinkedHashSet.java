package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.LinkedList.Node;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {
	HashMap<T, Node<T>> map = new HashMap<>(); //Node<T> нужна для удаления! 
	LinkedList<T> list = new LinkedList<>(); //нужен для порядка итерирования
	
	@Override
	public boolean add(T obj) {
		// поддерживать одновременно 2 структуры
		// у обоих О[1]
		boolean res = false;
		if (!contains(obj)) {
			Node<T> node = new Node<>(obj);
			map.put(obj, node);
			list.addNode(size++, node);
			res = true;
		}
		return res;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> node = map.get(pattern);
		boolean res = false;
		if (node != null) {
			res = true;
			list.removeNode(node);
			map.remove(pattern);
			size--;
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		return map.get(pattern) != null;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedHashSetIterator();
	}
	private class LinkedHashSetIterator implements Iterator<T> {
	    Node<T> current = list.head;
	    Node<T> prev = null;

	    @Override
	    public boolean hasNext() {
	        return current != null;
	    }

	    @Override
	    public T next() {
	        if (!hasNext()) {
	            throw new NoSuchElementException();
	        }
	        prev = current;
	        current = current.next;
	        return prev.data;
	    }

	    @Override
	    public void remove() {
	    	if(prev == null) {
				throw new IllegalStateException();
			}
	    	LinkedHashSet.this.remove(prev.data);
	        prev = null;
	    }
	}

	@Override
	public T get(T pattern) {
		Node<T> node = map.get(pattern);
		return node == null ? null : node.data;
	}

}
