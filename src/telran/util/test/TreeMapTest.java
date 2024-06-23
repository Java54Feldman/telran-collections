package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.TreeMap;

class TreeMapTest extends AbstractMapTest {
	TreeMap<Integer, Integer> treeMap;
	@Override
	@BeforeEach
	void setUp() {
		map = new TreeMap<>();
		super.setUp();
		treeMap = (TreeMap<Integer, Integer>) map;
	}
	@Override
	protected <T> void sort(T[] expected, T[] actual) {
		// iterating in ascending order
		//only expected must be sorted
		Arrays.sort(expected);
		
	}

	@Test
	void firstKeyTest() {
		assertEquals(-20, treeMap.firstKey());
	}
	@Test
	void lastKeyTest() {
		assertEquals(20, treeMap.lastKey());
	}
	//{-1, 4, 20, 3, -20, 10}
	@Test
	void ceilingKeyTest() {
		assertEquals(20, treeMap.ceilingKey(20));
		assertNull(treeMap.ceilingKey(25));
		assertEquals(-1, treeMap.ceilingKey(-10));
		
	}
	@Test
	void floorKeyTest() {
		assertEquals(20, treeMap.floorKey(20));
		assertNull(treeMap.floorKey(-21));
		assertEquals(-1, treeMap.floorKey(1));
		
	}

}