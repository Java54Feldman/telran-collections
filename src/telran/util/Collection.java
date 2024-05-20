package telran.util;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Collection<T> extends Iterable<T>{
	default Stream <T> stream() {
		return StreamSupport.stream(spliterator(), false);
	}
	default Stream <T> parralelStream() {
		return StreamSupport.stream(spliterator(), true);
	}
	boolean add(T obj);
	boolean remove(T pattern);
	boolean contains(T pattern);
	int size();
	
}
