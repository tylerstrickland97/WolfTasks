package wolftasks.test.util;
import static org.junit.Assert.*;

import org.junit.Test;

import wolftasks.src.util.SwapList;

/**
 * Tests the SwapList class
 * @author Tyler Strickland
 *
 */
public class SwapListTest {

	/**
	 * Tests constructing a new SwapList
	 */
	@Test
	public void testSwapList() {
		SwapList<String> s = new SwapList<String>();
		
		assertEquals(0, s.size());
	}

	/**
	 * Tests adding to the SwapList
	 */
	@Test
	public void testAdd() {
		SwapList<String> s = new SwapList<String>();
		
		try {
			s.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(0, s.size());
		}
		
		try {
			s.add("Apple");
			s.add("Banana");
			assertEquals(2, s.size());
		} catch (NullPointerException e) {
			fail(e.getMessage());
		}
		
		
		
		SwapList<String> a = new SwapList<String>();
		a.add("apple");
		a.add("pear");
		a.add("banana");
		a.add("cherry");
		a.add("blueberries");
		a.add("avocado");
		a.add("clementine");
		a.add("cranberries");
		a.add("elderberries");
		a.add("grapefruit");
		a.add("grapes");
		a.add("plum");
		a.add("kumquat");
		a.add("lemon");
		a.add("lime");
		a.add("mango");
		a.add("orange");
		a.add("peach");
		a.add("nectarine");
		a.add("mulberries");
		a.add("pineapple");
		
		
		
	}
	
	
	
	

	/**
	 * Tests removing from the SwapList
	 */
	@Test
	public void testRemove() {
		SwapList<String> s = new SwapList<String>();
		s.add("Apple");
		s.add("Banana");
		
		try {
			s.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, s.size());
		}
		
		try {
			s.remove(2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, s.size());
		}
		
		try {
			s.remove(0);
			assertEquals(1, s.size());
			s.remove(0);
			assertEquals(0, s.size());
		} catch (IndexOutOfBoundsException e) {
			fail(e.getMessage());
		}
		
	}

	/**
	 * Tests moving an element up one spot in the SwapList
	 */
	@Test
	public void testMoveUp() {
		SwapList<String> s = new SwapList<String>();
		s.add("Apple");
		s.add("Banana");
		s.add("Cherry");
		
		assertEquals("Apple", s.get(0));
		s.moveUp(0);
		assertEquals("Apple", s.get(0));
		s.moveUp(1);
		assertEquals("Banana", s.get(0));
		s.moveUp(2);
		assertEquals("Cherry", s.get(1));
		assertEquals("Apple", s.get(2));
	}
	/**
	 * Tests moving an element down one spot in the SwapList
	 */
	@Test
	public void testMoveDown() {
		SwapList<String> s = new SwapList<String>();
		s.add("Apple");
		s.add("Banana");
		s.add("Cherry");
		
		s.moveDown(2);
		assertEquals("Cherry", s.get(2));
		s.moveDown(1);
		assertEquals("Banana", s.get(2));
		s.moveDown(0);
		assertEquals("Apple", s.get(1));
		s.moveDown(1);
		assertEquals("Apple", s.get(2));
	}
	/**
	 * Tests moving an element to the front in the SwapList
	 */
	@Test
	public void testMoveToFront() {
		SwapList<String> s = new SwapList<String>();
		s.add("Apple");
		s.add("Banana");
		s.add("Cherry");
		
		s.moveToFront(0);
		assertEquals("Apple", s.get(0));
		s.moveToFront(1);
		assertEquals("Banana", s.get(0));
		s.moveToFront(2);
		assertEquals("Cherry", s.get(0));
		
		SwapList<String> l = new SwapList<String>();
		l.add("apple");
		l.add("pear");
		l.add("banana");
		l.add("cherry");
		l.add("blueberries");
		l.moveToFront(2);
		assertEquals("apple", l.get(1));
	
		
	}
	/**
	 * Tests moving an element to the back in the SwapList
	 */
	@Test
	public void testMoveToBack() {
		SwapList<String> s = new SwapList<String>();
		s.add("Apple");
		s.add("Banana");
		s.add("Cherry");
		
		s.moveToBack(2);
		assertEquals("Cherry", s.get(2));
		s.moveToBack(1);
		assertEquals("Banana", s.get(2));
		s.moveToBack(0);
		assertEquals("Apple", s.get(2));
		
		SwapList<String> l = new SwapList<String>();
		l.add("apple");
		assertEquals(1, l.size());
		assertEquals("apple", l.get(0));
		l.add("pear");
		assertEquals(2, l.size());
		assertEquals("pear", l.get(1));
		l.add("banana");
		assertEquals(3, l.size());
		assertEquals("banana", l.get(2));
		l.add("cherry");
		assertEquals(4, l.size());
		assertEquals("cherry", l.get(3));
		l.add("blueberries");
		assertEquals(5, l.size());
		assertEquals("blueberries", l.get(4));
		l.moveToBack(2);
		assertEquals("cherry", l.get(2));
		
	}


}
