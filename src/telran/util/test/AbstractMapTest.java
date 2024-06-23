package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Map;
import telran.util.Set;

class AbstractMapTest {
	protected Map<Integer, Integer> map;
	Integer[] keys = { 0, 2, 4, 6, 7 };
	Integer[] values = { -20, 10, 1, 100, -5 };
	int newKey = 50;
	int newValue = 500;

	@BeforeEach
	void setUp() {
		for (int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
	}
	@Test
	void getTest() {
		int actualFirst = values[0];
		int actualLast = values[values.length - 1];
		assertEquals(actualFirst, map.get(keys[0]));
		assertEquals(actualLast, map.get(keys[keys.length - 1]));		
		assertNull(map.get(newKey));
	}
	@Test
	void getOrDefaultTest() {
		int actual = values[1];
		assertEquals(actual, map.getOrDefault(keys[1], newValue));
		assertEquals(newValue, map.getOrDefault(newKey, newValue));
	}
	@Test
	void putTest() {
		assertNull(map.put(newKey, newValue));
		assertEquals(newValue, map.get(newKey));
		assertEquals(values[0], map.put(keys[0], newValue));
		assertEquals(newValue, map.get(keys[0]));
	}
	@Test
	void putIfAbsentTest() {
	    assertNull(map.putIfAbsent(newKey, newValue));
	    assertEquals(newValue, map.get(newKey));

	    int existingValue = map.get(keys[0]);
	    assertEquals(existingValue, map.putIfAbsent(keys[0], newValue));
	    assertEquals(existingValue, map.get(keys[0]));
	}
	@Test
	void removeTest() {
		assertNull(map.remove(newKey));
		assertEquals(values[0], map.remove(keys[0]));
		assertNull(map.remove(keys[0]));
	}
	@Test
	void keySetTest() {
		Integer[] actual = map.keySet().stream().toArray(Integer[]::new);
		assertArrayEquals(keys, actual);
	}
	@Test
	void entrySetTest() {
	    Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
	    assertEquals(keys.length, entrySet.size());
	    for (int i = 0; i < keys.length; i++) {
	        Map.Entry<Integer, Integer> expectedEntry = new Map.Entry<>(keys[i], values[i]);
	        assertTrue(entrySet.contains(expectedEntry));
	    }
	}
	@Test
	void valuesTest() {
		Integer[] actual = map.values().stream().sorted().toArray(Integer[]::new);
		values = Arrays.stream(values).sorted().toArray(Integer[]::new);
		assertArrayEquals(values, actual);
	}
	
}
