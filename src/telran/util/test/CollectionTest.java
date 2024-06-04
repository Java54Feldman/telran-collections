package telran.util.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

public abstract class CollectionTest {
	private static final int N_ELEMENTS = 1_048_575;
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
		assertTrue(collection.remove(100));
		runTest(new Integer[] { -20, 10, 1, -5 });
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
	@Disabled
	void performanceAddTest() {
		Random random = new Random();
		int[] randomNumbers = random.ints().distinct().limit(N_ELEMENTS).toArray();
		for (Integer num : numbers) {
			collection.remove(num);
		}
		for (int i = 0; i < N_ELEMENTS; i++) {
			collection.add(randomNumbers[i]);
		}
		assertEquals(N_ELEMENTS, collection.size());
		for (int i = 0; i < N_RUNS; i++) {
			collection.contains(random.nextInt());
		}

	}
}
