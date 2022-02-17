package wolftasks.test.tasks;

import static org.junit.Assert.*;

import org.junit.Test;

import wolftasks.src.tasks.Task;
import wolftasks.src.tasks.TaskList;

/**
 * Tests the TaskList class
 * @author Tyler Strickland
 *
 */
public class TaskListTest {

	/**
	 * Tests getting the String array of Tasks in the TaskList
	 */
	@Test
	public void testGetTasksAsArray() {
		TaskList list = new TaskList("Activities", 100);
		Task t = new Task("Complete Homework", "Complete homework for the week", true, false);
		Task t2 = new Task("Work on Project", "Work on Project 2 Part 2", false, true);
		Task t3 = new Task("Doctors appointment", "Go to doctors appointment", false, false);
		list.addTask(t);
		list.addTask(t2);
		list.addTask(t3);
		String[][] arr = list.getTasksAsArray();
		assertEquals("1", arr[0][0]);
		assertEquals("Complete Homework", arr[0][1]);
		assertEquals("2", arr[1][0]);
		assertEquals("Work on Project", arr[1][1]);
		assertEquals("3", arr[2][0]);
		assertEquals("Doctors appointment", arr[2][1]);
	
	}

	/**
	 * Tests constructing a new TaskList
	 */
	@Test
	public void testTaskList() {
		TaskList list = new TaskList("Homework", 1000);
		assertEquals("Homework", list.getTaskListName());
		assertEquals(1000, list.getCompletedCount());
		assertEquals(0, list.getTasks().size());
	}

	/**
	 * Tests comparing the names of two TaskLists
	 */
	@Test
	public void testCompareTo() {
		TaskList list = new TaskList("A", 1000);
		TaskList list2 = new TaskList("B", 100);
		TaskList list3 = new TaskList("C", 500);
		
		assertEquals(-1, list.compareTo(list2));
		assertEquals(-2, list.compareTo(list3));
		assertEquals(-1, list2.compareTo(list3));
		assertEquals(1, list3.compareTo(list2));
		
	}

}
