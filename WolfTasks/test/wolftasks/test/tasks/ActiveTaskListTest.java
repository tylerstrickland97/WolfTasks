package wolftasks.test.tasks;

import static org.junit.Assert.*;

import org.junit.Test;

import wolftasks.src.tasks.ActiveTaskList;
import wolftasks.src.tasks.Task;
import wolftasks.src.tasks.TaskList;

/**
 * Tests the ActiveTaskList class
 * @author Tyler Strickland
 *
 */
public class ActiveTaskListTest {

	/**
	 * Tests setting the name of the ActiveTaskList
	 */
	@Test
	public void testSetTaskListName() {
		ActiveTaskList a = new ActiveTaskList();
		
		try {
			a.setTaskListName("Tasks Active");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The Active Tasks list may not be edited.", e.getMessage());
		}
		
		try {
			a.setTaskListName("Active Tasks");
			assertEquals(ActiveTaskList.ACTIVE_TASKS_NAME, a.getTaskListName());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Tests adding a Task to the ActiveTaskList
	 */
	@Test
	public void testAddTask() {
		ActiveTaskList a = new ActiveTaskList();
		Task t = new Task("Brush teeth", "Brush teeth before bed", true, false);
		Task t2 = new Task("Work on Project", "Work on Project 2 Part 2", false, true);
		try {
			a.addTask(t);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot add task to Active Tasks", e.getMessage());
			assertEquals(0, a.getTasks().size());
		}
		
		try {
			a.addTask(t2);
			assertEquals(1, a.getTasks().size());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Tests getting the string array of the Tasks in the ActiveTaskList
	 */
	@Test
	public void testGetTasksAsArray() {
		ActiveTaskList a = new ActiveTaskList();
		Task t = new Task("Sweep the floor", "Sweep the living room", false, true);
		Task t2 = new Task("Go grocery shopping", "Get groceries for next week", true, true);
		Task t3 = new Task("Write notes", "Write notes for CSC216", true, true);
		TaskList list = new TaskList("Chores", 50);
		TaskList list2 = new TaskList("After School", 75);
		TaskList list3 = new TaskList("Homework", 1000);
		list.addTask(t);
		list2.addTask(t2);
		list3.addTask(t3);
		a.addTask(t);
		a.addTask(t2);
		a.addTask(t3);
		String[][] arr = a.getTasksAsArray();
		assertEquals("Chores", arr[0][0]);
		assertEquals("Sweep the floor", arr[0][1]);
		assertEquals("After School", arr[1][0]);
		assertEquals("Go grocery shopping", arr[1][1]);
		assertEquals("Homework", arr[2][0]);
		assertEquals("Write notes", arr[2][1]);
	}

	/**
	 * Tests constructing a new ActiveTaskList
	 */
	@Test
	public void testActiveTaskList() {
		ActiveTaskList a = new ActiveTaskList();
		assertEquals("Active Tasks", a.getTaskListName());
		assertEquals(0, a.getCompletedCount());
	}

	/**
	 * Tests clearing all the Tasks in ActiveTaskList
	 */
	@Test
	public void testClearTasks() {
		ActiveTaskList a = new ActiveTaskList();
		Task t = new Task("Sweep the floor", "Sweep the living room", false, true);
		Task t2 = new Task("Go grocery shopping", "Get groceries for next week", true, true);
		Task t3 = new Task("Write notes", "Write notes for CSC216", true, true);
		a.addTask(t);
		a.addTask(t2);
		a.addTask(t3);
		assertEquals(3, a.getTasks().size());
		a.clearTasks();
		assertEquals(0, a.getTasks().size());
		
	}

}
