package ule.edi.queuewithrep;


import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;

public abstract class AbstractQueueWithRefTests {

	protected abstract <T> QueueWithRep<T> createQueueWithRep();
	

	private QueueWithRep<String> S1;

	private QueueWithRep<String> S2;
	
	@Before
	public void setupQueueWithReps() {

		this.S1 = createQueueWithRep();
		
		this.S2 = createQueueWithRep();
		
		S2.add("ABC", 5);
		S2.add("123", 5);
		S2.add("XYZ", 10);
	}

	@Test
	public void testConstructionIsEmpty() {
		assertTrue(S1.isEmpty());
		assertFalse(S2.isEmpty());
	}
	
	@Test
	//Las nuevas instancias del TAD tienen tamaño cero: 
	public void testConstructionCardinality() {
		assertEquals(S1.size(), 0);
	}

	@Test
	public void testToStringInEmpty() {
		assertTrue(S1.isEmpty());
		assertEquals(S1.toString(), "()");
	}
	
	@Test
	public void testToString1elem() {
		assertTrue(S1.isEmpty());
		S1.add("A",3);
		assertEquals(S1.toString(), "(A A A )");
	}
	
	@Test
	//Añadir elementos con una multiplicidad incrementa su contador y el tamaño de la cola: ")
	public void testAddWithCount() {
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 5);
		assertEquals(S1.size(), 5);
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 10);
		S1.add("123", 5);		
		assertEquals(S1.count("123"), 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 15);
	}
	
	
	@Test
	//Se pueden eliminar cero instancias de un elemento con remove(x, 0): ")
	public void testRemoveZeroInstances() {
		S1.remove("ABC", 0);
	}
	
	// TODO AÑADIR MAS TESTS
	
	@Test(expected = NullPointerException.class)
	
	public void testAddNullOneTime() {
		S1.add(null);
	}
	
	@Test(expected = NullPointerException.class)
	
	public void testAddNullManyTimes() {
		S1.add(null,5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	
	public void testAddNullNegativeTimes() {
		S1.add("ABC",-5);
	}
	
	@Test
	
	public void testAddOneTimeAlreadyAdded() {
		S1.add("ABC");
		S1.add("ABC");
		Assert.assertEquals(S1.count("ABC"),2);
	}
	

	@Test
	
	public void testAddTwoEqualsAndOtherNot() {
		
		S1.add("DLr");
		S1.add("ABC");
		S1.add("ABC");
				
		Assert.assertEquals(S1.size(),3);
	}
	
	@Test
	
	public void testAddTwoEqualsAndOtherNotSeveralTimes() {
		
		S1.add("DLr",1);
		S1.add("ABC",1);
		S1.add("ABC",1);
				
		Assert.assertEquals(S1.size(),3);
	}
	
	@Test(expected = NullPointerException.class)
	public void testRemoveNull() {
		S1.remove(null, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveNegativeTimes() {
		S1.remove("ABC",-9);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveNotContained() {
		S1.remove("ABC",1);
	}
	
	@Test
	public void testRemoveContained() {
		S1.add("ABC",3);
		S1.remove("ABC",1);
		Assert.assertEquals(S1.count("ABC"), 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveContainedSameTimes() {
		S1.add("ABC",3);
		S1.remove("ABC",3);
	}
	
	@Test
	public void testRemoveContainedTwoSameOtherNot() {
		S1.add("JLK");
		S1.add("ABC",2);
		S1.add("ABC",1);
		assertEquals(S1.count("ABC"), 3);
		S1.remove("ABC", 1);
		assertEquals(S1.count("ABC"), 2);
	}
	
	
	@Test
	public void testRemoveFullCollection() throws EmptyCollectionException {
		S1.add("ABC");
		assertEquals(S1.remove(),1);
	}
	
	@Test(expected = EmptyCollectionException.class )
	public void testRemoveEmpty() throws EmptyCollectionException {
		S1.remove();
	}
	
	@Test
	public void testClear() {
		S1.add("ABC",3);
		S1.clear();
		
		assertEquals(S1.size(), 0);
	}
	
	@Test(expected = NullPointerException.class)
	public void testContainsNull() {
		S1.contains(null);
	}
	
	@Test
	public void testCountNotIncluded() {
		assertEquals(S1.count("ABC"), 0);
	}

	
	@Test
	public void testCountIncluded() {
		S1.add("BCA");
		S1.add("GJC");
		assertEquals(S1.count("GJC"), 1);
	}	
	
	@Test
	
	public void testContainsTrue() {
		S1.add("ABC");
		Assert.assertTrue(S1.contains("ABC"));
	}
	
	@Test
	
	public void testCountLastElement() {
		S1.add("ABv",11);
		S1.add("Jkl",7);
		
		assertEquals(S1.count("ABv"),11);
		assertEquals(S1.count("Jkl"),7);
		assertEquals(S1.count("lsd"),0);

	}
	
	@Test 
	public void testRemoveNothing(){
		assertEquals(S1.count("ABC"),0);
	}
	
	
	@Test
	public void testGetIterator() {
		assertFalse(S1.iterator().hasNext());
	}
	
	@Test
	public void testHasNext() {
		S1.add("22");
		S1.add("22");
		assertTrue(S1.iterator().hasNext());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testNextNotHasNext() {
		S1.iterator().next();
	}
	
	@Test
	public void testNextWithHasNext() {
		S1.add("22");
		S1.add("22");
		assertEquals(S1.iterator().next().toString(),"22");
		assertEquals(S1.iterator().next().toString(), "22");
	}
	
	@Test
	public void testCountNotOnCollection() {
		S1.add("ABC");
		assertEquals(S1.count("lpo"), 0);
	}	

}
