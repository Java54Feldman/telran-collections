package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.TreeSet;

class TreeSetTest extends SortedSetTest {
	TreeSet<Integer> treeSet;
	@Override
	@BeforeEach
	void setUp() {
		collection = new TreeSet<>();
		super.setUp();
		treeSet = (TreeSet<Integer>) collection;
	}
	@Test
	void displayRootChildrenTest() {
		treeSet.displayRootChildren();
	}
	@Test
	void treeInversionTest() {
		treeSet.treeInversion();
		Integer[] expected = { 100, 10, 1, -5, -20 };
		Integer[] actual = new Integer[treeSet.size()];
		int index = 0;
		for (Integer num : treeSet) {
			actual[index++] = num;
		}
		assertArrayEquals(expected, actual);
		assertTrue(treeSet.contains(100));
	}
	@Test
	void displayTreeRotatedTest() {
		treeSet.setSpacesPerLevel(4);
		treeSet.displayTreeRotated();
	}
	@Test
	void widthTest() {
		assertEquals(2, treeSet.width());
	}
	@Test
	void heightTest() {
		assertEquals(4, treeSet.height());
	}
	@Test
	void sortedSequenceTreeTest() {
		TreeSet<Integer> treeSet = new TreeSet<>();
		int[] sortedArray = new Random().ints().distinct()
				.limit(N_ELEMENTS).sorted().toArray();
		transformArray(sortedArray);
		for (int num : sortedArray) {
			treeSet.add(num);
		}
		balancedTreeTest(treeSet);
	}
	private void balancedTreeTest(TreeSet<Integer> treeSet) {
		assertEquals(20, treeSet.height());
		assertEquals((N_ELEMENTS + 1) / 2, treeSet.width());
	}
	private void transformArray(int[] sortedArray) {
	int [] balanceOrderedArray = new int[sortedArray.length];
	int [] indexRef = {0};
	transformArray(balanceOrderedArray, sortedArray,
			indexRef, 0, sortedArray.length - 1);
	System.arraycopy(balanceOrderedArray, 0,
			sortedArray, 0, N_ELEMENTS);

}
private void transformArray(int[] balanceOrderedArray, int[] sortedArray,
		int[] indexRef, int left, int right) {
	if (left <= right) {
		int indexRoot = (left + right) / 2;
		int index = indexRef[0];
		balanceOrderedArray[index] = sortedArray[indexRoot];
		indexRef[0]++;
		transformArray(balanceOrderedArray, sortedArray, indexRef, left, indexRoot - 1);
		transformArray(balanceOrderedArray, sortedArray, indexRef, indexRoot + 1,
				right);
	}
}
	@Test
	void balanceTreeTest() {
		createBigRandomCollection(new Random());
		treeSet.balance();
		balancedTreeTest(treeSet);
		int index = 0;
		for (Integer num : treeSet) {
			index++;
		}
		assertEquals(treeSet.size(), index);
	}
}
