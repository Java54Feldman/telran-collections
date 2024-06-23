package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.LinkedHashMap;

class LinkedHashMapTest extends AbstractMapTest {
	@BeforeEach
	@Override
	void setUp() {
		map = new LinkedHashMap<Integer, Integer>();
		super.setUp();
	}
}
