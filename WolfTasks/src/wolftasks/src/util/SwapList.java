/**
 * 
 */
package wolftasks.src.util;

import java.util.Arrays;

/**
 * This class is a custom array list. It is similar to a regular ArrayList, but allows elements to be swapped and moved interchangeably throughout the list without major iteration.
 * @author Tyler Strickland
 * @param <E> generic type argument for SwapList
 *
 */
public class SwapList<E> implements ISwapList<E> {

	/** Initial capacity of the list array */
	private static final int INITIAL_CAPACITY = 10;
	/** Array that holds all of the data in the list */
	private E[] list;
	/** Size of the list */
	private int size;
	
	/**
	 * Constructs a new SwapList by initializing the list field and setting size to 0
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		list = (E[]) (new Object[INITIAL_CAPACITY]);
		size = 0;
	}
	
	/**
	 * Adds an element to the list and udpates size
	 * @param element element to add to the list
	 * @throws NullPointerException if the element is null
	 */
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		checkCapacity(size);
		size++;
		list[size - 1] = element;
		
	}
	
	/** Checks the capacity of the list in case more room needs to be added in the list
	 * @param size size of the list
	 */
	private void checkCapacity(int size) {
		if (size == INITIAL_CAPACITY) {
			int capacity = (int) (Math.E * INITIAL_CAPACITY);
			list = Arrays.copyOf(list, capacity);
		}
		else if (size > 10 && size % 10 == 0) {
			int capacity = (int)(Math.E * size);
			list = Arrays.copyOf(list, capacity);
		}
	}

	
	/**
	 * Removes the element at the specific index and then shifts the elements if needed, then decrements size
	 * @param idx index of the List to remove
	 * @return data removed from the list
	 */
	public E remove(int idx) {
		checkIndex(idx);
		
		E element = list[idx];
		if (idx == size - 1) {
			list[size - 1] = null;
			size--;
			return element;
		}
		else {
			for (int i = idx; i < size; i++) {
				list[i] = list[i + 1];
			}
			size--;
			return element;
		}
		
		
	}
	
	/**
	 * Checks the index specified in the parameter and makes sure it is within the bounds of the list
	 * @param idx idx to check whether in the bounds of the list or not
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to size
	 */
	private void checkIndex(int idx) {
		if (idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	
	/**
	 * Moves the element at the specified index up one spot in the list
	 * @param idx index of the list to shift up one spot
	 */
	public void moveUp(int idx) {
		checkIndex(idx);
		if (idx == 0) {
			return;
		}
		else {
			E temp = list[idx - 1];
			list[idx - 1] = list[idx];
			list[idx] = temp;
		}
	}
	
	/**
	 * Moves the element at the specified index down one spot in the list
	 * @param idx index of the element to shift
	 */
	public void moveDown(int idx) {
		checkIndex(idx);
		if (idx == size - 1) {
			return;
		}
		else {
			E temp = list[idx + 1];
			list[idx + 1] = list[idx];
			list[idx] = temp;
		}
	}
	
	/**
	 * Moves the element at the specified index to the front of the list, then shifts the other elements
	 * @param idx index of the element to move to front
	 */
	public void moveToFront(int idx) {
		checkIndex(idx);
		if (idx == 0) {
			return;
		}
		else {
			E temp = list[idx];
			for (int i = idx; i > 0; i--) {
				list[i] = list[i - 1];
			}
			list[0] = temp;
		}
	}
	/**
	 * Moves the element at the specified index to the back of the list, then shifts the other elements
	 * @param idx index of the element to move to back
	 */
	public void moveToBack(int idx) {
		checkIndex(idx);
		if (idx == size - 1) {
			return;										                                           
		}
		else {
			E back = list[idx];
			for (int i = idx; i < size - 1; i++) {
				list[i] = list[i + 1];
			}
			list[size - 1] = back;
		}
	}
	
	/**
	 * Returns the element at the specified index of the list
	 * @param idx index of the list to get
	 * @return element at the specified index 
	 */
	public E get(int idx) {
		checkIndex(idx);
		return list[idx];
	}
	
	/**
	 * Returns the size of the list
	 * @return size of the list
	 */
	public int size() {
		return size;
	}
	
	
}
