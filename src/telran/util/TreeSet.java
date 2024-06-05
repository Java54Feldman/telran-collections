package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> implements SortedSet<T> {
	private static class Node<T> {
		T data;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T data) {
			this.data = data;
		}
	}

	Node<T> root;
	int size;
	private Comparator<T> comp;

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}

	private class TreeSetIterator implements Iterator<T> {
		Node<T> current = getLeastFrom(root);

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.data;
			current = getCurrent(current);
			return res;
		}
	}

	@Override
	public T get(T pattern) {
		Node<T> node = getNode(pattern);
		return node == null ? null : node.data;
	}

	private Node<T> getNode(T pattern) {
		Node<T> res = null;
		Node<T> node = getParentOrNode(pattern);
		if (node != null && comp.compare(node.data, pattern) == 0) {
			res = node;
		}
		return res;
	}

	private Node<T> getParentOrNode(T pattern) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes = 0;
		while (current != null && (compRes = comp.compare(pattern, current.data)) != 0) {
			parent = current;
			current = compRes > 0 ? current.right : current.left;
		}
		return current == null ? parent : current;
	}

	public Node<T> getCurrent(Node<T> current) {
		return current.right != null ? getLeastFrom(current.right) : getFirstGreaterParent(current);
	}

	private Node<T> getFirstGreaterParent(Node<T> current) {
		Node<T> res = null;
		if (current != root) {
			res = current.parent;
			while (res != null && comp.compare(res.data, current.data) < 0) {
				res = res.parent;
			}
		} 
		return res;
	}

	private Node<T> getLeastFrom(Node<T> node) {
		if (node != null) {
			while (node.left != null) {
				node = node.left;
			}
		}
		return node;
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if (obj == null) {
			throw new NullPointerException();
		}
		if (!contains(obj)) {
			res = true;
			Node<T> node = new Node<>(obj);
			if (root == null) {
				root = node;
			} else {
				addWithParent(node);
			}
			size++;
		}
		return res;
	}

	private void addWithParent(Node<T> node) {
		Node<T> parent = getParentOrNode(node.data);
		if (comp.compare(node.data, parent.data) > 0) {
			parent.right = node;
		} else {
			parent.left = node;
		}
		node.parent = parent;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> node = getNode(pattern);
		if (node != null) {
			removeNode(node);
			res = true;
		}
		return res;
	}

	private void removeNode(Node<T> node) {
		if (node.left == null || node.right == null) {
			if (node == root) {
				removeRoot();
				node.data = null;
			} else if (node.left == null && node.right == null) {
				removeEnd(node);
			} else if (node.left == null) {
				remomeRight(node);
			} else {
				removeLeft(node);
			}
			if (node != root) {
				setNulls(node);
			}
		} else {
			Node<T> substitute = getGreatestFrom(node.left);
			node.data = substitute.data;
			if (substitute.left == null) {
				removeEnd(substitute);
			} else {
				removeLeft(substitute);
			}
			setNulls(substitute);
		}
		size--;
	}

	private void removeEnd(Node<T> node) {
		if (node == node.parent.left) {
			node.parent.left = null;
		} else {
			node.parent.right = null;
		}
	}

	private void removeRoot() {
		if (size > 1) {
			if (root.left == null) {
				root.right.parent = null;
				root = root.right;
			} else {
				root.left.parent = null;
				root = root.left;
			}
		} else {
			root = null;
		}
	}

	private void removeLeft(Node<T> node) {
		node.left.parent = node.parent;
		node.parent.left = node.left;
	}

	private void remomeRight(Node<T> node) {
		node.right.parent = node.parent;
		node.parent.right = node.right;
	}

	private void setNulls(Node<T> node) {
		node.data = null;
		node.parent = null;
		node.left = null;
		node.right = null;
	}

	@Override
	public boolean contains(T pattern) {
		return getNode(pattern) != null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeSetIterator();
	}

	@Override
	public T first() {
		return root == null ? null : getLeastFrom(root).data;
	}

	@Override
	public T last() {
		return root == null ? null : getGreatestFrom(root).data;
	}

	private Node<T> getGreatestFrom(Node<T> node) {
		if (node != null) {
			while (node.right != null) {
				node = node.right;
			}
		}
		return node;
	}

}
