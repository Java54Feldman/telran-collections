package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.TreeSet;

class TreeSetTest extends SortedSetTest {
	@Override
	@BeforeEach
	void setUp() {
		collection = new TreeSet<>();
		super.setUp();
	}

}
