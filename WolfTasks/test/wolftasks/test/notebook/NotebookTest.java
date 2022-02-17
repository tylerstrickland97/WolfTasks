
   
package wolftasks.test.notebook;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import wolftasks.src.notebook.Notebook;
import wolftasks.src.tasks.Task;
import wolftasks.src.tasks.TaskList;



/**
 * Tests the Notebook class
 * @author Tyler Strickland
 *
 */
public class NotebookTest {

	/** 
	 * Tests constructing a notebook
	 */
	@Test
	public void testNotebook() {
		try {
			Notebook n = new Notebook("Notebook1");
			assertEquals("Notebook1", n.getNotebookName());
			assertTrue(n.isChanged());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		Notebook book = null;
		try {
			book = new Notebook(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(book);
		}
		
		try {
			book = new Notebook("");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(book);
		}
	}

	/** 
	 * Tests saving a Notebook
	 */
	@Test
	public void testSaveNotebook() {
		Notebook n = new Notebook("School");
		TaskList list1 = new TaskList("CSC 216", 35);
		TaskList list2 = new TaskList("CSC 226", 23);
		TaskList list3 = new TaskList("Habits", 0);
		n.addTaskList(list1);
		Task task1 = new Task("Read Project 2 Requirements", "Read Project 2 requirements " + '\n'  + "(https://pages.github.ncsu.edu/engr-csc216-staff/CSC216-SE-Materials/projects/project2/project2-part1.html)" + '\n' +
				"and identify candidate classes and methods.", false, false);
		Task task2 = new Task("Create CRC Cards", "Identify the key classes and create CRC cards. Note" + '\n' + "responsibilities, collaborators, and possible state.", false, true);
		Task task20 = new Task("Transfer CRC Cards to UMLetino", "Start creating a UML class diagram from the requirements", false, false);
		Task task3 = new Task("Download design proposal and rational template", "See (https://pages.github.ncsu.edu/engr-csc216-staff/CSC216-SE-Materials/projects/project2/project2-part1.html)" + '\n'
				+ "for template link", false, false);
		Task task4 = new Task("Write design proposal and rationale", "Start with UML class diagram description. Incorporate feedback " + '\n'
				+ "from Project 1.", false, false);
		Task task21 = new Task("Identify 5 system tests", "Consider 5 major paths through the system when working with" + '\n' + 
				"notebooks, task lists, and tasks.  How would I use the system" + '\n'
				+ "to keep track of my tasks?", false, false);
		Task task5 = new Task("Watch lecture video", "Keep up with lecture videos each week", true, true);
		Task task6 = new Task("Complete exercises", "Complete exercises by Sunday at 11:45pm each week", true, false);
		Task task7 = new Task("Complete quizzes", "Weekly quizzes open Thursdays at 3pm and close Mondays at 11:30am\n"
				+ "(all times Eastern)", true, false);
		n.addTask(task1);
		n.addTask(task2);
		n.addTask(task20);
		n.addTask(task3);
		n.addTask(task4);
		n.addTask(task21);
		n.addTask(task5);
		n.addTask(task6);
		n.addTask(task7);
		
		n.addTaskList(list2);
		Task task8 = new Task("Homework 7", "- Review the assignment\n"
				+ "- Schedule time to work on the assignment\n"
				+ "Don't forget to submit!", false, false);
		Task task9 = new Task("Homework 8", "- Review the assignment\n"
				+ "- Schedule time to work on the assignment\n"
				+ "Don't forget to submit!", false, false);
		Task task10 = new Task("Homework 9", "- Review the assignment\n"
				+ "- Schedule time to work on the assignment\n"
				+ "Don't forget to submit!", false, false);
		Task task11 = new Task("Homework 10", "- Review the assignment\n"
				+ "- Schedule time to work on the assignment\n"
				+ "Don't forget to submit!", false, false);
		Task task12 = new Task("Watch lectures", "Watch lectures associated with HW7 by March 31", true, true);
		n.addTask(task8);
		n.addTask(task9);
		n.addTask(task10);
		n.addTask(task11);
		n.addTask(task12);
		
		n.addTaskList(list3);
		Task task13 = new Task("Exercise", "Exercise every day. \n"
				+ "Alternate between cardio and weight training", true, true);
		Task task14 = new Task("Floss", "Floss when brushing my teeth before bed! ", true, true);
		n.addTask(task13);
		n.addTask(task14);
		assertEquals(4, n.getTaskListsNames().length);
		File file = new File("test-files/actual_notebook1.txt");
		n.saveNotebook(file);
		checkFiles("test-files/notebook1.txt", "test-files/actual_notebook1.txt");
		
		
	}

	/**
	 * Tests adding a TaskList to the notebook
	 */
	@Test
	public void testAddTaskList() {
		Notebook n = new Notebook("Notebook1");
		TaskList list = new TaskList("CSC216", 50);
		try {
			n.addTaskList(list);
			assertEquals(list, n.getCurrentTaskList());
			assertTrue(n.isChanged());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
		TaskList list2 = new TaskList("Chores", 500);
		try {
			n.addTaskList(list2);
			assertEquals(list2, n.getCurrentTaskList());
			assertTrue(n.isChanged());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
		TaskList list3 = new TaskList("Chores", 30);
		try {
			n.addTaskList(list3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
			assertEquals(list2, n.getCurrentTaskList());
		}
	}

	/**
	 * Tests getting the array of TaskList names in the notebook
	 */
	@Test
	public void testGetTaskListsNames() {
		Notebook n = new Notebook("Notebook1");
		TaskList list = new TaskList("CSC216", 50);
		TaskList list2 = new TaskList("Chores", 500);
		TaskList list3 = new TaskList("CSC226", 30);
		String[] a = n.getTaskListsNames();
		assertEquals(1, n.getTaskListsNames().length);
		assertEquals("Active Tasks", a[0]);
		
		n.addTaskList(list);
		n.addTaskList(list2);
		n.addTaskList(list3);
		
		String[] arr = n.getTaskListsNames();
		assertEquals(4, n.getTaskListsNames().length);
		assertEquals("Active Tasks", arr[0]);
		assertEquals("CSC216", arr[1]);
		assertEquals("CSC226", arr[2]);
		assertEquals("Chores", arr[3]);
		
		
	}

	/**
	 * Tests setting the currentTaskList in the notebook
	 */
	@Test
	public void testSetCurrentTaskList() {
		Notebook n = new Notebook("Notebook1");
		TaskList list = new TaskList("CSC216", 50);
		TaskList list2 = new TaskList("Chores", 500);
		TaskList list3 = new TaskList("CSC226", 30);
		
		n.addTaskList(list);
		n.addTaskList(list2);
		n.addTaskList(list3);
		
		assertEquals(list3, n.getCurrentTaskList());
		n.setCurrentTaskList("CSC216");
		assertEquals(list, n.getCurrentTaskList());
		n.setCurrentTaskList("Chores");
		assertEquals(list2, n.getCurrentTaskList());
		n.setCurrentTaskList("CSC226");
		assertEquals(list3, n.getCurrentTaskList());
				
	}


	/**
	 * Tests editing a TaskList in the notebook
	 */
	@Test
	public void testEditTaskList() {
		Notebook n = new Notebook("Notebook1");
		TaskList list = new TaskList("CSC216", 50);
		TaskList list2 = new TaskList("Chores", 500);
		TaskList list3 = new TaskList("CSC226", 30);
		n.addTaskList(list);
		
		try {
			n.editTaskList("Active Tasks");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			n.editTaskList("CSC216");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			n.editTaskList("csc216");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			n.editTaskList("Csc216");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		n.addTaskList(list2);
		n.addTaskList(list3);
		
		n.setCurrentTaskList("CSC216");
		assertEquals(list, n.getCurrentTaskList());
		try {
			n.editTaskList("Chores");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			n.editTaskList("CHORES");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			n.editTaskList("chores");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			n.editTaskList("CSC226");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			n.editTaskList("csc226");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			n.editTaskList("CHORES");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name.", e.getMessage());
		}
		
		try {
			n.editTaskList("New Name");
			assertEquals("New Name", n.getCurrentTaskList().getTaskListName());
			assertEquals(4, n.getTaskListsNames().length);
			assertTrue(n.isChanged());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
	}

	/**
	 * Tests removing a TaskList in the Notebook
	 */
	@Test
	public void testRemoveTaskList() {
		Notebook n = new Notebook("Notebook1");
		TaskList list = new TaskList("CSC216", 50);
		TaskList list2 = new TaskList("Chores", 500);
		TaskList list3 = new TaskList("CSC226", 30);
		n.addTaskList(list);
		n.addTaskList(list2);
		n.addTaskList(list3);
		
		assertEquals(4, n.getTaskListsNames().length);
		n.removeTaskList();
		assertEquals(3, n.getTaskListsNames().length);
		assertTrue(n.isChanged());
		n.setCurrentTaskList("Chores");
		n.removeTaskList();
		assertEquals(2, n.getTaskListsNames().length);
		assertTrue(n.isChanged());
		
		try {
			n.removeTaskList();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The Active Tasks list may not be deleted.", e.getMessage());
		}
	}

	/**
	 * Tests adding a Task to the currentTaskList in the Notebook
	 */
	@Test
	public void testAddTask() {
		Notebook n = new Notebook("Notebook1");
		TaskList list = new TaskList("CSC216", 50);
		n.addTaskList(list);
		
		Task t = new Task("Work on Project 2", "Work on Project 2 to complete PP2", false, true);
		n.addTask(t);
		assertEquals(1, n.getCurrentTaskList().getTasks().size());
		assertTrue(n.isChanged());
		
		try {
			Task t2 = new Task("", "study for exams", false, false);
			n.addTask(t2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, n.getCurrentTaskList().getTasks().size());
		}
	}

	/**
	 * Tests editing a Task in the currentTaskList in the Notebook
	 */
	@Test
	public void testEditTask() {
		Notebook n = new Notebook("Notebook1");
		TaskList list = new TaskList("CSC216", 50);
		n.addTaskList(list);
		Task t = new Task("Work on Project", "Work on Project for CSC216", false, true);
		Task t2 = new Task("Discrete math", "Work on discrete homework", false, false);
		Task t3 = new Task("Brush teeth", "Brush teeth before bed", true, false);
		n.addTask(t);
		n.addTask(t2);
		n.addTask(t3);
		
		n.editTask(0, "Complete Project", "Complete Project2", false, false);
		n.editTask(1, "Complete discrete homework", "Complete this week's homework for CSC226", false, true);
		n.editTask(2, "Clean room", "Clean room today", true, false);
		
		assertEquals("Complete Project", n.getCurrentTaskList().getTask(0).getTaskName());
		assertEquals("Complete discrete homework", n.getCurrentTaskList().getTask(1).getTaskName());
		assertEquals("Clean room", n.getCurrentTaskList().getTask(2).getTaskName());
		assertEquals("Complete Project2", n.getCurrentTaskList().getTask(0).getTaskDescription());
		assertEquals("Complete this week's homework for CSC226", n.getCurrentTaskList().getTask(1).getTaskDescription());
		assertEquals("Clean room today", n.getCurrentTaskList().getTask(2).getTaskDescription());
		assertFalse(n.getCurrentTaskList().getTask(0).isRecurring());
		assertFalse(n.getCurrentTaskList().getTask(1).isRecurring());
		assertTrue(n.getCurrentTaskList().getTask(2).isRecurring());
		assertFalse(n.getCurrentTaskList().getTask(0).isActive());
		assertTrue(n.getCurrentTaskList().getTask(1).isActive());
		assertFalse(n.getCurrentTaskList().getTask(2).isActive());
		
		Notebook b = new Notebook("Notebook1");
		TaskList l = new TaskList("TaskList1", 50);
		b.addTaskList(l);
		Task task1 = new Task("Task1", "Task1Description", false, false);
		Task task2 = new Task("Task2", "Task2Description", true, false);
		Task task3 = new Task("Task3", "Task3Description", true, true);
		Task task4 = new Task("Task4", "Task4Description", false, true);
		b.addTask(task1);
		b.addTask(task2);
		b.addTask(task3);
		b.addTask(task4);
		b.editTask(2, "Task5", "Task3Description", true, true);
		assertEquals("Task5", b.getCurrentTaskList().getTasks().get(2).getTaskName());
		b.editTask(0, "Task1", "New task description", false, false);
		assertEquals("New task description", b.getCurrentTaskList().getTasks().get(0).getTaskDescription());
		b.setCurrentTaskList("Active Tasks");
		assertEquals(2, b.getCurrentTaskList().getTasks().size());
		b.setCurrentTaskList("TaskList1");
		b.editTask(1, "Task2", "Task2Description", true, true);
		b.setCurrentTaskList("Active Tasks");
		assertEquals(3, b.getCurrentTaskList().getTasks().size());
		b.setCurrentTaskList("TaskList1");
		b.editTask(3, "Task4", "Task4Description", false, false);
		b.setCurrentTaskList("Active Tasks");
		assertEquals(2, b.getCurrentTaskList().getTasks().size());
		
		
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
