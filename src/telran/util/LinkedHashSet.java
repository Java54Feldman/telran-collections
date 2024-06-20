package telran.util;

import java.util.Iterator;

import telran.util.LinkedList.Node;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {
	HashMap<T, Node<T>> map = new HashMap<>(); //Node<T> нужна для удаления! 
	LinkedList<T> list = new LinkedList<>(); //нужен для порядка итерирования
	
	@Override
	public boolean add(T obj) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(T pattern) {
		Node<T> node = map.get(pattern);
		
		return node == null ? null : node.data;
	}

}
