package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.TreeMap;

class TreeMapTest extends AbstractMapTest {
	@BeforeEach
	@Override
	void setUp() {
		map = new TreeMap<Integer, Integer>();
		super.setUp();
	}
	@Test
	void firstKeyTest() {
		assertEquals(keys[0], ((TreeMap<Integer, Integer>) map).firstKey());
	}
	@Test
	void lastKeyTest() {
		assertEquals(keys[keys.length - 1], ((TreeMap<Integer, Integer>) map).lastKey());
	}
	@Test
	void floorKeyTest() {
		assertEquals(keys[1], ((TreeMap<Integer, Integer>) map).floorKey(3));
		assertNull(((TreeMap<Integer, Integer>) map).floorKey(keys[0] - 1));
	}
	@Test
	void ceilingKeyTest() {
		assertEquals(keys[3], ((TreeMap<Integer, Integer>) map).ceilingKey(5));
		assertNull(((TreeMap<Integer, Integer>) map).ceilingKey(newKey));
	}
}
