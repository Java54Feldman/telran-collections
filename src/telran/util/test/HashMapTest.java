package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.HashMap;

class HashMapTest extends AbstractMapTest {
	@BeforeEach
	@Override
	void setUp() {
		map = new HashMap<Integer, Integer>();
		super.setUp();
	}
}
