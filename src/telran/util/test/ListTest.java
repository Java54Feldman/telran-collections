package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import telran.util.List;

public abstract class ListTest extends CollectionTest {
	List<Integer> list;
	
	@BeforeEach
	@Override
	void setUp() {
		super.setUp(); //заполнение коллекции
		list = (List<Integer>)collection;
	}
	//Write all methods form interface List
	//(see interface telran.util.List)
	@Test
	void getTest() {
	    assertEquals((Integer) 10, list.get(1));
	    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
	    assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
	}

	@Test
	void addIndexTest() {
	    list.add(2, 999);
	    runTest(new Integer[] {-20, 10, 999, 1, 100, -5});
	    assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 999));
	    assertThrows(IndexOutOfBoundsException.class, () -> list.add(7, 999));
	}

	@Test
	void removeIndexTestOld() {
	    assertEquals((Integer) 1, list.remove(2));
	    runTest(new Integer[] {-20, 10, 100, -5});
	    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
	    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(4));
	    
	}
	@Test
	void removeIndexTest() {
		Integer num0 = -20, num2 = 100, numLast = -5;

		Integer[] expected0 = { 10, 1, 100, -5 };
		Integer[] expected0_2 = { 10, 1, -5 };
		Integer[] expected0_2_last = { 10, 1 };
		assertEquals(num0, list.remove(0));
		runTest(expected0);
		assertEquals(num2, list.remove(2));
		runTest(expected0_2);
		assertEquals(numLast, list.remove(list.size() - 1));
		runTest(expected0_2_last);
		testIndexExceptions(() -> list.remove(numbers.length));
		testIndexExceptions(() -> list.remove(-1));
		list.remove(0);
		list.remove(0);
		runTest(new Integer[0]);
	}
	void testIndexExceptions(Executable executable) {
		assertThrowsExactly(IndexOutOfBoundsException.class, executable);
	}

	@Test
	void indexOfTest() {
	    assertEquals(1, list.indexOf(10));
	    assertEquals(-1, list.indexOf(999));
	}

	@Test
	void lastIndexOfTest() {
	    assertEquals(3, list.lastIndexOf(100));
	    assertEquals(-1, list.lastIndexOf(999));
	}
}
