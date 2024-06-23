package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.Map;
import telran.util.Map.Entry;
import telran.util.Set;

abstract class AbstractMapTest {
 protected Map<Integer, Integer> map;
 protected Integer[] keys = {-1, 4, 20, 3, -20, 10};
 void setUp() {
	 //Map designated y = x ^ 2
	 for(Integer key: keys) {
		 map.put(key, key * key);
	 }
 }
 @Test
 void getTest() {
	 assertEquals(1, map.get(-1));
	 assertNull(map.get(1));
 }
 @Test
 void getOrDefaultTest() {
	 assertEquals(1, map.getOrDefault(-1, 0));
	 assertEquals(0, map.getOrDefault(1, 0));
 }
 @Test
 void putTest() {
	 int newValue = 100000;
	 assertNull(map.put(1, 1));
	 assertEquals(1, map.get(1));
	 assertEquals(1,map.put(1, newValue));
	 assertEquals(newValue, map.get(1));
 }
 @Test
 void putIfAbsentTest() {
	 int newValue = 100000;
	 assertNull(map.putIfAbsent(1, 1));
	 assertEquals(1, map.get(1));
	 assertEquals(1,map.putIfAbsent(1, newValue));
	 assertEquals(1, map.get(1));
 }
 @Test
 void removeTest() {
	 assertNull(map.remove(1));
	 assertEquals(1, map.remove(-1));
	 assertNull(map.get(-1));
 }
 @Test
 void entrySetTest() {
	 Set<Entry<Integer, Integer>> entrySet = map.entrySet();
	 // {-1, 4, 20, 3, -20, 10};
	 Entry [] expected = {
			 new Entry<Integer, Integer>(-1, 1),
			 new Entry<Integer, Integer>(4, 16),
			 new Entry<Integer, Integer>(20, 400),
			 new Entry<Integer, Integer>(3, 9),
			 new Entry<Integer, Integer>(-20, 400),
			 new Entry<Integer, Integer>(10, 100)
	 };
	 runIterableTest(expected, entrySet);
 }
 @Test
 void keySetTest() {
	// 
	 Integer [] expected = {-1, 4, 20, 3, -20, 10};
	 runIterableTest(expected, map.keySet());
 }
 @Test
 void valuesTest() {
	// 
	 Integer [] expected = {1, 16, 400, 9, 400, 100};
	 runIterableTest(expected, map.values());
 }
 protected <T> void runIterableTest(T[] expected, Iterable<T> iterable) {
		T[] actual = Arrays.copyOf(expected, expected.length);
		int index = 0;
		for(T obj: iterable) {
			actual[index++] = obj;
		}
		sort(expected, actual);
		assertArrayEquals(expected, actual);
		
	}
protected abstract <T>void sort(T[] expected, T[] actual);
}