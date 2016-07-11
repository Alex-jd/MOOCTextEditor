/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		//System.out.println(list1.get(5));
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		try {
			emptyList.remove(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			shortList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		String aShortList = shortList.remove(0);
		assertEquals("Remove: check a is correct ", "A", aShortList);
		assertEquals("Remove: check element 0 is correct ", "B", shortList.get(0));
		assertEquals("Remove: check size is correct ", 1, shortList.size());
		
		
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			int aLongerListRemove = longerList.remove(0);
			assertEquals("Check element", i, aLongerListRemove);
		}
		assertEquals("Remove: check size is correct ", 0, longerList.size());
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		Object temp1 = emptyList.tail.prev.data;
		emptyList.add(10);
		assertEquals("Remove: check a is correct ", null, temp1);
		assertEquals("Remove: check element 0 is correct ", (Integer)10, emptyList.tail.prev.data);
		assertEquals("Remove: check size is correct ", 1, emptyList.size());
		
		Object temp2 = shortList.tail.prev.data;
		shortList.add("ZZZ");
		assertEquals("Remove: check a is correct ", "B", temp2);
		assertEquals("Remove: check element 0 is correct ", "ZZZ", shortList.tail.prev.data);
		assertEquals("Remove: check size is correct ", 3, shortList.size());
		
		Object temp3 = longerList.tail.prev.data;
		longerList.add(100);
		assertEquals("Remove: check a is correct ", 9, temp3);
		assertEquals("Remove: check element 0 is correct ", (Integer)100, longerList.tail.prev.data);
		assertEquals("Remove: check size is correct ", 11, longerList.size());
		
		Object temp4 = list1.tail.prev.data;
		list1.add(652142);
		assertEquals("Remove: check a is correct ", 42, temp4);
		assertEquals("Remove: check element 0 is correct ", (Integer)652142, list1.tail.prev.data);
		assertEquals("Remove: check size is correct ", 4, list1.size());
		
		/*
		 shortList
		 emptyList
		longerList
		 list1
		*/
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Remove: check size is correct ", 0, emptyList.size());
		assertEquals("Remove: check size is correct ", 2, shortList.size());
		assertEquals("Remove: check size is correct ", 10, longerList.size());
		assertEquals("Remove: check size is correct ", 3, list1.size());
		
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		
		
		try {
			emptyList.add(1, 100);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			shortList.add(-1, "ZZZ");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			shortList.add(0, null);
			fail("Check out of nullPointer");
		}
		catch (NullPointerException e) {
		
		}
		
		shortList.add(1, "ZZZ");
		assertEquals("Remove: check element 0 is correct ", "ZZZ", shortList.get(1));
		//System.out.println(shortList.get(1));
		assertEquals("Remove: check size is correct ", 3, shortList.size());
		
		longerList.add(5, 100);
		assertEquals("Remove: check element 0 is correct ", (Integer)100, longerList.get(5));
		assertEquals("Remove: check size is correct ", 11, longerList.size());
		
		list1.add(2, 652142);
		assertEquals("Remove: check element 0 is correct ", (Integer)652142, list1.get(2));
		assertEquals("Remove: check size is correct ", 4, list1.size());
		
		emptyList.add(0, 100);
		assertEquals("Remove: check element 0 is correct ", (Integer)100, emptyList.get(0));
		assertEquals("Remove: check size is correct ", 1, emptyList.size());
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		try {
			emptyList.set(0, 100);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			shortList.set(-1, "ZZZ");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			shortList.set(0, null);
			fail("Check out of nullPointer");
		}
		catch (NullPointerException e) {
		
		}
		
		String temp2 = shortList.get(0);
		shortList.set(0, "ZZZ");
		assertEquals("Remove: check a is correct ", "A", temp2);
		assertEquals("Remove: check element 0 is correct ", "ZZZ", shortList.get(0));
		
		Integer temp3 = longerList.get(6);
		longerList.set(6, 100);
		assertEquals("Remove: check a is correct ", (Integer)6, temp3);
		assertEquals("Remove: check element 0 is correct ", (Integer)100, longerList.get(6));
		
		Integer temp4 = list1.get(1);
		list1.set(1, 652142);
		assertEquals("Remove: check a is correct ", (Integer)21, temp4);
		assertEquals("Remove: check element 0 is correct ", (Integer)652142, list1.get(1));
		
		
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
