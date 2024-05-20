package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.List;

public abstract class ListTest extends CollectionTest {
	List<Integer> list;
	
	@BeforeEach
	@Override
	void setUp() {
		super.setUp(); //заполнение коллекции
		list = (List<Integer>)collection;
	}
	//TODO
	//Write all methods form interface List
	//(see interface telran.util.List)
}
