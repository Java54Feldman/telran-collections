package telran.util.test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import telran.util.Collection;
import telran.util.List;

public abstract class CollectionTest {
	protected static final int N_ELEMENTS = 1_048_575;
	private static final int N_RUNS = 10000000;
	protected Collection<Integer> collection;
	Integer[] numbers = { -20, 10, 1, 100, -5 };
	int newNumber = 1000000;

	@BeforeEach
	void setUp() {
		for (Integer num : numbers) {
			collection.add(num);

		}
	}

	@Test
	void iteratorTest() {
		
		runTest(numbers);
		Iterator<Integer> it = collection.iterator();
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		Integer num = null;
		while(it.hasNext()) {
			num = it.next();
		}
		assertThrowsExactly(NoSuchElementException.class, () -> it.next());
		it.remove();
		assertFalse(collection.contains(num));
		assertEquals(numbers.length - 1, collection.size());
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		
	}

	@Test
	void addEqualedTest() {
		Integer[] expected = { -20, 10, 1, 100, -5, numbers[0] };
		assertTrue(collection.add(numbers[0]));
		runTest(expected);
	}

	@Test
	void addUniqueTest() {

		Integer[] expected = { -20, 10, 1, 100, -5, newNumber };
		assertTrue(collection.add(newNumber));
		runTest(expected);
	}

	protected void runTest(Integer[] expected) {
		Integer[] actual = collection.stream().toArray(Integer[]::new);
		assertArrayEquals(expected, actual);
	}

	// tests of all methods for interface Collection
	// (see the interface Collection)
	@Test
	void addTest() {
		assertTrue(collection.add(999));
		runTest(new Integer[] { -20, 10, 1, 100, -5, 999 });
	}

	@Test
	void removeTest() {
		assertTrue(collection.remove(10));
		runTest(new Integer[] { -20, 1, 100, -5 });
		assertTrue(collection.remove(-20));
		runTest(new Integer[] { 1, 100, -5 });
		assertTrue(collection.remove(100));
		runTest(new Integer[] { 1, -5 });
		assertTrue(collection.remove(1));
		runTest(new Integer[] { -5 });
	}

	@Test
	void containsTest() {
		assertTrue(collection.contains(10));
		assertFalse(collection.contains(999));
	}

	@Test
	void sizeTest() {
		assertEquals(5, collection.size());
		collection.add(999);
		assertEquals(6, collection.size());
		collection.remove(999);
		assertEquals(5, collection.size());
	}

	@Test
	void performanceAddContainsIteratorTest() {
		Random random = new Random();
		createBigRandomCollection(random);
		
		assertEquals(N_ELEMENTS, collection.size());
		Integer [] actual = new Integer[N_ELEMENTS];
		int index = 0;
		for(Integer num: collection) {
			actual[index++] = num;
		}
		assertEquals(N_ELEMENTS, index);
		if (collection instanceof List) {
			System.out.println("Performance test of method \"contains\" for all List objects takes huge time");
		} else {
			for(int i = 0; i < N_RUNS; i++) {
				collection.contains(random.nextInt());
			}
			
		}
		
	}

	protected void createBigRandomCollection(Random random) {
		int[] randomNumbers = random.ints().distinct().limit(N_ELEMENTS).toArray();
		for(Integer num: numbers) {
			collection.remove(num);
		}
		for(int i = 0; i < N_ELEMENTS; i++) {
			collection.add(randomNumbers[i]);
		}
	}
	@Test
	void removeIfTest() {
		assertTrue(collection.removeIf(n -> n % 2 == 0));
		assertTrue(collection.stream().allMatch(n -> n % 2 != 0 ));
		assertFalse(collection.removeIf(n -> n % 2 == 0));
		assertEquals(2, collection.size());
	}
	@Test
	void clearTest() {
		collection.clear();
		assertEquals(0, collection.size());
	}
	@Test
	@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
	void performanceClearTest() {
		createBigRandomCollection(new Random());
		collection.clear();
	}

}
