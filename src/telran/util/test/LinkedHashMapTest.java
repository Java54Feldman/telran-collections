package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.LinkedHashMap;

class LinkedHashMapTest extends AbstractMapTest {
	@Override
	@BeforeEach
	void setUp() {
		map = new LinkedHashMap<>();
		super.setUp();
	}
	@Override
	protected <T> void sort(T[] expected, T[] actual) {
		// keeps order neither expected nor actual should be sorted
		
	}

	

}