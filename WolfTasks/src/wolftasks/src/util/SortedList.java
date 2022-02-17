package wolftasks.src.util;

/**
 * This class is a custom Linked List. It has the same functionality as a regular LinkedList but requires elements to be sorted.
 * @author Tyler Strickland
 *
 * @param <E> type of data to be stored in the SortedList
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {

	/** Size of the list */
	private int size;
	/** Reference to the front of the List */
	private ListNode front;
	
	/** 
	 * Constructs a new SortedList by setting the size to 0 and the front of the list to null
	 */
	public SortedList() {
		size = 0; 
		front = null;
	}
	
	/**
	 * Adds the element to the list in sorted order and increments size
	 * @param element element to add to the list
	 * @throws NullPointerException if the element is null
	 */
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}

		else if (front == null || element.compareTo(front.data) < 0) {
			front = new ListNode(element, front);
		}
		else {
			ListNode n = front;
			while (n.next != null) {
				if (n.data.compareTo(element) == 0) {
					throw new IllegalArgumentException("Cannot add duplicate element.");
				}
				n = n.next;
			}
			ListNode curr = front;
			while (curr.next != null && curr.next.data.compareTo(element) < 0) {
				curr = curr.next;
			}
		
			curr.next = new ListNode(element, curr.next);

		}
		size++;

	}
	
	/**
	 * Removes the element at the specified index and updates size
	 * @param idx index of the list to remove
	 * @return the data that is removed from the list
	 * @throws IndexOutOfBoundsException if the index is less than zero or greater than or equal to the size of the list
	 */
	public E remove(int idx) {
		checkIndex(idx);
		if (front == null) {
			return null;
		}
		E rtn;
		if (idx == 0) {
			rtn = front.data;
			front = front.next;
			size--;
			return rtn;
		}
		else {
			ListNode curr = front;
			for (int i = 0; i < idx - 1; i++) {
				curr = curr.next;
			}
			rtn = curr.next.data;
			curr.next = curr.next.next;
			size--;
			return rtn;
		}
		
		
		
	}
	
	/**
	 * Checks to see if the idx is in the bounds of the list
	 * @param idx idx to check if within the bounds of the list
	 * @throws IndexOutOfBoundsException if the idx is less than 0 or greater than or equal to size
	 */
	private void checkIndex(int idx) {
		if (idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	/**
	 * Determines if an element exists in the list
	 * @param element element to determine if already in the list
	 * @return boolean of whether the element is in the list or not
	 */
	public boolean contains(E element) {
		ListNode curr = front;
		while (curr != null) {
			if (curr.data == element) {
				return true;
			}
			curr = curr.next;
		}
		return false;
	}
	
	/** 
	 * Returns the element at the specified index of the list
	 * @param idx index of the list to return 
	 * @return the data at the specific index of the list
	 */
	public E get(int idx) {
		checkIndex(idx);
			if (idx == 0) {
				return front.data;
			}
			else {
				ListNode curr = front;
				for (int i = 0; i < idx; i++) {
					curr = curr.next;
				}
				return curr.data;
			}
	}
	
	/**
	 * Returns the size of the list
	 * @return size of the list
	 */
	public int size() {
		return size;
	}
	
	/** 
	 * Inner class of the SortedList. This class represents a single node in the LinkedList
	 * @author Tyler Strickland
	 *
	 */
	public class ListNode {
		/** Data for the node */
		public E data;
		/** Reference to the next node in the list */
		public ListNode next;
		
		/** 
		 * Constructs a new ListNdoe
		 * @param data data for the ListNode
		 * @param next reference to the next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
	
} 
