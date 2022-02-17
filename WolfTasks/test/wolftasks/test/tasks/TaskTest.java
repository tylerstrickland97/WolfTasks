
   
package wolftasks.test.tasks;

import static org.junit.Assert.*;

import org.junit.Test;

import wolftasks.src.tasks.Task;
import wolftasks.src.tasks.TaskList;

/**
 * Tests the Task class
 * @author Tyler Strickland
 *
 */
public class TaskTest {

	/**
	 * Tests constructing a new Task
	 */
	@Test
	public void testTask() {
		Task t = new Task("Work on Project", "Work on Project 2 for CSC216", false, true);
		assertEquals("Work on Project", t.getTaskName());
		assertEquals("Work on Project 2 for CSC216", t.getTaskDescription());
		assertFalse(t.isRecurring());
		assertTrue(t.isActive());
	}


	/**
	 * Tests setting the name of the Task
	 */
	@Test
	public void testSetTaskName() {
		Task t = new Task("Work on Project", "Work on Project 2 for CSC216", false, true);
		try {
			t.setTaskName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Incomplete task information.", e.getMessage());
		}
		
		try {
			t.setTaskName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Incomplete task information.", e.getMessage());
		}
		
		try {
			t.setTaskName("Complete Project 2");
			assertEquals("Complete Project 2", t.getTaskName());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}


	/**
	 * Tests setting the description of the Task
	 */
	@Test
	public void testSetTaskDescription() {
		Task t = new Task("Work on Project", "Work on Project 2 for CSC216", false, true);
		
		try {
			t.setTaskDescription(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Incomplete task information.", e.getMessage());
			assertEquals("Work on Project 2 for CSC216", t.getTaskDescription());
		}
		
		try {
			t.setTaskDescription("Complete Project for CSC216");
			assertEquals("Complete Project for CSC216", t.getTaskDescription());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests setting the recurring variable of the Task
	 */
	@Test
	public void testSetRecurring() {
		Task t = new Task("Work on Project", "Work on Project 2 for CSC216", false, true);
		
		t.setRecurring(true);
		assertTrue(t.isRecurring());
	}


	/**
	 * Tests setting the active variable of the Task
	 */
	@Test
	public void testSetActive() {
		Task t = new Task("Work on Project", "Work on Project 2 for CSC216", false, true);
		t.setActive(false);
		assertFalse(t.isActive());
	}

	/**
	 * Test getting the TaskList name at the front of the list of TaskLists 
	 */
	@Test
	public void testGetTaskListName() {
		Task t = new Task("Work on Project", "Work on Project 2 for CSC216", false, true);
		TaskList list = new TaskList("CSC216", 20);
		
		t.addTaskList(list);
		assertEquals("CSC216", t.getTaskListName());
		TaskList list2 = new TaskList("CSC226", 50);
		t.addTaskList(list2);
		assertEquals("CSC216", t.getTaskListName());
	}

	/**
	 * Tests adding a TaskList to the Tasks list of TaskLists
	 */
	@Test
	public void testAddTaskList() {
		Task t = new Task("Work on Project", "Work on Project 2 for CSC216", false, true);
		try {
			t.addTaskList(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Incomplete task information.", e.getMessage());
		}
		TaskList list = new TaskList("CSC216", 20);
		t.addTaskList(list);
		assertEquals("CSC216", t.getTaskListName());
		
		TaskList list2 = new TaskList("CSC226", 50);
		t.addTaskList(list2);
		assertEquals("CSC216", t.getTaskListName());

		
	}

	/**
	 * Tests completing a Task
	 */
	@Test
	public void testCompleteTask() {
		Task t = new Task("Work on Project", "Work on Project 2 for CSC216", false, true);
		TaskList list = new TaskList("CSC216", 20);
		list.addTask(t);
		assertEquals(1, list.getTasks().size());
		t.completeTask();
		assertEquals(0, list.getTasks().size());
		
		Task r = new Task("Task2", "Task2Descripton", true, false);
		TaskList l = new TaskList("TaskList1", 20);
		l.addTask(r);
		assertEquals(1, l.getTasks().size());
		r.completeTask();
		assertEquals(1, l.getTasks().size());
	}

	/**
	 * Tests cloning a Task
	 */
	@Test
	public void testClone() {
		Task t = new Task("Work on Project", "Work on Project 2 for CSC216", false, true);
		TaskList list = new TaskList("CSC216", 50);
		Task c;
		try {
			c = (Task) t.clone();
			fail();
		} catch (CloneNotSupportedException e) {
			assertEquals("", t.getTaskListName());
		}
		t.addTaskList(list);
		try {
			c = (Task) t.clone();
			assertEquals("Work on Project", c.getTaskName());
			assertEquals("Work on Project 2 for CSC216", c.getTaskDescription());
			assertFalse(c.isRecurring());
			assertTrue(c.isActive());
		} catch (CloneNotSupportedException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests creating a String representation of the Task
	 */
	@Test
	public void testToString() {
		Task t = new Task("Work on Project", "Work on Project 2 for CSC216", false, true);
		Task t2 = new Task("Brush teeth", "Brush teeth before bed", true, true);
		Task t3 = new Task("Eat breakfast", "Eat breakfast before class", true, false);
		Task t4 = new Task("Work on CSC226 Homework", "Complete CSC226 homework", false, false);
		
		assertEquals("* Work on Project,active" + '\n' + "Work on Project 2 for CSC216", t.toString());
		assertEquals("* Brush teeth,recurring,active" + '\n' + "Brush teeth before bed", t2.toString());
		assertEquals("* Eat breakfast,recurring" + '\n' + "Eat breakfast before class", t3.toString());
		assertEquals("* Work on CSC226 Homework" + '\n' + "Complete CSC226 homework", t4.toString());
	}

}
