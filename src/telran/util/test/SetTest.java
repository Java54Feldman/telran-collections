package telran.util.test;



import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import telran.util.Set;

public abstract class SetTest extends CollectionTest {
	Set<Integer> set;

	@Override
	void setUp() {
		super.setUp(); //заполнение колеекции
		set = (Set<Integer>)collection;
	}
	@Test
	void getTest() {
		assertEquals(1, (int)set.get(1));
		assertNull(set.get(1000));
	}
	@Override
	@Test
	void addEqualedTest() {
		assertFalse(set.add(numbers[0]));
	}
}
