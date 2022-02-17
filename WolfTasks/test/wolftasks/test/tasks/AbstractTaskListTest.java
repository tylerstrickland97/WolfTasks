package wolftasks.test.tasks;

import static org.junit.Assert.*;

import org.junit.Test;

import wolftasks.src.tasks.AbstractTaskList;
import wolftasks.src.tasks.Task;
import wolftasks.src.tasks.TaskList;
/**
 * Tests the AbstractTaskList class
 * @author Tyler Strickland
 *
 */
public class AbstractTaskListTest {

	/**
	 * Tests constructing a new AbstractTasksList
	 */
	@Test
	public void testAbstractTaskList() {
		AbstractTaskList list = new TaskList("CSC216", 20);
		assertEquals("CSC216", list.getTaskListName());
		assertEquals(20, list.getCompletedCount());
	}

	/**
	 * Tests setting the name of the AbstractTaskList
	 */
	@Test
	public void testSetTaskListName() {
		AbstractTaskList list = new TaskList("CSC216", 20);
		try {
			list.setTaskListName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			list.setTaskListName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			list.setTaskListName("CSC 216");
			assertEquals("CSC 216", list.getTaskListName());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}


	/**
	 * Tests adding a Task to the AbstractTaskList
	 */
	@Test
	public void testAddTask() {
		AbstractTaskList list = new TaskList("CSC216", 20);
		Task t = new Task("Work on Project", "Work on Project for CSC216", false, true);
		list.addTask(t);
		assertEquals(1, list.getTasks().size());
		
		Task t2 = new Task("Discrete math", "Work on discrete homework", false, false);
		list.addTask(t2);
		assertEquals(2, list.getTasks().size());
	}

	/**
	 * Tests removing a Task from the AbstractTaskList
	 */
	@Test
	public void testRemoveTask() {
		AbstractTaskList list = new TaskList("CSC216", 20);
		Task t = new Task("Work on Project", "Work on Project for CSC216", false, true);
		list.addTask(t);
		
		Task t2 = new Task("Discrete math", "Work on discrete homework", false, false);
		list.addTask(t2);
		assertEquals(2, list.getTasks().size());
		list.removeTask(1);
		assertEquals(1, list.getTasks().size());
		list.removeTask(0);
		assertEquals(0, list.getTasks().size());
		
	}

	/**
	 * Tests getting a Task from the AbstractTaskList
	 */
	@Test
	public void testGetTask() {
		AbstractTaskList list = new TaskList("CSC216", 20);
		Task t = new Task("Work on Project", "Work on Project for CSC216", false, true);
		list.addTask(t);
		Task t2 = new Task("Discrete math", "Work on discrete homework", false, false);
		list.addTask(t2);
		Task t3 = new Task("Brush teeth", "Brush teeth before bed", true, false);
		list.addTask(t3);
		assertEquals(t, list.getTask(0));
		assertEquals(t2, list.getTask(1));
		assertEquals(t3, list.getTask(2));
		
		
	}

	/**
	 * Tests completing a Task in the AbstractTaskList
	 */
	@Test
	public void testCompleteTask() {
		AbstractTaskList list = new TaskList("CSC216", 20);
		Task t = new Task("Work on Project", "Work on Project for CSC216", false, true);
		list.addTask(t);
		Task t2 = new Task("Discrete math", "Work on discrete homework", false, false);
		list.addTask(t2);
		Task t3 = new Task("Brush teeth", "Brush teeth before bed", true, false);
		list.addTask(t3);
		
		list.completeTask(t);
		assertEquals(21, list.getCompletedCount());
		assertEquals(2, list.getTasks().size());
		list.completeTask(t2);
		assertEquals(22, list.getCompletedCount());
		assertEquals(1, list.getTasks().size());
		list.completeTask(t3);
		assertEquals(23, list.getCompletedCount());
		assertEquals(0, list.getTasks().size());
		
	}

	/**
	 * Tests getting the String array of Tasks in the AbstractTaskList
	 */
	@Test
	public void testGetTasksAsArray() {
		AbstractTaskList list = new TaskList("CSC216", 20);
		Task t = new Task("Work on Project", "Work on Project for CSC216", false, true);
		list.addTask(t);
		Task t2 = new Task("Discrete math", "Work on discrete homework", false, false);
		list.addTask(t2);
		Task t3 = new Task("Brush teeth", "Brush teeth before bed", true, false);
		list.addTask(t3);
		
		String[][] arr = list.getTasksAsArray();
		assertEquals("1", arr[0][0]);
		assertEquals("Work on Project", arr[0][1]);
		assertEquals("2", arr[1][0]);
		assertEquals("Discrete math", arr[1][1]);
		assertEquals("3", arr[2][0]);
		assertEquals("Brush teeth", arr[2][1]);
	}

}
