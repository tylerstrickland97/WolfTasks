package wolftasks.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import wolftasks.src.util.SortedList;

/**
 * Tests the SortedList class
 * @author Tyler Strickland
 *
 */
public class SortedListTest {

	/**
	 * Tests constructing a new SortedList
	 */
	@Test
	public void testSortedList() {
		SortedList<String> s = new SortedList<String>();
		assertEquals(0, s.size());
	}

	/**
	 * Tests adding to the SortedList
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		//Test adding null
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals("Cannot add null element.", e.getMessage());
			assertEquals(0, list.size());
		}
		//Test adding to an empty list
		list.add("Apple");
		assertEquals(1, list.size());
		assertEquals("Apple", list.get(0));


		//Test adding to the end of the list 
		list.add("Cherry");
		assertEquals(2, list.size());
		assertEquals("Cherry", list.get(1));


		//Test adding to the middle of the list
		list.add("Banana");
		assertEquals(3, list.size());
		assertEquals("Banana", list.get(1));

		//Test with more values

		list.add("Guava");
		assertEquals(4, list.size());
		assertEquals("Guava", list.get(3));

		list.add("Acorn");
		assertEquals(5, list.size());
		assertEquals("Acorn", list.get(0));
		assertEquals("Apple", list.get(1));
		assertEquals("Banana", list.get(2));
		assertEquals("Cherry", list.get(3));
		assertEquals("Guava", list.get(4));
	}

	/**
	 * Tests removing from the SortedList
	 */
	@Test
	public void testRemove() {
		SortedList<String> s = new SortedList<String>();
		s.add("Apple");
		s.add("Banana");
		s.add("Cherry");
		s.add("Danish");
		
		try {
			s.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, s.size());
		}
		
		try {
			s.remove(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, s.size());
		}
		
		
		SortedList<String> list = new SortedList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.add("Danish");
		list.remove(1);
		assertEquals(3, list.size());
		assertEquals("Apple", list.get(0));
		assertEquals("Cherry", list.get(1));
		list.remove(0);
		assertEquals(2, list.size());
		assertEquals("Cherry", list.get(0));
		
		
		SortedList<String> r = new SortedList<String>();
		r.add("banana");
		r.add("apple");
		r.add("orange");
		r.add("eggplant");
		assertEquals("banana", r.remove(1));
		
		
	
	}

	/**
	 * Tests the contains method
	 */
	@Test
	public void testContains() {
		SortedList<String> s = new SortedList<String>();
		s.add("Apple");
		s.add("Banana");
		s.add("Cherry");
		s.add("Danish");
		
		assertTrue(s.contains("Apple"));
		assertTrue(s.contains("Banana"));
		assertTrue(s.contains("Cherry"));
		assertTrue(s.contains("Danish"));
		assertFalse(s.contains("Train"));
		assertFalse(s.contains("Berry"));
		assertFalse(s.contains("Mouse"));
		assertFalse(s.contains("Orange"));
	}


}
