package telran.util.test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	void removeIndexTest() {
	    assertEquals((Integer) 1, list.remove(2));
	    runTest(new Integer[] {-20, 10, 100, -5});
	    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
	    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(4));
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
